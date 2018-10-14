package models

import org.apache.spark.sql.{Dataset, SparkSession}

case class InsuranceClaimRecord(name: String, filledDate: String, value: Float)

class DataProvider {

  def trainDataset(trainData: String, spark: SparkSession) = {
    import spark.implicits._

    val input = spark.read
      .option("header", "true")
      .option("inferSchema", "true")
      .format("com.databricks.spark.csv")
      .load(trainData)
      .cache

    println(input.printSchema())

  }
}