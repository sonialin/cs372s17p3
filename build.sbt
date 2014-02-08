name := "expressions-scala"

version := "0.0.2"

scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked")

resolvers ++= Seq(
  "Sonatype Releases" at "http://oss.sonatype.org/content/repositories/releases"
)

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.10" % "2.0.1-SNAP" % "test",
  "org.scalacheck" %% "scalacheck" % "1.11.1" % "test"
)
