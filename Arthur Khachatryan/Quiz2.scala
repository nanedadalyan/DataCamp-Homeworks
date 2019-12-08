import $ivy.`org.apache.spark::spark-sql:2.4.3`
import org.apache.spark.sql._
import org.apache.log4j.{Level, Logger}
Logger.getLogger("org").setLevel(Level.OFF)
def sc = spark.sparkContext
//import spark.implicits._
val spark = {
  SparkSession.builder()
    .master("local[*]")
    .getOrCreate()
}
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
import org.apache.spark.sql.functions._
import org.apache.spark.sql.expressions.Window
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.joda.time.format.DateTimeFormat
import org.joda.time.{DateTime, Days}

import scala.util.Try
import java.sql.{Connection, DriverManager, ResultSet}
val formatter = DateTimeFormat.forPattern("yyyy-MM-dd")

//////////////////////////////////////////////////////////////////////////////

var dataframe = spark.read.option("header", true).option("delimiter", ",").option("inferSchema",true).csv("HW.csv")

var df=dataframe.
  withColumn("MyDate",to_date(col("timestamp"),"yyyy-MM-dd")).
  withColumn("WeekOfYear",weekofyear(col("timestamp"))).
  withColumn("Month",month(col("timestamp")))

//////////////////////////////////////////////////////////////////////////////

//1. Calculate first 10 event after editor_open (hint:lag lead window functions)

// var dfdf=df.groupBy("event_type").agg(count("event_type").as("Count")).orderBy(col("Count").desc)
// ///hl@ vor aranc function
// var window = Window.orderBy(col("Count"))
// var lagCol = lag(col("event_type"), 1).over(window)
// var lagCol2 = lag(col("event_type"), 2).over(window)
// var lagCol3= lag(col("event_type"), 3).over(window)
// var lagCol4 = lag(col("event_type"), 4).over(window)
// var lagCol5 = lag(col("event_type"), 5).over(window)

// var newdf=dfdf.select("*").
// withColumn("lagCol", lagCol).
// withColumn("lagCol2", lagCol2).
// withColumn("lagCol3", lagCol3).
// withColumn("lagCol4", lagCol4).
// withColumn("lagCol5", lagCol5).
// where(col("event_type")==="editor_open").
// show()

//method 2
def firstN(n:Int,myCol:String,evType:String,df:DataFrame):Unit={
  var num =df.where(col(myCol)===evType).count()
  var newdf=df.groupBy(col(myCol)).agg(count(col(myCol)).as("Count")).orderBy(col("Count").desc)
  dfdf.select("*").where(col("Count")<num).limit(n).show(false)  
}
firstN(10,"event_type","editor_open",df)

//////////////////////////////////////////////////////////////////////////////
//2. Calculate daily active users trend over month
var df1=df.
  select("user_id","Month","MyDate").
  distinct().
  groupBy("Month").
  agg(count(col("user_id")).as("users_perMonth"),countDistinct(col("MyDate")).as("days_perMonth")).
  sort(col("Month")).
  withColumn("AverageMonthlyUsers",col("users_perMonth")/col("days_perMonth")).
  show()
  
//////////////////////////////////////////////////////////////////////////////
  //3. Calculate weekly seasonality
var df2=df.select("user_id","WeekOfYear").
  distinct().
  groupBy("WeekOfYear").
  agg(count(col("user_id")).as("users_perWeek")).
  sort(col("WeekOfYear"))

var window = Window.orderBy("WeekOfYear")
var lagCol = lag(col("users_perWeek"), 1).over(window)
var newdf=df2.withColumn("lagCol", lagCol)

var trend = newdf.
  withColumn("Trend",
  when(col("lagCol").isNull, lit("Note enough info"))       
  when(col("users_perWeek")>col("lagCol"), lit("Increase"))
  when(col("users_perWeek")<col("lagCol"), lit("Decrease"))
  otherwise(lit("No change"))).show()

 //////////////////////////////////////////////////////////////////////////////
  
  //5.calculate editor_open -> editor_done_click -> photo_upload funnel

var mydf=df.select("device_id","event_type","timestamp").
where(col("event_type").isin("editor_open","editor_done_click","photo_upload")).
orderBy(col("device_id"),col("timestamp"),col("event_type"))

////method 1 
var df3=mydf.withColumn("Lists", collect_list("event_type").
                        over(Window.partitionBy("device_id").
                             orderBy("timestamp")))
               
//distincti xndir ka?
df3.groupBy("Lists").agg(count(col("Lists"))).show(false)

//editor_open-i tiv@ chi brnum prosto filtrac editor_open-i het!
var df_edit=mydf.where(col("event_type")==="editor_open").count()

  
//////////////////////////////////////////////////////////////////////////////
  
//7. Calculate D7 retention of editor_open  kisata
var df200=df.
      select("device_id","MyDate").
      distinct().
      where(col("event_type")==="editor_open")//es pahin hl@ tox dzerqov @ntrenq event_type-@

def myfunc(date:String,days:Int,df:DataFrame):Long={
 
  var mydate=DateTime.parse(date,formatter)
  var helper=mydate.plusDays(days)
  var nextdate=helper.toString.slice(0,10)
  
  var df2=df.where(col("MyDate")===date)
  var df3=df.where(col("MyDate")===nextdate)
  df2.join(df3, df2.col("device_id")===df3.col("device_id")).count()
}
myfunc("2018-02-02",7,df200)  

//////////////////////////////////////////////////////////////////////////////