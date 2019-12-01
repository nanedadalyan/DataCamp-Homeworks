package project_Sharing_Journey
import org.joda.time.DateTime
import project_Sharing_Journey.City.City
import project_Sharing_Journey.Driver_Status.Driver_Status


class Driver {

  var first_name:String =_
  var last_name:String =_
  var starting_point: City =_
  var mid_destination: City =_
  var final_destination: City =_
  var date:DateTime =_
  var time:DateTime =_
  var number_of_seats:Int =_
  var price_per_seat:Int =_
  var status:Driver_Status =_
  var payment:Payment = new Payment
  def id(driver: Driver):Int = {
    Drivers_List.indexOf(driver)
  }




}

