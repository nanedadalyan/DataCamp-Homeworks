package FinReport
import java.io.FileWriter

class Shoping extends Expense {
  override def setData(Date:String,amount:Int,description:String,nameOfFile:String): Unit ={
    TrInfo.date = Date
    TrInfo.amount = amount
    TrInfo.description = description
    val file_Object = new FileWriter(nameOfFile,true)
    file_Object.write("\n" + TrInfo.id + "," + TrInfo.date + ","  + TrInfo.amount + "," + TrInfo.description)
    file_Object.close()
  }

  override def getData(uploadedfile:String){
    var id_arr = Array[String]()
    var date_arr = Array[String]()
    var amt_arr = Array[Double]()
    var desc_arr = Array[String]()
    val bufSource = io.Source.fromFile(uploadedfile)
    for (line <- bufSource.getLines) {
      val cols = line.split(",")
      id_arr = id_arr :+ cols(0)
      date_arr = date_arr :+ cols(1)
      amt_arr = amt_arr :+ cols(2).toDouble
      desc_arr = desc_arr :+ cols(3)
    }
    bufSource.close
  }


}