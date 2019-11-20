/**1.Create supermarket basket like function which creates fixed size array and  
*appends customer products  to this basket printing products. Basket becomes 
*full if products in the basket are  more than  5. 
*If basket is full throw exception by printing “Basket is  full”.*/
//in order to create our own exception, we need to declare the CustomException class
case class CustomException(s: String)  extends Exception(s)
import io.StdIn._
def supermarket():Unit = {
 println("Please, enter your products")
//created function supermarket with no argument which take user input of products, will add them to basket array
//and will return the list of 5 items.
 val products = readLine()
 var basket:Array[String] = products.split(" ")
 try {if (basket.length<=5)
      {println(basket.mkString(" "))}
//if the number of items is more than 5 will throw an exception and will print "basket is full"
      else {throw CustomException("full basket")}}
 catch {
   case e: CustomException => println("basket is full")
 }
}


/**2. Create function for converting strings to int. 
*Catch exception if confersion can not be done, otherwise return the converted value.*/

def strtoint(a:String):Unit = {
  try {println(a.toInt)}
//  creates function strtoint which turns a string to int through .toInt method
// if the string contains any other data type except integer a NumberFormatException is thrown 
  catch {
  case ex: NumberFormatException => println("Cannot be converted to an integer. Try again!")       
  }
}


/**3. Write a recursive function that generates the following pattern of letters. Make sure that the user enters an integer between 1 and 26. 
*If the non-negative integer is 6, then the pattern generated is:
*ABCDEF
*ABCDE
*ABCD
*ABC
*AB
*A
*A
*AB
*ABC
*ABCD
*ABCDE
*ABCDEF*/

def funnew(number:Int):String = {
  val alp = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
// created a variable alp: all uppercase letters of English alphabet
  if (number<1 || number>26) {
// make sure that user input is between 11 and 26 
  var res = "Please, enter a number between 1 and 26"
  return res
  } else if (number == 1) {
// if number is 1, prints first letter of alp string ("A") on two lines
  var res = (alp(0)).toString+"\n"+(alp(0)).toString
  return res
  } else {
// if number >2, two recursive functions work that print a substring of string alp from index 0 up to the index of the number.
// then gradually approach to base case "A" from different directions. 
  var res = (alp.substring(0,number)).toString+"\n"+
  (funnew(number-1)).toString+"\n"+(alp.substring(0,number)).toString
  return res
  }
  }


//4. Make a List[Char] that contains ’a’-’z’ without typing in all the characters. (Use toChar to make this work.)

var newlist = List[Char]('a')
//created a List(newlist) with only one item in it 'a' then appended (updating new list every time) 
// the rest of the letters in the alphabet through for loop
for (i <-1 to 25) 
  newlist = newlist:+(newlist(0)+i).toChar


//5. Write a function that takes a number of values and returns the average  excluding the largest and smallest values.

 def funav(args:Double*):Double = {
// created a funav function and res variable which is a list of function arguments
  var res = args
// created a new variable avargs which takes the res array and subtracts min and max values from the sum of its items
// then divides that amount to the number of items minus 2(min and max values)
  var avargs = (res.sum-res.min-res.max)/(res.length-2)
  return avargs
}

//6.https://www.hackerrank.com/challenges/array-left-rotation/problem
  
def makestring():Unit = {
// created a function with no arguments. 
  println("enter n and d")
// takes user inputs: n is the number of integers, d is the number of times they need to be shifted to left
  var str1 = readLine()
  var n = str1.split(" ")(0).toInt
  var d = str1.split(" ")(1).toInt
// as user inputis in the form of  "n d", first we need to split the string and then make strings to integers through .toInt method
  println("enter n integers")
// take a user input of the actual integers
  var str2 = readLine()
// split the string and make an array of integers
  var arr = str2.split(" ")
// as one of the conditions is that d <= n, if we shift the letters to left by d numbers, then all the letters starting from d till the end
// will become the first letters of the new string, and the first letters of the old string up to index d will become the continuation of the 
// new string. We just need to concatenate these strings.
  var newarr = arr.slice(d,n)++arr.slice(0,d)
  println(newarr.mkString(" "))
}

//7.https://www.hackerrank.com/challenges/repeated-string/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=warmup

import io.StdIn._
def repstr():Unit = {
  println("Please, enter a string")
  val s = readLine()
// takes user inputs: the string which can be repeated infinitely many times and the number n showing how many letters we should consider
  println("Please, enter an integer")
  val n = readInt()
// newstr is the string we need to consider and count letter 'a's in it. First, we need to know the lenght of the string s. If it is less 
// than n then it (or some of its letters) can be repeated. n/s.length will show how many full s strings can cpntain newstr which multiplying 
// with actual string will show the repeated string. If the newstr contains not full s string but part of it, we can find it by calculating
// the remainder of ndivided by s.length and taking the slice of s from index 0 up to the index of the remainder. 
// [so long, hope I explained clearly]
  val newstr = s*(n/s.length)+s.slice(0,n%s.length)
  var count = newstr.count(_=='a')
  println(count)}


