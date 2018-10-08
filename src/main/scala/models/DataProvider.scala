package models

import org.apache.spark.sql.{Dataset, SparkSession}

case class InsuranceClaimRecord()

class DataProvider {

  def dataset(filePath: String, spark: SparkSession): Dataset[InsuranceClaimRecord] = {


  }
}