package hovo

class Tariff{
  import io.StdIn._
  protected val db = scala.collection.mutable.Map[String,Map[String,Int]]()
  protected val outOfTariffPlan = scala.collection.mutable.Map("InnetPrice" -> 1,"OutofNetPrice" -> 5,
    "SmsPrice" -> 5,"InternetPrice" -> 5)
  def updateoutOfTariffPlan:Unit = {
    try{ outOfTariffPlan("InnetPrice") = readLine("Please enter in net price: ").toInt
      outOfTariffPlan("OutofNetPrice") = readLine("Please enter out of net price: ").toInt
      outOfTariffPlan("SmsPrice") = readLine("Please enter sms price: ").toInt
      outOfTariffPlan("InternetPrice") = readLine("Please enter internet price: ").toInt
      println()
      println("Out of tariff plan succesfully updated")
    } catch { case _ =>
      println()
      println("Please, enter integer: ")
    }
  }

  def addTariff:Unit = {
    val TariffName = readLine("Please enter Tariff name: ")
    if(db.keys.toList.contains(TariffName)){

      println("Tariff name already exists, try different name: ")
    } else {
      try{ db += (TariffName ->
        Map("ActivationFee" ->
          readLine("Please enter activation fee: ").toInt,
          "OnNetCalls" ->
            readLine("Please enter On net minutes: ").toInt,
          "OutOfNetCalls" ->
            readLine("Please enter out of net minutes: ").toInt,
          "Sms" ->
            readLine("Please enter number of sms: ").toInt,
          "Internet" ->
            readLine("Please enter Internet amount: ").toInt))
        println()
        println("Tariff succesfully added!!! ")}

      catch{case _ =>
        println()
        println("Please enter valid input!!! ")
        println()

        addTariff
      }
    }
  }
  def deleteTariff():Unit = {
    for(i <- db.keys){
      println(i)}
    println()
    val deleteName = readLine("Which tariff do you want to delete? ")
    if(db.keys.toList.contains(deleteName)){
      db -= deleteName
      println("Tariff succesfully deleted!!!")
    } else {
      println("Sorry, there is no tariff " + deleteName)
    }
  }
  def updateTariff(){
    val updateName = readLine("Please enter old name: ")
    if(db.keys.toList.contains(updateName)){
      db -= updateName
      addTariff}
    else {
      println("There is no tariff " + updateName)
    }
  }
  def infoTariff():Unit = {
    try{for(i <- db.keys){
      println(i)}
      println()
      val name = readLine("Please enter one of the tariffs: ")
      println()
      println("Tariff name: " + name)
      println("Activation fee: " + db(name)("ActivationFee") + " Amd")
      println("On Net Calls: " + db(name)("OnNetCalls") + " Minutes")
      println("Out Of Net Calls: " +db(name)("OutOfNetCalls") + " Minutes")
      println("SMS: " + db(name)("Sms"))
      println("Internet: " + db(name)("Internet") + " MB")
    }catch { case _ =>
      println()
      println("Can't find tariff!!!")

    }



  }
  def printTariffs{
    for(i <- db.keys){
      println(i)}
  }


}