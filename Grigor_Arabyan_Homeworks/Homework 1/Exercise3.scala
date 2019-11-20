import scala.io.StdIn.{readLine, readInt}
​
def letterPattern(to:Int):Unit = {
// Using below the conversions to chars, appending them to a string and printing them
// getting the uppercase numbers which convert toChar something
  var empty_string = ""
  for (i <- 65 until 65 + to){
    empty_string += i.toChar
  }
  println(empty_string)
  if(to >= 0 && to <=26){
    letterPattern(to - 1)
  }
}
//validating input if it is a number , then if the number is between 1-26
var users_input = readLine("Please enter a value between 1-26")
if (users_input forall Character.isDigit){
  var converted = users_input.toInt
  if(converted >= 0 & converted <= 26 ){
    println("Thanks for entering a valid input")
    letterPattern(converted)
  }else{
    println("Your Input was  a Number but not between 1 and 26, Try again")
  }
}else{
  println("Please enter a Number, not other thing")
}