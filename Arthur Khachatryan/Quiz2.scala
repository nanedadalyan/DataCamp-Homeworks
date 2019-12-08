{
 "cells": [
  {
   "cell_type": "code",
   "execution_count": 1,
   "metadata": {},
   "outputs": [
    {
     "name": "stderr",
     "output_type": "stream",
     "text": [
      "Using Spark's default log4j profile: org/apache/spark/log4j-defaults.properties\n"
     ]
    },
    {
     "data": {
      "text/plain": [
       "\u001b[32mimport \u001b[39m\u001b[36m$ivy.$                                  \n",
       "\u001b[39m\n",
       "\u001b[32mimport \u001b[39m\u001b[36morg.apache.spark.sql._\n",
       "\u001b[39m\n",
       "\u001b[32mimport \u001b[39m\u001b[36morg.apache.log4j.{Level, Logger}\n",
       "\u001b[39m\n",
       "defined \u001b[32mfunction\u001b[39m \u001b[36msc\u001b[39m\n",
       "\u001b[36mspark\u001b[39m: \u001b[32mSparkSession\u001b[39m = org.apache.spark.sql.SparkSession@2c73de55\n",
       "\u001b[32mimport \u001b[39m\u001b[36morg.joda.time.format.DateTimeFormat\n",
       "\u001b[39m\n",
       "\u001b[32mimport \u001b[39m\u001b[36morg.joda.time.{DateTime, Days}\n",
       "\u001b[39m\n",
       "\u001b[32mimport \u001b[39m\u001b[36morg.joda.time.format.DateTimeFormat\n",
       "\u001b[39m\n",
       "\u001b[32mimport \u001b[39m\u001b[36mjava.util.Properties\n",
       "\u001b[39m\n",
       "\u001b[32mimport \u001b[39m\u001b[36morg.apache.spark.sql.DataFrame\n",
       "\u001b[39m\n",
       "\u001b[32mimport \u001b[39m\u001b[36morg.apache.spark.sql.{DataFrame, SQLContext}\n",
       "\u001b[39m\n",
       "\u001b[32mimport \u001b[39m\u001b[36morg.apache.spark.{SparkConf, SparkContext}\n",
       "\u001b[39m\n",
       "\u001b[32mimport \u001b[39m\u001b[36morg.joda.time.{DateTime, Days}\n",
       "\u001b[39m\n",
       "\u001b[32mimport \u001b[39m\u001b[36morg.apache.spark.sql.{DataFrame, SparkSession}\n",
       "\u001b[39m\n",
       "\u001b[32mimport \u001b[39m\u001b[36morg.apache.spark.sql.SaveMode._\n",
       "\u001b[39m\n",
       "\u001b[32mimport \u001b[39m\u001b[36morg.apache.spark.sql.functions._\n",
       "\u001b[39m\n",
       "\u001b[32mimport \u001b[39m\u001b[36morg.apache.spark.sql.expressions.Window\n",
       "\u001b[39m\n",
       "\u001b[32mimport \u001b[39m\u001b[36morg.apache.spark.SparkConf\n",
       "\u001b[39m\n",
       "\u001b[32mimport \u001b[39m\u001b[36morg.apache.spark.sql.SparkSession\n",
       "\u001b[39m\n",
       "\u001b[32mimport \u001b[39m\u001b[36morg.json4s._\n",
       "\u001b[39m\n",
       "\u001b[32mimport \u001b[39m\u001b[36morg.json4s.jackson.JsonMethods._\n",
       "\u001b[39m\n",
       "\u001b[32mimport \u001b[39m\u001b[36morg.joda.time.format.DateTimeFormat\n",
       "\u001b[39m\n",
       "\u001b[32mimport \u001b[39m\u001b[36morg.joda.time.{DateTime, Days}\n",
       "\n",
       "\u001b[39m\n",
       "\u001b[32mimport \u001b[39m\u001b[36mscala.util.Try\n",
       "\u001b[39m\n",
       "\u001b[32mimport \u001b[39m\u001b[36mjava.sql.{Connection, DriverManager, ResultSet}\n",
       "\u001b[39m\n",
       "\u001b[36mformatter\u001b[39m: \u001b[32morg\u001b[39m.\u001b[32mjoda\u001b[39m.\u001b[32mtime\u001b[39m.\u001b[32mformat\u001b[39m.\u001b[32mDateTimeFormatter\u001b[39m = org.joda.time.format.DateTimeFormatter@24bbaa3c"
      ]
     },
     "execution_count": 1,
     "metadata": {},
     "output_type": "execute_result"
    }
   ],
   "source": [
    "import $ivy.`org.apache.spark::spark-sql:2.4.3`\n",
    "import org.apache.spark.sql._\n",
    "import org.apache.log4j.{Level, Logger}\n",
    "Logger.getLogger(\"org\").setLevel(Level.OFF)\n",
    "def sc = spark.sparkContext\n",
    "//import spark.implicits._\n",
    "val spark = {\n",
    "  SparkSession.builder()\n",
    "    .master(\"local[*]\")\n",
    "    .getOrCreate()\n",
    "}\n",
    "import org.joda.time.format.DateTimeFormat\n",
    "import org.joda.time.{DateTime, Days}\n",
    "import org.joda.time.format.DateTimeFormat\n",
    "import java.util.Properties\n",
    "import org.apache.spark.sql.DataFrame\n",
    "import org.apache.spark.sql.{DataFrame, SQLContext}\n",
    "import org.apache.spark.{SparkConf, SparkContext}\n",
    "import org.joda.time.{DateTime, Days}\n",
    "import org.apache.spark.sql.{DataFrame, SparkSession}\n",
    "import org.apache.spark.sql.SaveMode._\n",
    "import org.apache.spark.sql.functions._\n",
    "import org.apache.spark.sql.expressions.Window\n",
    "import org.apache.spark.SparkConf\n",
    "import org.apache.spark.sql.SparkSession\n",
    "import org.json4s._\n",
    "import org.json4s.jackson.JsonMethods._\n",
    "import org.joda.time.format.DateTimeFormat\n",
    "import org.joda.time.{DateTime, Days}\n",
    "\n",
    "import scala.util.Try\n",
    "import java.sql.{Connection, DriverManager, ResultSet}\n",
    "val formatter = DateTimeFormat.forPattern(\"yyyy-MM-dd\")\n"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 9,
   "metadata": {},
   "outputs": [
    {
     "data": {
      "text/plain": [
       "\u001b[36mdataframe\u001b[39m: \u001b[32mDataFrame\u001b[39m = [country_code: string, device_id: string ... 12 more fields]"
      ]
     },
     "execution_count": 9,
     "metadata": {},
     "output_type": "execute_result"
    }
   ],
   "source": [
    "var dataframe = spark.read.option(\"header\", true).option(\"delimiter\", \",\").option(\"inferSchema\",true).csv(\"HW.csv\")"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 10,
   "metadata": {},
   "outputs": [
    {
     "data": {
      "text/plain": [
       "\u001b[36mdf\u001b[39m: \u001b[32mDataFrame\u001b[39m = [country_code: string, device_id: string ... 15 more fields]"
      ]
     },
     "execution_count": 10,
     "metadata": {},
     "output_type": "execute_result"
    }
   ],
   "source": [
    "var df=dataframe.\n",
    "  withColumn(\"MyDate\",to_date(col(\"timestamp\"),\"yyyy-MM-dd\")).\n",
    "  withColumn(\"WeekOfYear\",weekofyear(col(\"timestamp\"))).\n",
    "  withColumn(\"Month\",month(col(\"timestamp\")))\n",
    "\n"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": null,
   "metadata": {},
   "outputs": [],
   "source": [
    "///////////////////////////////////////////////////////////////////////////////////////////////////"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 14,
   "metadata": {},
   "outputs": [
    {
     "name": "stdout",
     "output_type": "stream",
     "text": [
      "+--------------------------+-----+\n",
      "|event_type                |Count|\n",
      "+--------------------------+-----+\n",
      "|explore_open              |9407 |\n",
      "|photo_chooser_album_open  |9085 |\n",
      "|experiment_participate    |7710 |\n",
      "|app_load                  |7633 |\n",
      "|editor_done               |7235 |\n",
      "|app_open                  |7216 |\n",
      "|tags_widget_shown         |7128 |\n",
      "|draw_undo                 |6720 |\n",
      "|rewarded_video_ad_response|6316 |\n",
      "|tabbar_click              |6129 |\n",
      "+--------------------------+-----+\n",
      "\n"
     ]
    },
    {
     "data": {
      "text/plain": [
       "defined \u001b[32mfunction\u001b[39m \u001b[36mfirstN\u001b[39m"
      ]
     },
     "execution_count": 14,
     "metadata": {},
     "output_type": "execute_result"
    }
   ],
   "source": [
    "//1. Calculate first 10 event after editor_open (hint:lag lead window functions)\n",
    "\n",
    "// var dfdf=df.groupBy(\"event_type\").agg(count(\"event_type\").as(\"Count\")).orderBy(col(\"Count\").desc)\n",
    "// ///hl@ vor aranc function\n",
    "// var window = Window.orderBy(col(\"Count\"))\n",
    "// var lagCol = lag(col(\"event_type\"), 1).over(window)\n",
    "// var lagCol2 = lag(col(\"event_type\"), 2).over(window)\n",
    "// var lagCol3= lag(col(\"event_type\"), 3).over(window)\n",
    "// var lagCol4 = lag(col(\"event_type\"), 4).over(window)\n",
    "// var lagCol5 = lag(col(\"event_type\"), 5).over(window)\n",
    "\n",
    "// var newdf=dfdf.select(\"*\").\n",
    "// withColumn(\"lagCol\", lagCol).\n",
    "// withColumn(\"lagCol2\", lagCol2).\n",
    "// withColumn(\"lagCol3\", lagCol3).\n",
    "// withColumn(\"lagCol4\", lagCol4).\n",
    "// withColumn(\"lagCol5\", lagCol5).\n",
    "// where(col(\"event_type\")===\"editor_open\").\n",
    "// show()\n",
    "\n",
    "//method 2\n",
    "def firstN(n:Int,myCol:String,evType:String,df:DataFrame):Unit={\n",
    "  var num =df.where(col(myCol)===evType).count()\n",
    "  var newdf=df.groupBy(col(myCol)).agg(count(col(myCol)).as(\"Count\")).orderBy(col(\"Count\").desc)\n",
    "  dfdf.select(\"*\").where(col(\"Count\")<num).limit(n).show(false)  \n",
    "}\n",
    "firstN(10,\"event_type\",\"editor_open\",df)"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 15,
   "metadata": {},
   "outputs": [
    {
     "name": "stdout",
     "output_type": "stream",
     "text": [
      "+-----+--------------+-------------+-------------------+\n",
      "|Month|users_perMonth|days_perMonth|AverageMonthlyUsers|\n",
      "+-----+--------------+-------------+-------------------+\n",
      "|    2|        425861|           27|  15772.62962962963|\n",
      "|    3|         31692|            2|            15846.0|\n",
      "+-----+--------------+-------------+-------------------+\n",
      "\n"
     ]
    }
   ],
   "source": [
    "//2. Calculate daily active users trend over month\n",
    "var df1=df.\n",
    "  select(\"user_id\",\"Month\",\"MyDate\").\n",
    "  distinct().\n",
    "  groupBy(\"Month\").\n",
    "  agg(count(col(\"user_id\")).as(\"users_perMonth\"),countDistinct(col(\"MyDate\")).as(\"days_perMonth\")).\n",
    "  sort(col(\"Month\")).\n",
    "  withColumn(\"AverageMonthlyUsers\",col(\"users_perMonth\")/col(\"days_perMonth\")).\n",
    "  show()"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 16,
   "metadata": {},
   "outputs": [
    {
     "name": "stdout",
     "output_type": "stream",
     "text": [
      "+----------+-------------+------+----------------+\n",
      "|WeekOfYear|users_perWeek|lagCol|           Trend|\n",
      "+----------+-------------+------+----------------+\n",
      "|         5|        48205|  null|Note enough info|\n",
      "|         6|       105831| 48205|        Increase|\n",
      "|         7|       109958|105831|        Increase|\n",
      "|         8|       111687|109958|        Increase|\n",
      "|         9|        79069|111687|        Decrease|\n",
      "+----------+-------------+------+----------------+\n",
      "\n"
     ]
    },
    {
     "data": {
      "text/plain": [
       "\u001b[36mdf2\u001b[39m: \u001b[32mDataset\u001b[39m[\u001b[32mRow\u001b[39m] = [WeekOfYear: int, users_perWeek: bigint]\n",
       "\u001b[36mwindow\u001b[39m: \u001b[32mexpressions\u001b[39m.\u001b[32mWindowSpec\u001b[39m = org.apache.spark.sql.expressions.WindowSpec@6a724362\n",
       "\u001b[36mlagCol\u001b[39m: \u001b[32mColumn\u001b[39m = lag(users_perWeek, 1, NULL) OVER (ORDER BY WeekOfYear ASC NULLS FIRST unspecifiedframe$())\n",
       "\u001b[36mnewdf\u001b[39m: \u001b[32mDataFrame\u001b[39m = [WeekOfYear: int, users_perWeek: bigint ... 1 more field]"
      ]
     },
     "execution_count": 16,
     "metadata": {},
     "output_type": "execute_result"
    }
   ],
   "source": [
    "//3. Calculate weekly seasonality\n",
    "var df2=df.select(\"user_id\",\"WeekOfYear\").\n",
    "  distinct().\n",
    "  groupBy(\"WeekOfYear\").\n",
    "  agg(count(col(\"user_id\")).as(\"users_perWeek\")).\n",
    "  sort(col(\"WeekOfYear\"))\n",
    "\n",
    "var window = Window.orderBy(\"WeekOfYear\")\n",
    "var lagCol = lag(col(\"users_perWeek\"), 1).over(window)\n",
    "var newdf=df2.withColumn(\"lagCol\", lagCol)\n",
    "\n",
    "var trend = newdf.\n",
    "  withColumn(\"Trend\",\n",
    "  when(col(\"lagCol\").isNull, lit(\"Note enough info\"))       \n",
    "  when(col(\"users_perWeek\")>col(\"lagCol\"), lit(\"Increase\"))\n",
    "  when(col(\"users_perWeek\")<col(\"lagCol\"), lit(\"Decrease\"))\n",
    "  otherwise(lit(\"No change\"))).show()\n"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 12,
   "metadata": {},
   "outputs": [
    {
     "name": "stdout",
     "output_type": "stream",
     "text": [
      "+---------------------------+------------+\n",
      "|Lists                      |count(Lists)|\n",
      "+---------------------------+------------+\n",
      "|[editor_done_click]        |2111        |\n",
      "|[editor_open, photo_upload]|2           |\n",
      "|[photo_upload, editor_open]|1           |\n",
      "|[photo_upload]             |2324        |\n",
      "|[editor_open, editor_open] |1           |\n",
      "|[editor_open]              |3890        |\n",
      "+---------------------------+------------+\n",
      "\n"
     ]
    },
    {
     "data": {
      "text/plain": [
       "\u001b[36mmydf\u001b[39m: \u001b[32mDataset\u001b[39m[\u001b[32mRow\u001b[39m] = [device_id: string, event_type: string ... 1 more field]\n",
       "\u001b[36mdf3\u001b[39m: \u001b[32mDataFrame\u001b[39m = [device_id: string, event_type: string ... 2 more fields]\n",
       "\u001b[36mdf_edit\u001b[39m: \u001b[32mLong\u001b[39m = \u001b[32m3892L\u001b[39m"
      ]
     },
     "execution_count": 12,
     "metadata": {},
     "output_type": "execute_result"
    }
   ],
   "source": [
    "//5.calculate editor_open -> editor_done_click -> photo_upload funnel\n",
    "\n",
    "var mydf=df.select(\"device_id\",\"event_type\",\"timestamp\").\n",
    "where(col(\"event_type\").isin(\"editor_open\",\"editor_done_click\",\"photo_upload\")).\n",
    "orderBy(col(\"device_id\"),col(\"timestamp\"),col(\"event_type\"))\n",
    "\n",
    "////method 1 \n",
    "var df3=mydf.withColumn(\"Lists\", collect_list(\"event_type\").\n",
    "                        over(Window.partitionBy(\"device_id\").\n",
    "                             orderBy(\"timestamp\")))\n",
    "               \n",
    "//distincti xndir ka?\n",
    "df3.groupBy(\"Lists\").agg(count(col(\"Lists\"))).show(false)\n",
    "\n",
    "//editor_open-i tiv@ chi brnum prosto filtrac editor_open-i het!\n",
    "var df_edit=mydf.where(col(\"event_type\")===\"editor_open\").count()\n",
    "\n"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 8,
   "metadata": {},
   "outputs": [
    {
     "data": {
      "text/plain": [
       "\u001b[36mdf200\u001b[39m: \u001b[32mDataset\u001b[39m[\u001b[32mRow\u001b[39m] = [device_id: string, MyDate: date]\n",
       "defined \u001b[32mfunction\u001b[39m \u001b[36mmyfunc\u001b[39m\n",
       "\u001b[36mres7_2\u001b[39m: \u001b[32mLong\u001b[39m = \u001b[32m0L\u001b[39m"
      ]
     },
     "execution_count": 8,
     "metadata": {},
     "output_type": "execute_result"
    }
   ],
   "source": [
    "//7. Calculate D7 retention of editor_open\n",
    "var df200=df.\n",
    "      select(\"device_id\",\"MyDate\").\n",
    "      distinct().\n",
    "      where(col(\"event_type\")===\"editor_open\")//es pahin hl@ tox dzerqov @ntrenq event_type-@\n",
    "\n",
    "def myfunc(date:String,days:Int,df:DataFrame):Long={\n",
    " \n",
    "  var mydate=DateTime.parse(date,formatter)\n",
    "  var helper=mydate.plusDays(days)\n",
    "  var nextdate=helper.toString.slice(0,10)\n",
    "  \n",
    "  var df2=df.where(col(\"MyDate\")===date)\n",
    "  var df3=df.where(col(\"MyDate\")===nextdate)\n",
    "  df2.join(df3, df2.col(\"device_id\")===df3.col(\"device_id\")).count()\n",
    "}\n",
    "myfunc(\"2018-02-02\",7,df200)"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": null,
   "metadata": {},
   "outputs": [],
   "source": []
  },
  {
   "cell_type": "code",
   "execution_count": null,
   "metadata": {},
   "outputs": [],
   "source": []
  },
  {
   "cell_type": "code",
   "execution_count": null,
   "metadata": {},
   "outputs": [],
   "source": []
  },
  {
   "cell_type": "code",
   "execution_count": null,
   "metadata": {},
   "outputs": [],
   "source": []
  },
  {
   "cell_type": "code",
   "execution_count": null,
   "metadata": {},
   "outputs": [],
   "source": []
  },
  {
   "cell_type": "code",
   "execution_count": null,
   "metadata": {},
   "outputs": [],
   "source": []
  },
  {
   "cell_type": "code",
   "execution_count": null,
   "metadata": {},
   "outputs": [],
   "source": []
  },
  {
   "cell_type": "code",
   "execution_count": null,
   "metadata": {},
   "outputs": [],
   "source": []
  },
  {
   "cell_type": "code",
   "execution_count": null,
   "metadata": {},
   "outputs": [],
   "source": []
  },
  {
   "cell_type": "code",
   "execution_count": null,
   "metadata": {},
   "outputs": [],
   "source": []
  },
  {
   "cell_type": "code",
   "execution_count": null,
   "metadata": {},
   "outputs": [],
   "source": []
  },
  {
   "cell_type": "code",
   "execution_count": null,
   "metadata": {},
   "outputs": [],
   "source": []
  },
  {
   "cell_type": "code",
   "execution_count": null,
   "metadata": {},
   "outputs": [],
   "source": []
  }
 ],
 "metadata": {
  "kernelspec": {
   "display_name": "Scala (2.11)",
   "language": "scala",
   "name": "scala211"
  },
  "language_info": {
   "codemirror_mode": "text/x-scala",
   "file_extension": ".scala",
   "mimetype": "text/x-scala",
   "name": "scala",
   "nbconvert_exporter": "script",
   "version": "2.11.12"
  }
 },
 "nbformat": 4,
 "nbformat_minor": 2
}
