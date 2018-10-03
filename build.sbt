name := "elasticsearch-geo-demo"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.11"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)


libraryDependencies += jdbc
libraryDependencies += "com.adrianhurt" %% "play-bootstrap" % "1.0-P25-B3"

libraryDependencies += "io.netty" % "netty-all" % "4.0.41.Final"
libraryDependencies += "org.elasticsearch.client" % "transport" % "5.4.0"
libraryDependencies += "org.apache.logging.log4j" % "log4j-core" % "2.8.1"
libraryDependencies += "org.apache.logging.log4j" % "log4j-api" % "2.8.1"
