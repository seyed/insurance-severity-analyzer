import controllers.analyzer.SeverityCalculator

object SparkApp extends SeverityCalculator{

  def main(args: Array[String]): Unit = {
    predicate()
  }
}

