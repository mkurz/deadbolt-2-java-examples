name := "deadbolt-2-usage-java"

version := "2.5.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.7"

organization := "be.objectify"

libraryDependencies ++= Seq(
  evolutions,
  "be.objectify" %% "deadbolt-java" % "2.5.0-SNAPSHOT",
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4"
)

routesGenerator := InjectedRoutesGenerator
