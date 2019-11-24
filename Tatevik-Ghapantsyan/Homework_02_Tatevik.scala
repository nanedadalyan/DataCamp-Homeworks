//---------------------------------1-----------------------------------------------------
//Task 1 Create function that calculates factorial without using recursion.

def factorial(num:Integer):Integer={
  var fac = 1
  for (i <- 1 to num){
    fac *= i
  }
  return fac
}

println(factorial(3))

//---------------------------------1 END -------------------------------------------------

//---------------------------------2-----------------------------------------------------
//Calculate sum of n numbers using recursion. What  is the running time? 

def sum(num:Integer):Integer={
  var result = 0
  if (num > 1){
    result = num + sum(num-1)
  }
  else{
    return 1;
  }
  return result;
}

println(sum(7))
//---------------------------------2 END -------------------------------------------------


//---------------------------------3-----------------------------------------------------
//Suppose you have an array with integers. Create function/method that  will 
//create another data type containing number with it frequencies.

import scala.collection.mutable.Map;
def frequency(arr:Array[Int]):Unit ={
  var result = Map[Int, Int]()
  for (i <- arr){
    if (result.keySet.exists(_==i))
      result(i) += 1
    else
      result(i) = 1
  }
  println(result)
}

var arr:Array[Int]=Array(1, 3, 5, 6, 3, 5, 7, 8, 7)
frequency(arr)
//---------------------------------3 END -------------------------------------------------



//---------------------------------4-----------------------------------------------------
//Task 4 Create function that  will find out how many cases are in array 
//where sum of numbers is 12. Carefully  test your  function. 

var counter = 0
def sumIs12(arr:Array[Int], sum:Int=0): Int={
  if (arr.length > 1){
    sumIs12(arr.tail, sum)
    if(sum + arr.head == 12)
      counter += 1
    if(sum + arr.head < 12)
      sumIs12(arr.tail, sum + arr.head)
    return counter
  }
  else if(arr.length == 1){
    if(sum + arr.head == 12)
      counter += 1
  }
  return counter
}


println(sumIs12(Array(0, 1, 0, 3, 9, 2, 12, 1)))
counter = 0
println(sumIs12(Array(12)))
counter = 0
println(sumIs12(Array(0, 1, 7, 6, 5, 1, 1, 2, 12, 1)))
counter = 0
//---------------------------------4 END -------------------------------------------------



//---------------------------------5-----------------------------------------------------
//Task 5 Sort array in ascending order using different sorting methods 
//(choose 2 different algos). Write what is running time. 
//Explain the logic behind. 


// Mergesort --------------------------------------------------------
def mergeSort(arr:Array[Int]):Array[Int]={  // This function divides array into 2 parts
  var len = arr.length
  if (len > 1){
    var a = len/2
    return merge(mergeSort(arr.slice(0, a)), mergeSort(arr.slice(a, len+1)))
  }
  else
    return arr
}

def merge(arr1:Array[Int], arr2:Array[Int]):Array[Int]={  // This function merges arrays
  var i1=0      // this variable points to the current member of first array
  var i2=0      //this variable points to the current member of second array
  var len1 = arr1.length
  var len2 = arr2.length
  var arrMerged:Array[Int] = new Array[Int](len1+len2)
  for (i <- 0 to (len1+len2)-1){
    if (i1==len1){    //if we have used all the members of the first array
      arrMerged(i1+i2) = arr2(i2)
      i2 += 1
    }
    else if (i2 == len2){    //if we have used all the members of the second array
      arrMerged(i1+i2) = arr1(i1)
      i1 += 1
    }
    else if (arr1(i1) <= arr2(i2)){
      arrMerged(i1+i2) = arr1(i1)
      i1 += 1
    }
    else {
      arrMerged(i1+i2) = arr2(i2)
      i2 += 1
    }
    
  }
  return arrMerged
}

println(mergeSort(Array(3, 8, 12, 5, 100, -1, 7, 7)).mkString(", "))

