name := "expressions-scala"

version := "0.1"

scalaVersion := "2.11.7"

scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked")

libraryDependencies ++= Seq(
  "org.parboiled" %% "parboiled" % "2.1.0",
  "org.scalatest" %% "scalatest" % "2.2.6" % Test
)
