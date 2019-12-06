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

val spark = SparkSession.builder().appName("test").getOrCreate()

val frame = spark.read.option("header", true).option("delimiter", ",").option("inferSchema",true).csv("/Users/training/Downloads/train.csv")

import org.apache.spark.sql.functions.{col, lit, when}



import org.joda.time.format.DateTimeFormat
import org.joda.time.{DateTime, Days}
import org.joda.time.format.DateTimeFormat
import java.util.Properties
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.{DataFrame, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}
import org.joda.time.{DateTime, Days}
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.SaveMode._
import org.apache.spark.sql.expressions.Window
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.joda.time.format.DateTimeFormat
import org.joda.time.{DateTime, Days}
val formatter = DateTimeFormat.forPattern("yyyy-MM-dd")
import scala.util.Try
import java.sql.{Connection, DriverManager, ResultSet}



import org.apache.spark.sql.functions._















// 1)
val frame = spark.read.option("header", true).option("delimiter", ",").option("inferSchema",true).csv("/Users/training/Downloads/part-00000-02611a1e-96dd-4f4b-809f-14aa2a490389-c000.csv")
val df = frame.select("timestamp","device_id")
var df1 = df.withColumn("Date", to_date(col("timestamp")))
df1 = df1.drop(col("timestamp"))
val df2 = df1.groupBy("Date").agg("device_id" -> "count").orderBy("Date")
df2
.withColumn("month",month(col("Date"))).groupBy(col("month")).agg("count(device_id)" -> "avg").show


// 2)
var df1 = df.withColumn("Date", to_date(col("timestamp")))
df1 = df1.drop(col("timestamp"))
val df2 = df1.groupBy("Date").agg("device_id" -> "count").orderBy("Date")
df2
.withColumn("month",month(col("Date"))).groupBy(col("month")).agg("count(device_id)" -> "avg").show




// 2b)
df
.withColumn("week",weekofyear(col("timestamp"))).groupBy("week").agg(countDistinct("device_id")).show

// 3)
frame
.select("country_code","event_type","platform","device_id")
.where(col("event_type")
       .isin("photo_open","photo_like","photo_comment"))
.groupBy("country_code").agg(count("country_code")).orderBy(desc("count(country_code)")).show


/*frame
.select("country_code","event_type","platform","device_id")
.where(col("event_type")
       .isin("photo_open","photo_like","photo_comment"))
.groupBy("platform").agg(count("platform")).orderBy(desc("count(platform)")).show*/




