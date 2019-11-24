//1.Create function that calculates factorial without using recursion
def fact(num:Int):Int=
{
  if(num==0 || num==1)
  {
    return 1
  }
  else if(num>0)
  {
    var mult=1
    for (i<-1 to num)
    {
      mult=mult*i
    }
    return mult
  }
  else
  {
    throw new Exception("Please input non negative number")
  }
}
fact(5)
///////////////////////////////////////////////////////////////////////////////////////////
//2. Calculate sum of n numbers using recursion. 

def sumNum(nums:Array[Int]):Int=
{ 
  def calc(myArr:Array[Int],myArrLen:Int): Int=
  {
    if (myArrLen<0)
    {
      return 0
    }
    return myArr(myArrLen)+calc(myArr,myArrLen-1)
  }
  
  calc(nums,nums.length-1)
}
sumNum(Array(1,2,3,4,5,-5,-4,-3))

///////////////////////////////////////////////////////////////////////////////////////////
// 3. Suppose you have an array with integers. Create function/method that  
//   will create another data type containing number with it frequencies.
import scala.collection.mutable.Map
def countNum(arr:Array[Int]): Map[Int,String]=
{
  var MyMap = Map[Int, String]()
  for (i <- arr.distinct)
  {
    MyMap=MyMap + (i->arr.count(_==i).toString)//count ani distinct arrayi amen element@ skzbnakan arrayum
  }
  return MyMap
}
countNum(Array(1,2,2,3,3,3,4,4,4,4,5,5,5,5,6))
//Inchia inq@ random sharum vochte im tvac hertakanutyamb?

///////////////////////////////////////////////////////////////////////////////////////////
/*4. Create function that  will find out how many cases are in array where sum of numbers is 12. Carefully  test your  function. 


Im patkeracnelov ete xndir@ senca grac urem amen qanaki gumarelineri depqer@ petqa hashvi arnel sksac
1ic minchev 12 hat gumareli(en depqum ete  bacasakan tver chen lini grac)

Mi bani mejem vstah vor petqa sort arvi 

Petqa stugvi qani depq ka vor 1 gumareliova stacvum 12, 
heto stugvi qani depq ka vor 2 gumareliova stacvum 12 ,
heto 3 gumareliov u tenc micnhev 12 

Qani vor chka pahanj naev vor krknvox tver chlinen urem orinak 
2 gumarelii hamar petqa arajin indexi hamar  verji indexic galov bolor gumarner@ stugvi 
qani der erkusi gumar@ poqr chi lini 12ic ayd depqum arajin index@ mi hatov avelacnenq u noric verjic galov stugenq gumar@

Ete 3 gumareli unenq urem amen angam 12ic hanenq tvyal indexi tiv@ ev xndir@ veradarcnenq 2 hatanoc depqin arden urish tvi hamar

Isk ete 4 ev avela? Recursive kanchenq enqan minchev xndir@ chhasni noric 2 hatanoc depqin lriv urish tvov 
ev arden lriv nor subarrayi hamar, isk et skzbanakn tveri @ntrutyun@ vonc katarvi ?

Miteska shat xorna stacvum kamel de inchvor ban sxalem haskanum :D
*/
///////////////////////////////////////////////////////////////////////////////////////////
//5. Sorting algos
import java.util.Arrays._
def mergeSort(arr:Array[Int]):Array[Int]=
{
  var len=arr.length
  var mid=len/2
  if(len==1) return arr

  var left=java.util.Arrays.copyOfRange(arr,0,mid)
  var right=java.util.Arrays.copyOfRange(arr,mid,len)
  mergeSort(left)
  mergeSort(right)
  sort(arr,left,right)
  
  def sort(arr:Array[Int],left:Array[Int],right:Array[Int]):Array[Int]=
  {
    var l=left.length
    var r=right.length
    var i=0
    var j=0
    var k=0
    
    while(i<l && j<r)
    {
      if(left(i)<=right(j)) 
      {
        arr(k)=left(i)
        i=i+1
        k=k+1
      }
      else
      {
        arr(k)=right(j)
        j=j+1
        k=k+1 
      }
    }
    while(i<l)
    {
      arr(k)=left(i)
      i=i+1
      k=k+1
    }
      while(j<r)
    {
      arr(k)=right(j)
      j=j+1
      k=k+1
    }
    
    return arr
  }
   return arr
}
mergeSort(Array(0,6,4,1,3,-5,-7))
// In each level of division of an arra into two parts code does n elemnt comparisons 
//and the number of levels of divisons depends on the number of elements(n) for 8 elements the level of division is 3, for 4 elemnt- is 2
//which makes it logn times. So in total we have nlogn timecoplexity and for every new created array we use space on computer memory
//-additional memory for an array with n elements. Doesnt matter if array is sorted or no.

