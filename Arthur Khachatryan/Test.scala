package HomeWork

object Test extends App {
  /////////////////////////////////Base/////////////////
  var allRooms = new MyStat

  var Room1 = new Room
  var Room2 = new Room
  var Room3 = new Room
  var Room4 = new Room

  println("////////////////////////////Part of Validation//////////////////////////")
  Room1.setRoomNumber("001")
  Room2.setRoomNumber("002")
  Room3.setRoomNumber("003")

  //vonc roomname-@ avtomat dnem objecti anunov?
  Room1.setRoomName("Room1")
  Room2.setRoomName("Room2")
  Room3.setRoomName("Room3")

  Room1.setRoomType(BaseType.Triple)
  Room2.setRoomType(BaseType.Double)
  Room3.setRoomType(BaseType.Luxury)
  Room4.setRoomType(BaseType.Luxury)

  allRooms.addRoom(Room1,Room2,Room3)// baic ete minchev addroom tal@ krknvox anunov lini k@nduni

  println("/////////Check for using same number several time////////")
  Room4.setRoomNumber("003")
  println("/////////Check for using same name several time////////")
  Room4.setRoomName("Room3")

  println("/////////Check for ability to change room number or type////////")
  Room2.setRoomNumber("004")
  Room3.setRoomType(BaseType.Double)///Room-i anun@ chi berum lav chem jarangel


  println("/////////Check for multiple addition and addition with not complete parameters////////")
  allRooms.addRoom(Room2)
  allRooms.addRoom(Room4)
//////////////////////////Actions///////////////////////////////////////
  Room1.reserve(5, 2)
  
  println("/////////Part of Mystat////////////")
  allRooms.showReserved()
  allRooms.showFree()
  allRooms.showIncome()

  ////////////////////////////Classes///////////////////////////////////////
  class Room extends RoomType {
    private var rStatus: String = "Free"
    private var rNum: String = _
    private var rName:String=_
    var rGuestNum: Int = _
    var rResDays: Int = _

    def setRoomName(name:String):Unit= {
        if(allRooms.checkRoomName(name)==1) {
          println("This name is already taken")
        }
        else if (rName == null) {
          rName = name
        }
        else {
          println("You cant change " + this.rName + " name")
        }
    }
    def setRoomNumber(num:String):Unit={// stuguma vor lini eranish u 000-n chlini
      if(num.length==3 && num.toInt>0) {
        if (allRooms.checkRoomNumber(num) == 1) {
          println("This number is already taken")
        }
        else if (rNum == null) {
          rNum = num
        }
        else {
          println("You cant change " + this.rName + " number")
        }
      }
      else{
        println("PLease input valid number like- 011 ")
      }
    }
    def showRoomName():String={
      rName
    }
    def showRoomNumber():String={
      rNum
    }
    def showStatus():String={
     rStatus
    }
    def reserve(guestNum: Int, resDays: Int): Unit = {
      rGuestNum = guestNum
      rResDays = resDays
      rStatus = "Reserved"
    }
    def makeFree(): Unit = {
      rStatus = "Free"
      rGuestNum = 0
      rResDays = 0
    }
    def myPrice(): Double = {
      rPrice() * rGuestNum * 1.2 * rResDays
    }
  }

  ////////////////////////////////////////

  object  BaseType extends Enumeration{
    val Studio,Double,Triple,Luxury=Value
  }

  class RoomType extends BasePrice{
    import HomeWork.Test.BaseType._
    private var rType:BaseType.Value =_
//////////////////////////////////////////////////////////show-n u set@ texapoxel room-i mej?!
    def setRoomType(roomtype:BaseType.Value):Unit={
      if (rType==null){
        rType=roomtype
      }
      else {
        println("You cant change " + this +" type")//normal roomi anun@ cuyc ta
      }
    }
    def showRoomType():BaseType.Value={
      rType
    }
    def rPrice(): Double = {
      if (rType==Double) {
        bPrice() * 2
      }
      else if (rType==Triple) {
        bPrice() * 3
      }
      else if (rType==Luxury) {
        bPrice() * 5
      }
      else {
        bPrice()
      }
    }
  }

  ///////////////////////////////
  class BasePrice {
    def bPrice(): Int = {
      1000
    }
  }

///////////////////////////////
class MyStat{
  var allRooms: Array[Room] =Array()

  def checkRoomName(str:String):Int ={
    var sum=0
    for(i<-allRooms){
      if (i.showRoomName()==str){
        sum=1
      }
    }
    sum
  }
  def checkRoomNumber(str:String):Int ={
    var sum=0
    for(i<-allRooms){
      if (i.showRoomNumber()==str){
        sum=1
      }
    }
    sum
  }
  def addRoom(myRooms:Room*):Unit={
    for (eachRoom <- myRooms){
      if (allRooms.contains(eachRoom)){
        println("Sorry " + eachRoom.showRoomName() +" already exists")
      }
      else if (eachRoom.showRoomNumber()==null || eachRoom.showRoomType()==null || eachRoom.showRoomName()==null){
        println("Not enought information for " + eachRoom)///roomi anun beri inchvor dzevov
      }
      else {
        allRooms = allRooms :+ eachRoom
      }
    }
  }
  def showReserved():Unit={
    val res=allRooms.filter(_.showStatus()=="Reserved")
    if(allRooms.isEmpty){
      println("No rooms were even entered")
    }
    else if (res.isEmpty){
      println("No reserved rooms")
    }
    else{
      res.foreach(x=>{println(x.showStatus(),x.showRoomNumber())})
    }
  }
  def showFree():Unit={
    val res=allRooms.filter(_.showStatus()=="Free")
    if(allRooms.isEmpty){
      println("No rooms were even entered")
    }
   else if (res.isEmpty){
      println("Sorry all rooms are reserved")
    }
    else{
      res.foreach(x=>{println(x.showStatus(),x.showRoomNumber())})
    }
  }
  def showIncome():Unit={
    val res=allRooms.filter(_.showStatus()=="Reserved")
    var sum=0.0
    for (i<-res){
      sum=sum+i.myPrice()
    }
    println("Income = "+sum)
  }
}
}