name := "SoccerLeague"
organization := "com.soccerleague"
version := "0.0.1"

scalaVersion := "2.12.1"
dependencyOverrides += "org.scala-lang" % "scala-library" % scalaVersion.value


lazy val soccerApp = (project in file(".")).settings(
  libraryDependencies ++= Seq("org.scala-lang" % "scala-reflect" % scalaVersion.value,
    "org.scalatest" % "scalatest_2.12" % "3.0.1"),
  mainClass in assembly := Some("com.soccerleague.Main"),
  assemblyJarName in assembly := "soccer-league.jar"
)