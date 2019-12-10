
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


def returnday(day:Int): Any ={
frame.select("device_id","timestamp")
.withColumn("Day",to_date(col("timestamp")))               
.drop("timestamp")                                     //selecting device_id and timestamp with days
.groupBy("Day")
.agg(collect_set("device_id").as("today_users"))        // collecting all unique daily users in a set
.withColumn("tomorrow_users",lag("today_users",day,null).over(Window.orderBy("Day"))) // creating lag variable 
.withColumn("match",array_intersect(col("today_users"),col("tomorrow_users"))) // matching today users with tomorrow users
.withColumn("returns",size(col("match"))).drop("today_users","tomorrow_users","match").show() // counting users
}
// you can choose any day














