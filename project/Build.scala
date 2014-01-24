import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

    val appName         = "deadbolt-2-usage-java"
    val appVersion      = "2.2-RC4"

    val appDependencies = Seq(
      javaCore,
      javaEbean,
      "be.objectify" %% "deadbolt-java" % "2.2-RC4",
      "postgresql" % "postgresql" % "9.1-901-1.jdbc4"
    )

    val main = play.Project(appName, appVersion, appDependencies).settings(
      // Change this to point to your local play repository
      resolvers += Resolver.url("Objectify Play Repository", url("http://schaloner.github.com/releases/"))(Resolver.ivyStylePatterns),
      resolvers += Resolver.url("Objectify Play Repository - snapshots", url("http://schaloner.github.com/snapshots/"))(Resolver.ivyStylePatterns),
      resolvers += Resolver.url("typesafe", url("http://repo.typesafe.com/typesafe/repo"))
    )

}
