package analyzer

import org.apache.spark.sql.SparkSession
import controllers.Spark

object Calculate extends Spark {
  def main(args: Array[String]): Unit = {
    initiateSession()
  }
}