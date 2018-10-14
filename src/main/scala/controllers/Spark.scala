package controllers

import org.apache.spark.sql.SparkSession

trait Spark {
  def initiateSession(): SparkSession = SparkSession.builder()
    .master("local")
    .appName("Insurance Severity Predictor")
    .getOrCreate()
}