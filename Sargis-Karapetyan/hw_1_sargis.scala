//Problem 1
import scala.io.StdIn._

def createBasket(arraySize:Int):Array[String] = {
  val arr = Array.ofDim[String](arraySize)
  var index = 0
  
  try{
    while(index < (arraySize)){
      arr(index) = readLine("Add product to your basket.")
      println(arr(index))
      index = index + 1
    }
    throw new Exception()
  }
  catch{
    case e: Throwable => {println("Your basket is full.")
                          return(arr)}
  }
}

createBasket(5)

//Problem 2
def stringToInt(str:String):Int = {
  try{
    str.toInt
  }
  catch{
    case e: Exception => 0
  }
}

stringToInt("5")
stringToInt("5a")

//Problem 3 (with 2 user defined (m, n) and one hard coded parameter value (rev) to indicate direction)
def letterPyramid(m:Int, n:Int, rev:Int=0):Unit = {
    if(m > 0 && rev==0){
      for(i <- 0 to m-1){
        print(('A'.toInt + i).toChar + " ")
      }
      println()
      letterPyramid(m-1, n)
    }
    else if(m<=n){
      for(i <- 0 to m-1){
        print(('A'.toInt + i).toChar + " ")
      }
      println()
      letterPyramid(m+1, n, rev=1)
    }
}

letterPyramid(5,5)

//Problem 4
var alphabetList = List[Char]()
for (letter <- 'a' to 'z'){
  
  alphabetList = alphabetList:+(letter.toChar)
}

//Problem 5
import scala.math

def cleverMean(nums:Array[Double]):Double = {
  val n = nums.length
  val mean = (nums.sum - nums.min - nums.max) / (n-2)
  return(mean)
}

val nums = Array(-10.0,2.0,6.0,4.0,30.5)
cleverMean(nums)

//Problem 6
def leftShift(arr:Array[Int], shift:Int):Array[Int] = {
  val n = arr.length
  var shift1 = 
  	if(shift > n) shift % n
  	else shift
  
  val shiftedArr = arr.slice(shift1,n) ++ arr.slice(0,shift1)
  return shiftedArr
}

val arr = Array(1,2,3,4)
leftShift(arr,5)

//Problem 7
def numOfAs(s:String, n:Int):Int = {
  val m = s.length
  val countInOneWord = s.count(_ == 'a')
  val countInRemainder = s.slice(0, n%m).count(_ == 'a')
  
  val count =
    if(n%m == 0) {
      (n/m) * countInOneWord
    } else {
      (n/m) * countInOneWord + countInRemainder
    }
  
  return count
}

numOfAs("ananas",7)
