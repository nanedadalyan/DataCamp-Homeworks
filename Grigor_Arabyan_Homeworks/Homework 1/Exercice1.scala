import scala.collection.mutable.ArrayBuffer
​
def printAll(strings: String) : Any = {
// If we input more items than 5 , program asks to repeat
//else accepting the string splitting it and ladding to empty basket array
  if (strings.split(" ").length > 5){
    println("Sorry, you can't fill that all in one basket")
  }else{
    var myemptybasket = ArrayBuffer[String]()
    (strings.split(" ")).foreach(myemptybasket += _)
    println(myemptybasket)
  }
}
printAll("First Seconds third ")