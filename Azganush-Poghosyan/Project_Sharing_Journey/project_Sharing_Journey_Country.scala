package project_Sharing_Journey

class Country {

  def add_driver(new_driver:Driver):Unit = {
    Drivers_List.Drivers_List += new_driver

  }
  def add_passenger(new_passenger:Passenger):Unit = {
    Passengers_List.Passengers_List += new_passenger

  }
  def delete_driver(driver: Driver):Unit = {
    Drivers_List.Drivers_List -= driver
  }
  def delete_passenger(passenger:Passenger):Unit = {
    Passengers_List.Passengers_List -= passenger
  }
}

