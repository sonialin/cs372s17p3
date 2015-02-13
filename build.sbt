name := "expressions-scala"

version := "0.1"

scalaVersion := "2.11.5"

scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked")

libraryDependencies ++= Seq(
  "org.parboiled" %% "parboiled" % "2.0.1",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "org.scalacheck" %% "scalacheck" % "1.12.2" % "test"
)
