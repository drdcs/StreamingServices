package com.demo.source

import org.apache.spark.sql.SparkSession

trait SparkSessionWrapper extends Serializable {
  lazy val spark: SparkSession = {
    SparkSession.builder().master("local[3]").appName("spark streaming").getOrCreate()
  }
}
