name := "medium-scala-client"
description := "Scala client for Medium.com REST API"
version := "0.1.0"

scalaVersion := "2.12.2"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"
libraryDependencies += "com.squareup.okhttp3" % "okhttp" % "3.0.1"
libraryDependencies += "io.spray" %% "spray-json" % "1.3.3"
libraryDependencies += "com.squareup.okhttp3" % "mockwebserver" % "3.7.0" % "test"
