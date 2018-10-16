import Dependencies._

val sparkVersion = "2.3.0"

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "seyed",
      scalaVersion := "2.11.8",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "insurance-severity-controllers.analyzer",
    libraryDependencies ++= Seq(sparkCore, sparkSql, sparkMl)
  )