package hovo

trait Users{
  import io.StdIn._
  protected val dbUsers = scala.collection.mutable.Map[String,Map[String,Int]]()
  def addUser:Unit = {

    try{dbUsers += (readLine("Please enter fullname: ") -> Map("OnNetCalls" ->
      readLine("Please enter On net minutes: ").toInt,
      "OutOfNetCalls" ->
        readLine("Please enter out of net minutes: ").toInt,
      "Sms" ->
        readLine("Please enter number of sms: ").toInt,
      "Internet" ->
        readLine("Please enter Internet amount: ").toInt))
      println()
      println("User succesfully added: ")}
    catch{case _ =>
      println()
      println("Please enter valid input!!! ")
      println()

      addUser
    }
  }
  def deleteUser():Unit = {
    for(i <- dbUsers.keys){
      println(i)}
    println()
    val deleteName = readLine("Which User do you want to delete? ")
    if(dbUsers.keys.toList.contains(deleteName)){
      dbUsers -= deleteName
      println("Tariff succesfully deleted!!!")
    } else {
      println("Sorry, there is no User " + deleteName)
    }
  }

  def infoUser():Unit = {
    try{for(i <- dbUsers.keys){
      println(i)}
      println()
      val name = readLine("Please enter user name: ")
      println()
      println("user name: " + name)
      println("On Net Calls: " + dbUsers(name)("OnNetCalls") + " Minutes")
      println("Out Of Net Calls: " +dbUsers(name)("OutOfNetCalls") + " Minutes")
      println("SMS: " + dbUsers(name)("Sms"))
      println("Internet: " + dbUsers(name)("Internet") + " MB")
    }catch { case _ =>
      println()
      println("Can't find user!!!")

    }



  }

}
