ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "poker"
  )

val Http4sVersion = "0.23.18"
val LogbackVersion = "1.2.11"

libraryDependencies ++= Seq(
  "org.typelevel"   %% "munit-cats-effect-3" % "1.0.7" % Test,
  "org.http4s"      %% "http4s-ember-server" % Http4sVersion,
  "org.http4s"      %% "http4s-ember-client" % Http4sVersion,
  "org.http4s"      %% "http4s-circe"        % Http4sVersion,
  "org.http4s"      %% "http4s-dsl"          % Http4sVersion,
  "ch.qos.logback"  %  "logback-classic"     % LogbackVersion % Runtime,
  "org.tpolecat" %% "doobie-core" % "1.0.0-RC1",
  "org.tpolecat" %% "doobie-postgres" % "1.0.0-RC1",
)
