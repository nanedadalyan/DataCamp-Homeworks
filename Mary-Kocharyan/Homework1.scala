//Exercise 1

import io.StdIn._

def superbasket():Array[String]= {
  var custImp = readLine.split(",")
  var basket:Array[String] = Array() 
  custImp.foreach {product =>
               basket = basket:+ product
        if (basket.length > 5)
        throw new Exception("Basket is Full")
      
      }
        return basket
  }
  

//Exercise 2


def convInt(): Unit = {
  try {
    readLine.toInt
  } catch {
    case e: Exception => println ("Impossible to convert this string into Int")
  }
}


//Exercise 3

//I have tried to solve this one for two days. This is what I could do. I have a function two arguments, because I
//couldn't solve it with one argument.

var iter = 0
def f(n:Int, k: Int): Unit = {
  iter = iter + 1
  var alp = "ABCDEFGHIJKLMNOPQRSTUVWQYZ"
  if (n > 0) {
    println(alp.take(n))
    return (f(n-1,k))
  }
  else{
    if (k != iter/2){
    println(alp.take(k+1))
    return f(n, k+1)
  }
  }
}



//Ecercise 4

var alphabet:List[Char] = List() 
for(i <- 97 to 97+25){
  alphabet = alphabet :+ i.toChar
}



//Exercise 5

 def average(numbers: Array[Int]):Double={
  var len = numbers.length
  var average = (numbers.sum - numbers(0) - numbers(len - 1)).toDouble / (len - 2).toDouble
     return average
}



//Exercise 6

def rotateLeft(n:Int, d:Int): Array[Int] ={
  var arr = Array.range(1, n+1)
  var size = arr.length
  var rotated_arr = arr.drop(d%size)++arr.take(d%size)
  return rotated_arr
}



//Exercise 7

def rep_str(s:String, n:Int): Int={
var new_string = s*math.floorDiv(n,s.length)+s.take((n%s.length))
var count = 0
new_string.foreach{letter =>
         if (letter == 'a') count = count + 1

  }
  return count
}

