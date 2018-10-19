package models.data

import org.apache.spark.ml.feature.{StringIndexer, VectorAssembler}
import org.apache.spark.sql.{DataFrame, SparkSession}

trait ProcessingProvider{
  def preProcessing(df: DataFrame, spark: SparkSession) : Unit
}

class ProcessingProviderViaFiles extends ProcessingProvider {

  def preProcessing(df: DataFrame, spark: SparkSession): Unit = {
    import spark.implicits._

    //Dropping rows containing any null values
    val noNullDf = df.na.drop()
    val seed = 12345L
    val splits = noNullDf.randomSplit(Array(0.75, 0.25), seed)

    val trainSample = 1.0
    val testSample = 1.0
    val traininingData = "train.csv"
    val test = "test.csv"

    println("INFO: reading the train data")

    val trainInput = spark.read
      .option("header", "true")
      .option("inferSchema", "true")
      .format("com.databricks.spark.csv")
      .load(traininingData)
      .cache

    val testInput = spark.read
      .option("header", "true")
      .option("inferSchema", "true")
      .format("com.databricks.spark.csv")
      .load(test)
      .cache

    println("INFO: preparing data for training model")
    var data = trainInput.withColumnRenamed("loss", "label").sample(withReplacement = false, trainSample)

    val (trainingData, validationData) = (splits(0), splits(1))
    trainingData.cache
    validationData.cache

    //Sampling
    val testData = testInput.sample(withReplacement = false, testSample).cache

    //Construct the definitive set of feature columns
    val featureColumns = trainingData.columns
      .filter(removeTooManyCategs)
      .filter(onlyFeatureCols)
      .map(categNewCol)

    //Use StringIndexer for categorical columns
    val stringIndexerStages = trainingData.columns.filter(isCateg)
      .map(column => new StringIndexer()
        .setInputCol(column)
        .setOutputCol(categNewCol(column))
        .fit(trainInput.select(column).union(testInput.select(column))))

    //List of columns => single vector column
    val assemble = new VectorAssembler().setInputCols(featureColumns).setOutputCol("features")
  }

  //Category identifications
  private def isCateg(category: String): Boolean = category.startsWith("cat")

  private def categNewCol(category: String): String = if (isCateg(category)) s"idx_$category" else category

  //Remove categorical items, when there are too many categories [check the data properties for why we used these #s]
  private def removeTooManyCategs(category: String): Boolean = !(category matches "cat(109$|110$|112$|113$|116$)")

  //Remove id, as it is not necessary
  private def onlyFeatureCols(column: String): Boolean = !(column matches "id|label")

}