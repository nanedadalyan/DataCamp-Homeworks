package project_Sharing_Journey
import scala.collection.mutable.ArrayBuffer

object Passengers_List {
  def apply(id: Int): Passenger = Passengers_List(id)

  def indexOf(passenger: Passenger): Int = Passengers_List.indexOf(passenger)

  var Passengers_List:ArrayBuffer[Passenger] = ArrayBuffer[Passenger]()

}
