package com.demo.source

import java.util.Properties

import com.demo.schema.MeetUpCaseClass
import org.apache.log4j.Logger
import org.apache.spark.sql.functions.{col, from_json}
import org.apache.spark.sql.streaming.Trigger
import org.apache.spark.sql.{DataFrame, SaveMode}

import scala.io.Source

object MeetUpStreaming extends  Serializable with SparkSessionWrapper {

  @transient lazy val logger: Logger = Logger.getLogger(getClass.getName)

  def main(args: Array[String]): Unit = {
    import spark.implicits._
    logger.info(" Starting Streaming Services ..")
    var properties : Properties = null
    val url = this.getClass.getResource("/application.properties")
    if (url != null) {
      val source = Source.fromURL(url)
      println("here")
      properties = new Properties()
      properties.load(source.bufferedReader())
    } else {
      println("an error occured")
    }

    val jdbcHostname = properties.getProperty("jdbcHostname")
    val jdbcPort = properties.getProperty("jdbcPort")
    val jdbcDatabase = properties.getProperty("jdbcDatabase")
    val jdbcTable = properties.getProperty("jdbcTable")
    val jdbcVenueTable = properties.getProperty("venueTable")
    val jdbcUrl = s"jdbc:sqlserver://${jdbcHostname}:${jdbcPort};database=${jdbcDatabase}"
    val streamDF = getDataDS()
    val query = streamDF.writeStream.trigger(Trigger.ProcessingTime("2 seconds")).outputMode("update").foreachBatch{
      (batchDF: DataFrame, batchId: Long) =>
        batchDF.select("group.*").show()
        batchDF.coalesce(2).select("venue.*").show()
         val venue = batchDF.select($"venue.venue_name", $"venue.lon", $"venue.lat",$"venue.venue_id")
         val event = batchDF.select($"event.event_name", $"event.event_id", $"event.time", $"event.event_url")
        event.write.mode(SaveMode.Append)
             .jdbc(jdbcUrl, jdbcTable, properties)
        venue.write.mode(SaveMode.Append)
          .jdbc(jdbcUrl, jdbcVenueTable, properties)
    }.start()

    query.awaitTermination()
    logger.info("Finished Streaming Services")
  }

  def getDataDF() ={
    spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", "pykafka")
      .option("startingOffsets", "latest")
      .load()
      .selectExpr("CAST(value as STRING)")

  }

  import org.apache.spark.sql.Encoders
  val jsonSchema = Encoders.product[MeetUpCaseClass].schema

  def getDataDS() = {
    import spark.implicits._
    spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", "pykafka")
      .option("startingOffsets", "latest")
      .load()
      .select($"value" cast "string" as "json")
      .select(from_json($"json", jsonSchema) as "data")
      .select("data.*")
  }


  def extractEvent(df: DataFrame): DataFrame =
    df.select(
      col("meetUpDF.event.event_name"),
      col("meetUpDF.event.event_id"),
      col("meetUpDF.event.time"),
      col("meetUpDF.event.event_url")
    )
}
