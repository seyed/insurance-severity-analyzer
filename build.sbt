import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "seyed",
      scalaVersion := "2.11.8",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "insurance-severity-analyzer",
    libraryDependencies ++= Seq(sparkSql, scalaTest % Test)
  )

