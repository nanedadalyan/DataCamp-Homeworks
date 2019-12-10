
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

val frame = spark.read.option("header", true).option("delimiter", ",").csv("/home/hovhannes/Downloads/part-00000-02611a1e-96dd-4f4b-809f-14aa2a490389-c000.csv")
val frame1 = frame.select("device_id","timestamp")
.where(col("event_type") === "photo_like")
.withColumn("Day",to_date(col("timestamp")))               
.drop("timestamp")                                     //selecting device_id and timestamp with days
.groupBy("Day").agg(countDistinct("device_id").alias("daily_likes")).orderBy("Day")

frame1.select(max("daily_likes").alias("max_daily_likes")).show


