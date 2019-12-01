package project_Sharing_Journey
import org.joda.time.{DateTime, Interval}
import project_Sharing_Journey.City.City
import project_Sharing_Journey.Passenger_Status.Passenger_Status
import scala.collection.mutable.ArrayBuffer

class Passenger {
  var first_name:String =_
  var last_name:String =_
  var starting_point:City =_
  var final_destination:City =_
  var date:DateTime =_
  var time_range:Interval =_
  var price_range:Range =_
  var status:Passenger_Status =_
  var payment = new Payment
  def id(passenger:Passenger):Int = {
    Passengers_List.indexOf(passenger)
  }

  def find_driver():Driver = {
    var matching_drivers: ArrayBuffer[Driver] = ArrayBuffer[Driver]()
    for (driver <- Drivers_List.Drivers_List) {
      if ((driver.starting_point == this.starting_point || driver.mid_destination == this.starting_point) &&
        (driver.mid_destination == this.final_destination || driver.final_destination == this.final_destination)
        && driver.date == this.date && this.time_range.contains(driver.time)
        && (driver.number_of_seats>=1)) {
        matching_drivers += driver

      }
    }
    try {
      if (matching_drivers.isEmpty) {
       println("No driver was found. Please, search later or change searching parameters.")
       matching_drivers.head
      }
      else if (matching_drivers.length == 1) {
       println("We found a driver. Name is: "+matching_drivers.head.first_name+" "+matching_drivers.head.last_name+
         ". ID number is: "+matching_drivers.head.id(matching_drivers.head))

      }
      else {
       matching_drivers = matching_drivers.filter(_.price_per_seat < this.price_range.end)

        println("We found a driver. Name is: "+matching_drivers.head.first_name+" "+matching_drivers.head.last_name+
          ". ID number is: "+matching_drivers.head.id(matching_drivers.head))
      }
    matching_drivers.head
    }
    catch {
      case a:java.util.NoSuchElementException => println("Drivers_List is empty.")
      null
    }
  }
  def getDriver:Driver = {
    this.find_driver()
  }


}
