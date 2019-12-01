package project_Sharing_Journey
import scala.collection.mutable.ArrayBuffer

object Drivers_List {
  def apply(id: Int): Driver = Drivers_List(id)

  def indexOf(driver: Driver): Int = Drivers_List.indexOf(driver)

  var Drivers_List:ArrayBuffer[Driver] = ArrayBuffer[Driver]()

}
