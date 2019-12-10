
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


import scala.collection.mutable.WrappedArray
def count(arr: WrappedArray[String]):Int={arr.count(_=="effect_apply")}
val countUDF=udf(count(_:WrappedArray[String]))

val df = frame.orderBy("timestamp")
.filter(col("event_type").isin("effect_try","effect_apply"))
.select("device_id","session_id","event_type")
.groupBy("device_id","session_id")
.agg(collect_list("event_type").alias("all"))  // collect all events in a list
.withColumn("size",size(col("all")))           // count size of a list
.withColumn("count_effect_apply",countUDF(col("all")))   // count number of effect_apply
.withColumn("ratio",round(col("count_effect_apply").divide(col("size")) * 100))  // get the conv_rate in each session of users
.groupBy("device_id").agg(avg(col("ratio")).alias("conv_rate_by_device_id")).show // get the conv_rate for each user












