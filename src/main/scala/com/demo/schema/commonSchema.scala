package com.demo.schema

import org.apache.spark.sql.types.{ArrayType, StringType, StructField, StructType}

object commonSchema {

  val meetSchema = StructType(Array(
    StructField("venue", StructType(Array(
      StructField("venue_name",  StringType),
      StructField("lon", StringType),
      StructField("lat", StringType),
      StructField("venue_id", StringType)
    ))),
    StructField("visibility", StringType),
    StructField("response", StringType),
    StructField("guests", StringType),
    StructField("member", StructType(Array(
      StructField("member_id", StringType),
      StructField("photo", StringType),
      StructField("member_name", StringType)
    ))),
    StructField("rsvp_id", StringType),
    StructField("mtime", StringType),
    StructField("event",StructType(Array(
      StructField("event_name", StringType),
      StructField("event_id", StringType),
      StructField("time",StringType),
      StructField("event_url", StringType)
    ))),
    StructField("group", StructType(Array
      (StructField("group_topics", ArrayType(StructType(Array(
        StructField("urlkey", StringType),
        StructField("topic_name", StringType)
      )), true)),
        StructField("group_city", StringType),
        StructField("group_country", StringType),
        StructField("group_id", StringType),
        StructField("group_name", StringType),
        StructField("group_lon", StringType),
        StructField("group_urlname", StringType),
        StructField("group_state", StringType),
        StructField("group_lat", StringType)
      )))
  ))

}
