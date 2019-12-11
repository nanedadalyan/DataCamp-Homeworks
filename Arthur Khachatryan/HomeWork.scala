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
val formatter = DateTimeFormat.forPattern("yyyy-MM-dd")
import scala.util.Try
import java.sql.{Connection, DriverManager, ResultSet}

////////////////////////////////////////////////////////////////////////////////////////////////
var dataframe = spark.read.option("header", true).option("delimiter", ",").option("inferSchema",true).csv("users.csv")
var df=dataframe.withColumn("MyDate",to_date(col("timestamp"))).orderBy("device_id","session_id","timestamp")

////////////////////////////////////////////////////////////////////////////////////////////////

//1. Calculate how many users who used app in X day 
// a) returned the next day
// b) returned 7 day after

//df200 -our base dataframe from wich we calculate retention
var df200=df.select("device_id","MyDate").where(col("MyDate").isNotNull).distinct().orderBy("Mydate")
/////////////////////////////////////

// now we get grouped info for which we have to calculate retention
var df20=df.select("device_id","MyDate").groupBy("MyDate").agg(countDistinct("device_id").as("daily_users")).orderBy("Mydate")

// a) returned the next day
df20.withColumn("Ret",retUDF(df20("MyDate"),lit(1))).show()
// b) returned 7 day after
df20.withColumn("Ret",retUDF(df20("MyDate"),lit(7))).show()

// 2) which tool has highest return rate (edit_text_apply, effect_apply, edit_item_open, edit_sticker_apply)
//was taking too long to calculate
/////////////////////////////////////

//retention function veeeery bad took 3-4 min to calculate
def ret(date:String,days:Int):Long={
  //retention function takes date from our column as string, 
  //changes to date, adds specified date, 
  //changes back to string
  var mydate=DateTime.parse(date,formatter)
  var helper=mydate.plusDays(days)
  var nextdate=helper.toString.slice(0,10) 

  //here we filter our base dataframe first time for first date and the for the second, join them and count
  var df2=df200.where(col("MyDate")===date)
  var df3=df200.where(col("MyDate")===nextdate)  
  df2.join(df3, df2.col("device_id")===df3.col("device_id")).count()
  
}
var retUDF=udf[Long,String,Int](ret)

////////////////////////////////////////////////////////////////////////////////////////////////

// 3) how much users did photo_like daily, in which day is it highest, if there is seasonality
var pu_daily=df.
where(col("event_type")==="photo_upload").
groupBy("MyDate").
agg(countDistinct("device_id").as("daily_photo_uploaders")).
orderBy("MyDate").
show()
//i have changed photo-like to photo_upload to have some more data
//highest -2018-02-02, no obviouse seasonality, but whe have decreasing trend



////////////////////////////////////////////////////////////////////////////////////////////////

// 4) what is conversion rate that users who did effect_try then did effect apply
//ardyoq karevora skzbanakan sortavorum@?
val wind=Window.partitionBy(col("device_id"))

var newdf=df.select("device_id","event_type").withColumn("lists",collect_list(col("event_type")).over(wind))
var mydf=newdf.select("device_id","lists").distinct()

  mydf.
     withColumn("effect_try_done",when(array_contains(col("lists"),"effect_try")==="true",lit(1))otherwise(lit(0))).
     withColumn("effect_apply_done",when(array_contains(col("lists"),"effect_apply")==="true",lit(1))otherwise(lit(0)))
     .groupBy("effect_try_done").
     agg(sum("effect_try_done").as("sum_of_ef_try"),sum("effect_apply_done").as("sum_of_ef_app")).
     drop("effect_try_done").
     withColumn("%",round(col("sum_of_ef_app")/col("sum_of_ef_try"),1)*100).
     show()

////////////////////////////////////////////////////////////////////////////////////////////////

// 5) find how many users did effect apply and what is descriptive statistics for it (outliers, avg, median)
var daily=df.
where(col("event_type")==="effect_apply").
groupBy("MyDate").
agg(countDistinct("device_id").as("daily_users"))
.orderBy("daily_users")
daily.show()

daily.select(avg("daily_users").as("avg"),mean("daily_users").as("mean"),sum("daily_users").as("sum")).show()
//or
daily.describe().show()


//what is happening when we take(1)(0)(0)???
var x=daily.select(mean("daily_users")).take(1)(0)(0).toString.toDouble
var Q1=daily.filter(col("daily_users")<=x).select(mean("daily_users")).take(1)(0)(0).toString.toDouble
var Q3=daily.filter(col("daily_users")>=x).select(mean("daily_users")).take(1)(0)(0).toString.toDouble
var IQR=Q3-Q1

var lowout=Q1-1.5*IQR
var upout=Q3+1.5*IQR

daily.select(col("Mydate"),col("daily_users").as("users_of_outlier_day")).
where(col("users_of_outlier_day")<lowout ||col("users_of_outlier_day")>upout).
show()
////////////////////////////////////////////////////////////////////////////////////////////////

// 6)* what first 5 events users do in their session start - 5 events in array and % of users did it
//ardyoq karevora skzbanakan sortavorum@?
val wind=Window.partitionBy(col("session_id")).orderBy(col("timestamp"))
val rankWind = Window.partitionBy(col("session_id")).orderBy(col("timestamp"))


var newdf=df.
select("session_id","timestamp","event_type").
withColumn("ev1",lead(col("event_type"),1).over(wind)).
withColumn("ev2",lead(col("event_type"),2).over(wind)).
withColumn("ev3",lead(col("event_type"),3).over(wind)).
withColumn("ev4",lead(col("event_type"),4).over(wind))
//concati jamanak ete mi value-n null exav syuneri mejic sax concat@ nulla sarqum


//chhaskaca baic inchem are
var mydf=newdf.withColumn("list",array(newdf.columns.map(c => when(col(c).isNotNull && newdf.schema.fieldIndex(c)>1, col(c))):_*)).
drop("event_type","ev1","ev2","ev3","ev4").

//filtrel amen sessioni nuyn timestampi arajinner@
withColumn("rank", row_number.over(rankWind)).
filter(col("rank")===1).
groupBy("list").agg(countDistinct(col("session_id")).as("Count")).
orderBy(col("Count").desc)

// val ex = (x:Column) => array_remove(x,"")
// mydf.withColumn("Cleaned",ex(mydf("list"))).show()
mydf.withColumn("%_total_sessions",round((col("Count")/df.select("session_id").distinct().count())*100,1)).show(70,false)

////////////////////////////////////////////////////////////////////////////////////////////////

