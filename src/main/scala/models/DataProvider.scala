package models

import org.apache.spark.sql.{Dataset, SparkSession}

case class InsuranceClaimRecord(name: String, filledDate: String, value: Float)

class DataProvider {

  def dataset(filePath: String, spark: SparkSession): Dataset[InsuranceClaimRecord] = {


  }
}