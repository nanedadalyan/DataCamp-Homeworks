package project_Sharing_Journey
import org.joda.time.{DateTime,Interval}
import org.joda.time.format.DateTimeFormat

object Main extends App {
  var Armenia: Country = new Country
  var driver_1: Driver = new Driver
  driver_1.first_name = "A"
  driver_1.last_name = "B"
  driver_1.starting_point = City.Yerevan
  driver_1.mid_destination = City.Abovyan
  driver_1.final_destination = City.Dilijan
  driver_1.date = DateTime.parse("30/11/2019", DateTimeFormat.forPattern("dd/MM/yyyy"))
  driver_1.time = DateTime.parse("3:00", DateTimeFormat.forPattern("hh:mm"))
  driver_1.price_per_seat = 3500
  driver_1.number_of_seats = 2
  driver_1.status = Driver_Status.Free
  Armenia.add_driver(driver_1)
  var driver_2: Driver = new Driver
  driver_2.first_name = "K"
  driver_2.last_name = "N"
  driver_2.starting_point = City.Yerevan
  driver_2.mid_destination = City.Abovyan
  driver_2.final_destination = City.Dilijan
  driver_2.date = DateTime.parse("30/11/2019", DateTimeFormat.forPattern("dd/MM/yyyy"))
  driver_2.time = DateTime.parse("3:00", DateTimeFormat.forPattern("hh:mm"))
  driver_2.price_per_seat = 3000
  driver_2.number_of_seats = 1
  driver_2.status = Driver_Status.Free
  Armenia.add_driver(driver_2)
  var passenger_1 = new Passenger
  passenger_1.first_name = "C"
  passenger_1.last_name = "H"
  passenger_1.starting_point = City.Abovyan
  passenger_1.final_destination = City.Dilijan
  passenger_1.date = DateTime.parse("30/11/2019", DateTimeFormat.forPattern("dd/MM/yyyy"))
  passenger_1.time_range = new Interval(DateTime.parse("1:00", DateTimeFormat.forPattern("hh:mm")),
    DateTime.parse("5:00", DateTimeFormat.forPattern("hh:mm")))
  passenger_1.max_price = 4000
  passenger_1.status = Passenger_Status.Searching
  Armenia.add_passenger(passenger_1)
  passenger_1.find_driver()
  passenger_1.payment.pay(passenger_1, 1)
  driver_2.payment.accept_payment(driver_2, 0)

}