//END of Mergesort --------------------------------------------------
// Quicksort --------------------------------------------------------
def quicksort(arr: Array[Int]):Array[Int]={
  
  var len = arr.length
  if(len<=1)
    return arr
  else{
    var array = arr
    var temp = 0
    var j = 0
    var pivot = array(len-1)
    for (i <- 0 to len-2){
      if (array(i) < array(len-1)){
        temp = array(j)
        array(j)=array(i)
        array(i)=temp
        j += 1
      }
      //println(arr.mkString(", "))
   }
    temp = array(len-1)
    array(len-1)=array(j)
    array(j)=temp
    //println(arr.mkString())
    array = array.patch(0, quicksort(array.slice(0, j)), j)
    array = array.patch(j+1, quicksort(array.slice(j+1, len)), (len-j-1))
    return array
  }
}

println(quicksort(Array(-1, 1000000, 100000000, 7, 99, 7777, 3000+9000+300+1100+9000)).mkString(","))

//---------------------------------5 END -------------------------------------------------



//---------------------------------6-----------------------------------------------------
//Task 6 Create a function that will calculate the length of strings 
//in an array and will output indexes of a strings having even length. 

def stringEvenLength(arr:Array[String]): Array[Int]={
  var arrEven = Array[Int]()
  var j = 0
  for (i <- arr){
    if (i.length % 2 == 0)
      arrEven = arrEven :+ j
    j += 1
  }
  return arrEven
}

var arr = Array("asas", "dfdf", "dfg", "dfgh")
println(stringEvenLength(arr).mkString(", "))
//---------------------------------6 END -------------------------------------------------



//---------------------------------7-----------------------------------------------------
//Task 7 Create function that will return the first 3 items in a list.

	import scala.math.min
	def threeItems(lst:List[Any]): Unit ={
		var listThree = List[Any]()
		for(i <- min(2, lst.length-1) to 0 by -1){
		    listThree =  lst(i) :: listThree
		}

		println("The result is " + listThree.mkString(", "))
	}


	var lst=List(1, 2, 'a', "hello")
	threeItems(lst)
//---------------------------------7 END -------------------------------------------------



//---------------------------------8-----------------------------------------------------

//---------------------------------8 END -------------------------------------------------



//---------------------------------9-----------------------------------------------------
//Task 9 Filter elements in an array of integers where >4 and <7

var arr:Array[Int] = Array(1, 4, 8, 23, -5, 99, 4, 6, 3, 5, 6, 5, 7)
println(arr.filter(_ > 4).filter(_<7).mkString(", "))

//---------------------------------9 END -------------------------------------------------




//---------------------------------10-----------------------------------------------------
//Task 10  Write a recursive function called isPrime that returns 
//a Boolean and lets you know whether or not a number is prime.


def primeRecursive(num:Int, div:Int):Boolean = {
	if (div == 1)
		return true
	if (num % div == 0)
		return false
	primeRecursive(num, div-1)
	
}

println(primeRecursive(11, 11/2))     //I also don't like this solution, but will leave it for now

//---------------------------------10 END -------------------------------------------------



//---------------------------------11-----------------------------------------------------
//Task 11 Write a program that deletes duplicate 
//elements from the array.

def deleteDublicates(arr:Array[Int]):Array[Int] = {
	var arrUnique = arr
	var flag = true
	for (i <- arr.length-1 to 1 by -1){
		var j = i-1
		flag = true
		while (j>=0 && flag){
			if(arrUnique(i) == arrUnique(j)){
				arrUnique = arrUnique.patch(i, Nil, 1)
				flag = false
			}
			j -= 1
		}
	}
	return arrUnique
}

arr = Array(1, 1, 3, 4, 1, 4, 1, 9)
println(deleteDublicates(arr).mkString(", "))

//---------------------------------11 END -------------------------------------------------




//---------------------------------12-----------------------------------------------------
//Task 12 Given two strings, find the number of common 
//characters between them. 

def commonCharacters(str1:String, str2:String):Int = {
	var count = 0
	var str2new = str2
	var flag = true
	for (i <- str1){
		flag = true
		var j = 0
		while ( j < str2new.length && flag){
			if(i == str2new(j)){
				str2new = str2new.patch(j, Nil, 1)
				flag = false
				count += 1
			}
			j += 1
		}
	}
	return count
}

val str1 = "aannaassn"
val str2 = "asnls"
println(commonCharacters(str1, str2))

//---------------------------------12 END -------------------------------------------------

