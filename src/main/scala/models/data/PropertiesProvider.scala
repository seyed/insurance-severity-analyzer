package models.data

import org.apache.spark.sql.{DataFrame, SparkSession}

case class InsuranceClaimRecord(name: String, filledDate: String, value: Float)

class PropertiesProvider {

  def trainDataset(trainData: String, spark: SparkSession) = {


    val trainDataframe: DataFrame = spark.read
      .option("header", "true")
      .option("inferSchema", "true")
      .format("com.databricks.spark.csv")
      .load(trainData)
      .withColumnRenamed("loss", "label")
      .cache

    //Creating in memory view of the dataframe
    trainDataframe.createOrReplaceTempView("insurance")

    averageDamageClaim(spark)
    lowestClaim(spark)
    highestClaim(spark)

  }

  def dataframeSchema(dataFrame: DataFrame): Unit = dataFrame.printSchema()
  def numberOfRows(dataFrame: DataFrame): Long = dataFrame.count
  def dataframeSnapShot(dataFrame: DataFrame): Unit = dataFrame.show()

  def averageDamageClaim(spark: SparkSession): Unit =
    spark.sql("SELECT avg(insurance.label) as Average_Lost From insurance").show()

  def lowestClaim(spark: SparkSession): Unit =
    spark.sql("SELECT min(insurance.label) as Minimum_Lost FROM insurance").show()

  def highestClaim(spark: SparkSession): Unit =
    spark.sql("SELECT max(insurance.label) as Maximum_Lost FROM insurance").show()

}