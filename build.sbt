name := "Lagom_Akka_streaming"

version := "0.1"

scalaVersion := "2.13.1"

val macwire = "com.softwaremill.macwire" %% "macros" % "2.3.3" % "provided"

lazy val `lagom-akka-streaming` = (project in file("."))
  .aggregate(`lagom-akka-stream-api`, `lagom-akka-stream-impl`)

lazy val `lagom-akka-stream-api` = (project in file("lagom-akka-stream-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `lagom-akka-stream-impl` = (project in file("lagom-akka-stream-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceCassandra,
      lagomScaladslKafkaBroker,
      lagomScaladslTestKit,
      macwire
    )
  )
  .settings(lagomForkedTestSettings)
  .dependsOn(`lagom-akka-stream-api`)