/* 1. Create supermarket basket like function which creates fixed size array and
appends customer products  to this basket printing products. 
Basket becomes full if products in the basket are  more than  5.
If basket is full throw exception by printing “Basket is  full”.*/

import io.StdIn._
def inBasket(): Unit=
{
  var basket= new Array[String](5)
  var counter=0
  var prod=""
  try
  {
    while (true)
    {
      prod=readLine("PLease insert your product ")
      basket(counter)=prod
      //vonc tpenq menak voch null arjeqner@? filter porceci chexav
      println(basket.mkString(","))
      counter=counter+1
    } 
  }
  catch
  {
    case full:ArrayIndexOutOfBoundsException => throw new Exception("Sorry the Basket is full")
  }
}
inBasket()




/* 2. Create function for converting strings to int. 
Catch exception if confersion can not be done, otherwise return the converted value.*/

import io.StdIn._
def StrToInt(): Int =
{
  var str=readLine("Please insert Int")
  try
  {
    str.toInt
  }
  catch
  {
    case mycase:NumberFormatException => throw new Exception("I said please insert Int")
  }
}
StrToInt()




/* 4. Make a List[Char] that contains ’a’-’z’ without typing in all the characters. (Use toChar to make this work.)*/
def letters(): List[String]=
{
  var myList= List[String]()
  var i=1
  while (i<=26)
   {
     myList=myList:+(i+64).toChar.toString
     i=i+1
   }
   myList
}
println(letters())



/* 5. Write a function that takes a number of values and returns the average
excluding the largest and smallest values. */

def avg(arr:Array[Int]): Unit=
{
  if (arr.length<3)
  {
    println ("please instert at least three numbers")
  }
  else
  {
    try
    {
      var helper=(arr.filter(_!=arr.min)).filter(_!=arr.max)
      println(helper.sum/helper.length)
    }
    catch
    {
      case a:ArithmeticException => throw new Exception("please instert at least three Different numbers")
    }
  }
}
avg(Array(1,2,3,4,5))



/* 6. https://www.hackerrank.com/challenges/array-left-rotation/problem */


def leftStep(arr:Array[Int],step:Int): Array[Int]=
{
  //vonc filter anenq indexic kaxvac?
   return arr.slice(step,arr.length)++arr.slice(0,step)
}  
leftStep(Array(1,2,3,4,5),1)



def countA(str:String,num:Int):Unit=
{
  var counter=0
  var i=0
  var newStr=str
  if (str.length<num)
  { 
    //if possible join the whole word
    while(num%str.length==0)
    {
      newStr=newStr+str
    }
    //join to newStr letters one by one
    for(i<-0 to num-newStr.length)
    {
      newStr=newStr+str(i)
    }
    //counting "a" in new string 
    for (i <- newStr)
    {
      if (i.toString=="a")
      {
        counter=counter+1
      }
    }
    println(counter)
  }
  else
  {
    println("num should be greater than the length of string")
  }
}
