package project_Sharing_Journey

import project_Sharing_Journey.Payment_Status.Payment_Status

class Payment {
  private var account:Int = 0
  private var payment_status:Payment_Status =_

  def set_account(x: Int){
    account = x
  }

  def get_account:Int = {
    account
  }

  def set_payment_status(new_status:Payment_Status){
    payment_status = new_status
  }

  def get_payment_status(): Payment_Status ={
     payment_status
      }

  def pay(passenger: Passenger, driver_id:Int):Unit = {
    Drivers_List(driver_id).payment.set_account(Drivers_List(driver_id).price_per_seat)
    passenger.payment.set_payment_status(Payment_Status.Paid)
    Drivers_List(driver_id).payment.set_payment_status(Payment_Status.Received)
    println("Passenger "+passenger.first_name+" "+passenger.last_name+" paid for journey to driver "+
    Drivers_List(driver_id).first_name+" "+Drivers_List(driver_id).last_name+". Passenger ID is "+passenger.id(passenger))
  }

  def accept_payment(driver: Driver, passenger_id:Int):Unit = {
    if (driver.payment.get_account == driver.price_per_seat && driver.payment.get_payment_status() == Payment_Status.Received &&
      Passengers_List(passenger_id).payment.get_payment_status() == Payment_Status.Paid) {
      println("Driver " + driver.first_name + " " + driver.last_name + " accepted the payment")
      driver.payment.account = 0
      driver.payment.set_payment_status(Payment_Status.Accepted)
      Passengers_List(passenger_id).payment.set_payment_status(Payment_Status.Accepted)
      Passengers_List(passenger_id).status = Passenger_Status.Found
      driver.number_of_seats = driver.number_of_seats - 1
      if (driver.number_of_seats == 0) {
        driver.status = Driver_Status.Booked
      }

      println("Passenger " + Passengers_List(passenger_id).first_name + " " + Passengers_List(passenger_id).last_name +
        " status is " + Passengers_List(passenger_id).status)
      println("Driver " + driver.first_name + " " + driver.last_name + " status is " + driver.status + ". Number of free " +
        "seats now is " +driver.number_of_seats)
    }
  }
}
