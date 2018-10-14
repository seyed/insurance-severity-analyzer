

Description
-----------

This project calculates the severity claim. 

The dataset is from here: https://www.kaggle.com/c/allstate-claims-severity/data 


How to run it?
--------------
+ You need to use `spark-submit`. So you need ot have the Apache spark in your 
machine (the `spark-submit` is located in its `\bin` folder). 

+ Run `sbt package` in the project parent directory

+ Go to the `/target` folder and run the following command: 
`spark-submit --class "SparkApp --master local[4] insurance-severity-controllers-analyzer_2.11-0.1.0-SNAPSHOT.jar` 