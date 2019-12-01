package hovo

class FinalApp extends Tariff with Users{
  import io.StdIn._
  def suggestPlan:Any = {
    val name = readLine("Please enter full name: ")
    if(db.isEmpty){
      println("Sorry, at this moment we don't have tariffs: ")}
    else if(dbUsers.keys.toList.contains(name) == false){
      println("There is no user " + name)
    } else {


      val totalspendings = Array(dbUsers(name)("OnNetCalls") * outOfTariffPlan("InnetPrice"),
        dbUsers(name)("OutOfNetCalls") * outOfTariffPlan("OutofNetPrice"),
        dbUsers(name)("Sms") * outOfTariffPlan("SmsPrice"),
        dbUsers(name)("Internet") * outOfTariffPlan("InternetPrice")).sum
      val mapSavings = scala.collection.mutable.Map[String,Int]()
      for(i <- db.keys){
        mapSavings += (i -> (totalspendings - db(i)("ActivationFee") -
          (Array(Array(0,(dbUsers(name)("OnNetCalls") - db(i)("OnNetCalls"))).max*outOfTariffPlan("InnetPrice"),
            Array(0,(dbUsers(name)("OutOfNetCalls") - db(i)("OutOfNetCalls"))).max*outOfTariffPlan("OutofNetPrice"),
            Array(0,(dbUsers(name)("Sms") - db(i)("Sms"))).max*outOfTariffPlan("SmsPrice"),
            Array(0,(dbUsers(name)("Internet") - db(i)("Internet"))).max*outOfTariffPlan("InternetPrice")).sum)))
      }


      val (bestTariff, savings) =  mapSavings.maxBy(_._2)

      println("Hi " + name + ": ")
      println("You currently use ")
      println()
      println("On Net Calls: " + dbUsers(name)("OnNetCalls") + " Minutes")
      println("Out Of Net Calls: " + dbUsers(name)("OutOfNetCalls") + " Minutes")
      println("SMS: " + dbUsers(name)("Sms"))
      println("Internet: " + dbUsers(name)("Internet") + " MB")
      println("Total spendings: " + totalspendings + " AMD")
      println()
      println("With tariff " + bestTariff + " you will save " + savings + " AMD")

    }
  }
}
