name := "StreamingServices"
organization:= "data.engineering.tutorial"
version := "0.1"
scalaVersion := "2.11.12"
val sparkVersion = "2.4.4"
val sparkDependencies = Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.apache.spark" %% "spark-hive" % sparkVersion,
  "org.apache.spark" %% "spark-sql-kafka-0-10" %sparkVersion
)
libraryDependencies ++= sparkDependencies
libraryDependencies += "com.microsoft.sqlserver" % "mssql-jdbc" % "8.2.2.jre8"
libraryDependencies += "org.apache.kafka" %% "kafka" % "2.4.0"
libraryDependencies += "com.typesafe" % "config" % "1.4.0"

