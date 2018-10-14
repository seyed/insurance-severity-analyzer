package analyzer

import controllers.Spark
import models.DataProvider

class SeverityCalculator extends Spark with {
  def predicate() {
    val sparkSession = initiateSession()
    //Get the data from Kaggle (check README.md for URI)
    val trainDataPath = "~/Documents/Development/insurance_train.csv"
    val dataProvider: DataProvider = new DataProvider

    dataProvider.trainDataset(trainDataPath, sparkSession )
  }
}

