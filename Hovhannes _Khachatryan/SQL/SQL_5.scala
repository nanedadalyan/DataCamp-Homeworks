
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

//val frame = spark.read.option("header", true).option("delimiter", ",").csv("/home/hovhannes/Downloads/part-00000-02611a1e-96dd-4f4b-809f-14aa2a490389-c000.csv")
val frame1 = frame.select("device_id","timestamp")
.where(col("event_type") === "effect_apply")
.withColumn("Day",to_date(col("timestamp")))               
.drop("timestamp")                                     //selecting device_id and timestamp with days
.groupBy("Day").agg(countDistinct("device_id").alias("daily_effect_apply")).orderBy("Day") // count daily effect apply

val mean = frame1.select(avg("daily_effect_apply").alias("mean"))                    // mean
val Median = frame1.stat.approxQuantile("daily_effect_apply",Array(0.5),0)          // median
val quantiles = frame1.stat.approxQuantile("daily_effect_apply",
           Array(0.25,0.75),0.0)
val Q1 = quantiles(0)
val Q3 = quantiles(1)
val IQR = Q3 - Q1
val lowerRange = Q1 - 1.5*IQR
val upperRange = Q3+ 1.5*IQR

val outliers = frame1.filter(s"daily_effect_apply < $lowerRange or daily_effect_apply > $upperRange") // outliers





