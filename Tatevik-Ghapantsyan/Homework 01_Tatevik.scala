//--------------------------Task 1--------------------------
// Shopping basket
import scala.io.StdIn.readLine
def shopping(products:String):Unit={
    val basket:Array[String] = products.split(" ")		// this function takes string and splits it into an array
    println(basket.mkString(", "))
    println(basket.length)
    if (basket.length>5){					// if the size of an array is larger than 5, we throw exception
        throw new Exception("Your basket contains more than 5 items!!!")
    }
}

shopping("Cake juice cigarettes bread")

//------------------------Task 1 END-------------------------


//--------------------------Task 2--------------------------
// Convert user input to int
import scala.io.StdIn._
def stringToInt(): Unit ={
  var text = readLine()
  try{				//if we can make conversion, we convert the input to integer			
    var number = text.toInt
    println(number)
  }
  catch{			//if conversion is impossible, we throw an exception
    case _: Throwable => println("ERROR!!! Conversion from character to integer is impossible!")
  }
}

stringToInt()

//------------------------Task 2 END-------------------------


//--------------------------Task 3--------------------------
//Print sequence of letters recursively
//AB
//A
//A
//AB
def typeLettersRecursive(number: Int): Unit={
    
  if (number>1 && number<27){
    for (i <- 0 until number)        // if the number is in acceptable range, print the sequence
      print((65+i).toChar)
    println()
    typeLettersRecursive(number-1)   // calls the same function for number-1
    for (i <- 0 until number)        // printing the sequence once more, after exiting recursion
      print((65+i).toChar)
    println()
  }
	
    else if (number == 1){           // 1 is the base case, where we type "A" twice and exit
    println("A\nA")
  }
    else{                            // if we have number which is not in acceptable range, we output the error message
    println("Invalid input. Should be integer between 1 and 26")
  
  }
}

typeLettersRecursive(2)

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
    if (min > i)	// Find min
      min = i
    if (max < i)	// Find max
      max = i
    sum += i
  }
  return (sum - min - max)/(arr.length-2)   // calculate average without min and max
}

var arrayTest:Array[Int] = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 100, -999)
println(averageNoOutlyers(arrayTest))

//------------------------Task 5 END-------------------------


//--------------------------Task 6--------------------------
//Shift the sequence of numbers from 1 to n to the left and append the ones that pop out to the right
def leftShift(range:Int, shifts:Int):Unit={
  for(i <- shifts+1 to range)		// this loop outputs the sequense from 'shifts + 1' to the end
    print(i + " ")
  for(i <- 1 to shifts)			// this loop outputh the sequence from 1 to 'shifts'
    print(i + " ")
}


leftShift(6, 2)

//------------------------Task 6 END-------------------------


//--------------------------Task 7--------------------------
// repeat the string infinite times and find how many 'a' letters occur in the first n characters of that string
import scala.collection.immutable.StringOps

def findA(str:String, n:Int):Integer={
  val len:Int = str.length
  val substr:String = str.slice(0, (n%len))      // this substring is the incomplete part of string that comprises the last part of 'n'
  var result = (n/len)*(str.count(_=='a')) + substr.count(_=='a') //complete strings that fit into 'n' plus substring
  return result
}

findA("aba", 6)

//------------------------Task 7 END-------------------------

