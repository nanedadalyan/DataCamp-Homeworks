//--------------------------Task 1--------------------------
// Shopping basket
import scala.io.StdIn.readLine
def shopping():Array[String]={
  var checkout:String = ""
  var count = 0
  var basket:Array[String] = new Array[String](5)
  while(checkout != "YES"){
    try{
      basket(count) = readLine("Enter product name\n")
      checkout = readLine("If you wish to check out, type YES \nIf you want to continue, press ENTER")
      count += 1
    }
    catch{
      case _:Throwable => println("ERROR!!! Your items exceeded the limit of 5!");
			return basket
    }

  }
  return basket  
}

shopping()

//------------------------Task 1 END-------------------------


//--------------------------Task 2--------------------------
// Convert user input to int
import scala.io.StdIn._
def stringToInt(): Unit ={
  var text = readLine()
  try{
    var number = text.toInt
    println(number)
  }
  catch{
    case _: Throwable => println("ERROR!!! Conversion from character to integer is impossible!")
  }
  
}

stringToInt()

//------------------------Task 2 END-------------------------


//--------------------------Task 3--------------------------
//Print sequence of letters recursively
//ABC
//AB
//A
//A
//AB
//ABC
def typeLettersRecursive(number: Int): Unit={
  
  if (number>1 && number<27){
    for (i <- 0 to number){
      print((65+i).toChar)
    }
    println()
    
    typeLettersRecursive(number-1)
    
    for (i <- 0 to number){
      print((65+i).toChar)
    }
    println()
  }
    else if (number == 1){
    println(65.toChar)
    println(65.toChar)
  }
    else{
    println("Invalid input. Should be integer between 1 and 26")
  
  }
}

var num:Int =8
typeLettersRecursive(num)

//------------------------Task 3 END-------------------------


//--------------------------Task 4--------------------------
//Generate all lowercase leters in list
var lowercase:List[Char] = List()
for (i <- 97 to 122){
  lowercase = lowercase:+i.toChar
}
println(lowercase.mkString(", "))

//------------------------Task 4 END-------------------------


//--------------------------Task 5--------------------------
//Calculate average of array, exluding min and max
def averageNoOutlyers(arr:Array[Int]):Double={
  var min = arr(0)
  var max = arr(0)
  var sum:Double = 0.0
  
  for (i <- arr){
    if (min > i)
      min = i
    if (max < i)
      max = i
    sum += i
  }
  
  return (sum - min - max)/(arr.length-2)
}

var arrayTest:Array[Int] = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 100, -999)
println(averageNoOutlyers(arrayTest))

//------------------------Task 5 END-------------------------


//--------------------------Task 6--------------------------
//Shift the sequence of numbers from 1 to n to the left and append the ones that pop out to the right
def leftShift(numbers:Int, shifts:Int):Unit={
  println("Input numbers       : from 1 to " + numbers)
  println("Number of shifts : " + shifts)
  for(i <- 1 to numbers){
    print(i.toString + " ")
  }
  println("")
  for(i <- shifts+1 to numbers)
    print(i.toString + " ")
  for(i <- 1 to shifts)
    print(i.toString + " ")
}


leftShift(6, 2)

//------------------------Task 6 END-------------------------


//--------------------------Task 7--------------------------
// repeat the string infinite times and find how many 'a' letters occur in the first n characters of that string
import scala.collection.immutable.StringOps

def findA(str:String, n:Int):Integer={
  val len:Int = str.length
  val substr:String = str.slice(0, (n%len))
  var result = (n/len)*(str.count(_=='a')) + substr.count(_=='a')
  return result
}

findA("aba", 6)

//------------------------Task 7 END-------------------------

