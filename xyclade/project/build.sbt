scalaVersion := "2.12.1"

libraryDependencies += "org.scala-lang.modules" % "scala-swing_2.12" % "2.0.0-M2"
// libraryDependencies += "org.bytedeco" % "sbt-javacv" % "1.14"


lazy val root = (project in file("./src/main/scala"))
  .settings(
    name := "knn"
  )
