import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"
  lazy val sparkSql = "org.apache.spark" %% "spark-sql" % "2.3.1"
  lazy val sparkMl = "org.apache.spark" %% "spark-mllib" % "2.3.1" % "runtime"

}
