
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
def calculate_mean_retention_rate(tool:String):Any = {

    val frame1 = frame.select("device_id","timestamp")
.withColumn("Week",weekofyear(to_date(col("timestamp"))))                   // select all users grouped by week
.drop("timestamp").groupBy("Week").agg(collect_set("device_id").alias("Users_without_tool"))

    val frame2 = frame.select("device_id","timestamp")
.withColumn("Week",weekofyear(to_date(col("timestamp"))))               
.drop("timestamp")
.where(col("event_type") === tool)                                         // select all users who used tool grouped by week
.groupBy("Week").agg(collect_set("device_id").alias("Users_with_tool"))

    val frame3 = frame2.join(frame1,"Week").orderBy("Week")          // joining two tables

    frame3
.withColumn("next_week_users",lag("Users_without_tool",-1,null).over(Window.orderBy("Week"))) 
.drop("Users_without_tool")                 // generate lag(-1) of all users to match with users who used tool last week 
.withColumn("match_size",size(array_intersect(col("Users_with_tool"),col("next_week_users"))))
.withColumn("Users_size",size(col("Users_with_tool")))   // calculate size of matched array
.drop("Users_with_tool","next_week_users")
.withColumn("retention_rate", (col("match_size") / col("Users_size")) * 100) // calculate ratio of users who used the tool this week to users who are active next week
.drop("Week","match_size","Users_size")
.filter(col("retention_rate") >= 0)
.select(avg("retention_rate").as("mean_retention_rate")).show
}
// The highest retention_rate has edit_sticker_apply with 1.68 %. 

calculate_mean_retention_rate("effect_apply")
