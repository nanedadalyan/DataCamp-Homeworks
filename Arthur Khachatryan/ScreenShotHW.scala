/////////////////////////////////////////////////////////////////////////////////////

import $ivy.`org.apache.spark::spark-sql:2.4.3`
import org.apache.log4j.{Level, Logger}
Logger.getLogger("org").setLevel(Level.OFF)
import org.joda.time.format.DateTimeFormat
import org.joda.time.{DateTime, Days}
import java.util.Properties
import java.sql.{Connection, DriverManager, ResultSet}
import scala.util.Try
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{DataFrame, SparkSession, SQLContext}
import org.apache.spark.sql.SaveMode._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.expressions.Window
import org.json4s._
def sc = spark.sparkContext
//import spark.implicits._
val spark = {
  SparkSession.builder()
    .master("local[*]")
    .getOrCreate()
}


/////////////////////////////////////////////////////////////////////////////////////

var dataframe = spark.read.
      option("header", true).
      option("delimiter", ",").
      option("inferSchema",true).
      csv("beauty.csv")

var df=dataframe
    .select(col("device_id"),col("session_id"),col("user_id"),col("tool"),col("timestamp"))
    .withColumn("MyDate",to_date(col("timestamp")))
    

/////////////////////////////////////////////////////////////////////////////////////

//1. How many times face tool was applied---face_fix

//overall
var overall_face=df.filter(col("tool")==="face_fix")
overall_face.count()

//by users
var face_users=overall_face.groupBy("device_id")
                           .agg(count(col("tool")).as("Count"))
                           .orderBy(col("Count").desc)
face_users.show(false)
face_users.count() //how many users 


/////////////////////////////////////////////////////////////////////////////////////

//2. % change in subscribed users 
var window = Window.orderBy("MyDate")
var lagCol = lag(col("users_perDay"), 1).over(window)

var users_perday=df
  .filter(col("user_id").isNotNull)
  .select("user_id","MyDate")
  .distinct()
  .groupBy("MyDate")
  .agg(countDistinct(col("user_id")).as("users_perDay"))
  .withColumn("lagCol", lagCol)
  .withColumn("%Change", round((col("users_perDay")-col("lagCol"))/col("users_perDay")*100,1))
  
users_perday.show()

/////////////////////////////////////////////////////////////////////////////////////

//3. stickiness?

//total users
var total=df.agg(countDistinct("user_id")).take(1)(0)(0).toString.toDouble

//DAU? 
var UPD=users_perday.agg(avg("users_perDay")).take(1)(0)(0).toString.toDouble

//stickiness
var stick=(UPD/total)*100-(UPD/total)*100 % 0.01//normal round anox function chgta :D


/////////////////////////////////////////////////////////////////////////////////////

//4. engagement?? 
//asenq mi hogi amenor mtnuma 2 gorcoxutyuna anum mi hogiel araji or@ mtel mi 20 gorcoxutyun arela u el chi mtel --ova aveli engaged?
// mi hogu gcov hashvvox tiva te @ndhanur mijina?
// payman dnenq vor amenor aktiv lini ?

var newdf=df
          .groupBy("device_id","MyDate")
          .agg(count("tool").as("Count"))     //amen mi device_id-i eventneri qanak@ orerov                                
          newdf
          .groupBy("device_id")
          .agg(avg("Count").as("daily_user_avg")) //amen mi device_id-i eventneri mijin qanak@ orva ktrvacqov                                      
          .agg(avg("daily_user_avg")).show()   //ev et saxi mijin@


/////////////////////////////////////////////////////////////////////////////////////

//5. most used tool combination

val wind=Window.partitionBy(col("session_id")).orderBy(col("timestamp"))

//max quantity of used tools in one session to kindoff collect list using for loop instead of collect_list --- any good ideas how to do?
var myMax=df.groupBy("session_id").agg(count(col("tool")).as("Count")).agg(max("Count")).take(1)(0)(0).toString.toInt

//using lead in for loop depending on the max quantity of used tools in one session--myMax
var newdf=df.select(col("session_id"),col("timestamp"),col("tool").as("all0"))

var i=0
while(i< myMax-1){
  i=i+1
  newdf=newdf.withColumn("ev",lead(col("all0"),i).over(wind)) //adding lead column
             .withColumn("all"+i,concat_ws(",",col("all"+(i-1)),col("ev"))) // concatenating old column with new(lead) column
             .drop("ev") //dropping new(lead) column
 }

newdf
.withColumn("rank", row_number.over(wind))
.filter(col("rank")===1)  //for every session filter its first timestamp
.drop("session_id","all0","timestamp","rank")
.groupBy("all"+i)
.agg(count("all"+i).as("Count"))
.orderBy("Count")
.show(50,false)

// 
//most used tool is skin_tone -- we also have combination of skin_tone,skin_tone which probably needs to be added
// we also have 24 null values, which are represented as "" because of concat_ws


/////////////////////////////////////////////////////////////////////////////////////