def bSort(arr:Array[Int]):Unit=
{
    for(i <- 0 to arr.length-2)
    {  
      for(j <- 0 to arr.length - i - 2)
      {
        if(arr(j)>arr(j+1))
        {//swap
          arr(j)=   arr(j)+arr(j+1)
          arr(j+1)= arr(j)-arr(j+1)
          arr(j)=   arr(j)-arr(j+1)
        }
      }
     }    
     println( arr.mkString(",") )
}


bSort(Array(1,5,9,4,2,8))

//The running time is always n^2 despite beaing sorted in reverse order or unsorted or sorted.
//Beacouse it is cheaking everytime for need of swap.The code can be improved by setting a variable that checks
//if any swap has been done in fisrt loop. if no - it breakes the loop, so in case of sorted array the running time will be smaller

///////////////////////////////////////////////////////////////////////////////////////////
//6.Create a function that will calculate the length of strings in an array and will
//output indexes of a strings having even length.

def evenStr(arr:Array[String]):Array[Int]=
{
  var newArr=Array[Int]()
 
  for (i<-0 until arr.length)
  {
    if (arr(i).length%2!=0)
    {
      newArr=newArr:+i
    }
  } 
  return newArr
}
evenStr(Array("123","1234","1234567","123456"))
///////////////////////////////////////////////////////////////////////////////////////////
//7.Create function that will return the first 3 items in a list.

def numOfEl(arr:Array[Any],num:Int): Unit=
{
  if (num>arr.length || num<0)
  {
    println("Please input valid data")
  }
  else
  {
    var newArr=new Array[Any](num)
    for (i<-0 to num-1)
    {
      newArr(i)=arr(i)
    }
    println(newArr.mkString(", "))
  } 
}
numOfEl(Array(1,2,3,4,5,6,7,8),5)//return the first 5
//kam method ka arr.take(3) orinak..kam slice-ov
///////////////////////////////////////////////////////////////////////////////////////////
//8.	Describe when it is more convenient to use array vs list vs set.

// For performance characteristics, an Array is faster with random access to elements, 
//whereas a List is faster when adding new elements. use a Set when you want to specifically exclude duplicates
//  Due to the way they're stored, it's faster to check whether an item is part of a set, rather than part of a list.
// When to use the other types:
//  - Use lists if you have a collection of data that does not need random access. 
//Try to choose lists when you need a simple, iterable collection that is modified frequently.
//  - Use a set if you need uniqueness for the elements.
//  - Use tuples when your data cannot change.

///////////////////////////////////////////////////////////////////////////////////////////
//9.Filter elements in an array of integers where >4 and <7.
def filt(arr:Array[Int]): Unit=
{
  var lBound=4
  var uBound=7
  var newArr=arr.filter(_ >lBound).filter(_<uBound)
  if (newArr.length==0)
  {
    println("No numbers are greater than "+lBound+ " and lower than " +uBound)
  }
  else
  {
   println(newArr.mkString(", "))
  }
}
filt(Array(1,2,3,4,5,6,7,8,9,0))
///////////////////////////////////////////////////////////////////////////////////////////
//10. Write a recursive function called isPrime that returns a Boolean and lets you know whether or not a number is prime
import scala.math.sqrt
def IsPrime(myNum:Int):Unit=
{
  var div=2
  realFunc(myNum,div)

  def realFunc(num:Int,div:Int)
  {  
    if(num==1 || num==2)
    {
       println("true") 
    }
    else if (num%div!=0)
    {
        if(div<sqrt(num))
        {
         realFunc(num,div+1)  
        }
        else
        {
          println("true") 
        }

    }
    else
    {
      println("false") 
    } 
  }
}
IsPrime(11)
///////////////////////////////////////////////////////////////////////////////////////////
//11.Write a program that deletes duplicate elements from the array.
import scala.collection.mutable.ListBuffer
def removeDub(arr:ListBuffer[Int]): Unit=
{
  var helper= ListBuffer[Int]()
  for (i<- 0 to arr.length-1)
  {
    if (helper contains arr(i))
    {
      //do nothing ?
    }
    else
    {
      helper=helper:+arr(i)
    }
  }
println(helper.mkString(","))
}
removeDub(ListBuffer(1,1,1,2,2,3,4,5,6,6))

///////////////////////////////////////////////////////////////////////////////////////////
//12. Given two strings, find the number of common characters between them. Example
          //For s1 = "aabcc" and s2 = "adcaa", the output should be
          //commonCharacterCount(s1, s2) = 3.

def commonCharCount(str1:String,str2:String):Int=
{
 str1.length-(str1 diff str2).length 
}
commonCharCount("aabcc","adcaa")
///////////////////////////////////////////////////////////////////////////////////////////