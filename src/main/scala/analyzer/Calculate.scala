package analyzer


import org.apache.spark.sql.SparkSession


object Calculate {

  def main(args: Array[String]): Unit = {
    sparkSession()
  }

  def sparkSession(): SparkSession = SparkSession.builder()
    .master("local")
    .appName("Analyzer")
    .getOrCreate()

}