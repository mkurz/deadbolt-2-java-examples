name := "deadbolt-2-usage-java"

version := "2.3.2"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

organization := "be.objectify"

libraryDependencies ++= Seq(
  javaEbean,
  "be.objectify" %% "deadbolt-java" % "2.3.2",
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4"
)

resolvers += Resolver.url("Objectify Play Repository", url("http://schaloner.github.com/releases/"))(Resolver.ivyStylePatterns)
