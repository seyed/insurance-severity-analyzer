package models

import org.apache.spark
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

case class InsuranceClaimRecord(name: String, filledDate: String, value: Float)

class DataProvider {

  def trainDataset(trainData: String, spark: SparkSession) = {
    import spark.implicits._

    val trainDataframe: DataFrame = spark.read
      .option("header", "true")
      .option("inferSchema", "true")
      .format("com.databricks.spark.csv")
      .load(trainData)
      .withColumnRenamed("loss", "label")
      .cache


    println("Train data frame Schema ${dataframeSchema(trainDataframe)}")
    println(s"Number of rows: ${numberOfRows(trainDataframe)}")
    println(s"Dataframe snapshot:  \n ${dataframeSnapShot(trainDataframe)}")

    averageDamageClaim(trainDataframe, spark)

  }

  def dataframeSchema(dataFrame: DataFrame): Unit = dataFrame.printSchema()
  def numberOfRows(dataFrame: DataFrame): Long = dataFrame.count
  def dataframeSnapShot(dataFrame: DataFrame): Unit = dataFrame.show()

  def averageDamageClaim(df: DataFrame, spark: SparkSession): Unit = {
    df.createOrReplaceTempView("insurance")
    spark.sql("SELECT avg(insurance.label) as Average_Lost From insurance").show()
  }


}