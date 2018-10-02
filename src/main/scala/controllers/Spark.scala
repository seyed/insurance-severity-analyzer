package controllers

import org.apache.spark.sql.SparkSession

class Spark {

  def sparkSession(): SparkSession = SparkSession.builder()
    .master("local")
    .appName("Analyzer")
    .getOrCreate()

}