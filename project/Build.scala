import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

    val appName         = "deadbolt-2-usage-java"
    val appVersion      = "2.2.0"

    val appDependencies = Seq(
      javaCore,
      javaEbean,
      "be.objectify" %% "deadbolt-java" % "2.2.1",
      "postgresql" % "postgresql" % "9.1-901-1.jdbc4"
    )

    val main = play.Project(appName, appVersion, appDependencies).settings(
      resolvers += Resolver.url("typesafe", url("http://repo.typesafe.com/typesafe/repo"))
    )

}
