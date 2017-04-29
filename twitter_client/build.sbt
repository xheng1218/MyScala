name := "twitter_client"
description := "Twitter Client examples in Scala"
version := "0.1.0"

scalaVersion := "2.12.2"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"
libraryDependencies ++= Seq(
  "org.twitter4j" % "twitter4j-core" % "4.0.6",
  "org.twitter4j" % "twitter4j-stream" % "4.0.6")
libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-api" % "1.7.25",
  "org.slf4j" % "slf4j-simple" % "1.7.25")
