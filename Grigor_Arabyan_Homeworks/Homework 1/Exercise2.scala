def intConverter(someints: String): Any = {
//  isDigit is mostly used in Backend which checks if it is a digit and returns
//  true or false, so i am converting them if they are digit
  if (someints forall Character.isDigit){
    println("here is your converted string")
    println(someints.toInt)
  }else{
    println("I am sorry , your Input is not a string")
  }
}
intConverter("33")