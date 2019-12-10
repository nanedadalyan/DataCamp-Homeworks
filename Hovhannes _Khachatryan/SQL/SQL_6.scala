
import $ivy.`org.apache.spark::spark-sql:2.4.3` // Or use any other 2.x version here

// import $ivy.`sh.almond::almond-spark:_` // Added automatically on importing Spark



import org.apache.spark.sql._

import org.apache.log4j.{Level, Logger}

Logger.getLogger("org").setLevel(Level.OFF)
val spark = {

  NotebookSparkSession.builder()

    .master("local[*]")

    .getOrCreate()

}

import org.apache.spark.sql.functions._
import org.apache.spark.sql.expressions.Window

val frame = spark.read.option("header", true).option("delimiter", ",").csv("/home/hovhannes/Downloads/picsart.csv")


frame.select("device_id","session_id","event_type")
.groupBy("device_id","session_id")
.agg(collect_list("event_type").alias("all_events"))
.withColumn("first_5_events",slice(col("all_events"),1,5))
.groupBy("first_5_events")
.agg(count("first_5_events").alias("count"))
.orderBy(desc("count")).show()
