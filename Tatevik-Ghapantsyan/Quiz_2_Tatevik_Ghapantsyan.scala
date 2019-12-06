import $ivy.`org.apache.spark::spark-sql:2.4.3` // Or use any other 2.x version here
// import $ivy.`sh.almond::almond-spark:_` // Added automatically on importing Spark

import org.apache.spark.sql._

import org.apache.log4j.{Level, Logger}
Logger.getLogger("org").setLevel(Level.OFF)

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

val spark = {
  SparkSession.builder()
    .master("local[*]")
    .getOrCreate()
}

def sc = spark.sparkContext

var df = spark.read.format("csv").option("inferSchema", "true").option("header", "true").load("Data.csv")

df.printSchema

// TASK 1 Calculate first 10 event after editor_open (hint:lag lead window functions)

// sort data by id and date. lag 10 times and save those in 10 new columns and filter out nulls
// after 10 lags, filter only the "editor_open" lines and implement collectList.
// count the number of each event and take top 10 events

val top10 = df.select("device_id", "timestamp", "event_type").sort("device_id", "timestamp").toDF()

top10.agg(
    countDistinct("device_id")).take(1)(0)

top10.registerTempTable("table")
val top10_01 = spark.sql("select device_id, timestamp, event_type, lag(event_type, 1) over (partition by device_id order by timestamp) as lag_1 from table")
top10_01.show(10)
                                                                                                                                                                        


top10_01.registerTempTable("table1")
val top10_02 = (spark.sql("select device_id, timestamp, event_type, lag_1, lag(lag_1, 1) over (partition by device_id order by timestamp) as lag_2 from table1")).filter(col("lag_2").isNotNull)
top10_02.show(10)




top10_02.registerTempTable("table2")
val top10_03 = (spark.sql("select device_id, timestamp, event_type, lag_1, lag_2, lag(lag_2, 1) over (partition by device_id order by timestamp) as lag_3 from table2")).filter(col("lag_3").isNotNull)
top10_03.show(10)
















