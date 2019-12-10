
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


// number of actions per session
frame.select("session_id").groupBy("session_id").count.orderBy(desc("count")).show

// session duration
frame.select("timestamp","session_id")
.groupBy("session_id")
.agg(min(col("timestamp")).alias("session_start"),max(col("timestamp")).alias("session_end"))
.withColumn("diff_seconds",
            unix_timestamp(col("session_end").cast("timestamp")) - unix_timestamp(col("session_start").cast("timestamp"))).show












