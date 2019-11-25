//1. Create function that calculates factorial without using recursion. What do
//you think about the efficiency of your function compared to recursive one (space complexity, running time).
def factorial(n:Int):Unit = {
  var fact = 1
  for (i <- 2 to n) { fact = fact * i}
  println(fact)
}
//time complexity is n as it loops through n number to multiply all of them. In case of recursive function each time it divides 
//between two branches (n and factorial(n-1)) until 1 so the time complexity is 2^n. The space complexity in case of non-recursive
//is 1 as it doesn't grow with respect to input size; it declared one variable and just multiplies all the numbers in it.
//In case of recursive function each time it calls a function it uses 1 space to input n-th variable, so space complexity is n.


//2. Calculate sum of n numbers using recursion. What is the running time?
//option 1: sum of n consecutive integers (from 1 to n)
def funsum(n:Int):Unit = {
  var sum = 0
  if (n==1) {sum = 1}
  else {sum = n + funsum(n-1)}
  return sum  
}
//option2: sum of n numbers in an array,
def funsum(arr:Array[Double]):Double = {
  var n = arr.length
  var sum = 0.0
  if (n == 1) {
      sum = sum + arr(0)
    }
    else {sum = sum + arr(0) + funsum(arr.slice(1,n))
    }
    return sum
  }
// in both cases the running time is n*(n+1)/2, as it takes every time the first element of the array (and each  new array)
// and that way passes through the array n times, and in the case of slicing it passes (n-1) times through the array. 
// As every time the array becomes one element less, so the total time complexity becomes the sum from 1 to n which is n*(n+1)/2


//3.Suppose you have an array with integers. Create function/method that will create another data type containing number with it frequencies.
import scala.collection.mutable.Map
def funfreq(arr:Array[Int]):Map[Int,Int] = {
// create a new array with distinct elements of input array
  var newarr = arr.distinct
// create a mutable map and input each member of the new array as a key and number of occureneces of that member in input array
  var newmap = Map[Int,Int]()
  for (i <- newarr) {
    newmap += (i -> arr.count(_ == i))
 }
  newmap
}


//4. Create function that will find out how many cases are in array where sum of numbers is 12.
def sum_n(arr:Array[Int],n:Int):Int = {
//create a recursive function with two arguments: array and an integer (in this case 12)
  var len = arr.length
  var count = 0
  if (n==1) {count = 0}
  if (len==1 && arr(0)==n) {count=1}
//base case length is 1 and that one item is int n, discuss cases when n>1
//if base case is not satisfy, create 2 new arrays, first contains elements that are larger
//than n and the second contains the rest. 
  else{
    if (arr.sum<12) {count = 0}
    var arr1 = arr.filter(_>n/2)
    var arr2 = arr.diff(arr1)
//sum of elements should be at least equal to n, if not, count is 0
    if (arr.diff(arr.distinct).contains(n/2)==true) {count +=1}    
//for each element in first array, check if the second array contains the element n-i. 
//if yes add 1 to variable count. Both if it contains n-i and not call the same function
//on the rest of the array this time with integer value of n-i, because we are interested
//in all combinations, not only doubles
    for (i <- arr1.distinct) {
      if (i==n){count+=1}
      if (arr2.contains(n-i)==true) {count +=1}
       count += sum_n(arr2, n-i)    
    }
}
  
  return (count)
}
//ps. has some bugs, counts extra elements.
sum_n(Array(),12)

//5. Sort array in ascending order using different sorting methods (choose 2
//different algos). Write what is running time. Explain the logic behind.
//a. When your array is sorted in reversed order
//b. When your array is unsorted
//c. When your array is already sorted.
//insertion sort
def insertionsort(arr:Array[Int]):Unit = {
  var len = arr.length
  var counter = 0
  for (i <- 1 to len-1) {
    counter += 1
    var item = arr(i)
    var j =  i-1
    while (j>=0 && item < arr(j)){
      counter +=1
      arr(j+1) = arr(j)
      j = j-1
    }
    arr(j+1) = item
    counter +=1
  }
  println(arr.mkString(" "))
  println(counter)
}
//a. time complexity is n^2 as it loops through n-1 items starting from 2nd and if while loop works (which does in case of reversed
// list it switches n items. b.in case of unsorted items it again passes through n-1 items and depending how sorted is the list and
//how big is the array, it will swith n-k items where k can be 0 to n so time complexity is (n-1)*(n-k). c. in case of sorted array
//it only loops through n-1 items and doesn't pass through while loop so time complexity is n.

//bubble sort
def bubblesort(arr:Array[Int]):Unit = {
  var len = arr.length
  var temp = 0
  var counter = 0
  for (i <- 0 to len-2) {
    counter += 1
    for (j <- 0 to len-i-2) {
      counter += 1
      if(arr(j+1)<arr(j)) {
        temp = arr(j)
        arr(j) = arr(j+1)
        arr(j+1) = temp
        counter += 1
      }
    }
  }
  println(arr.mkString(" "))
  println(counter)
}
//a. in this case it pases through (n-1) loops and does (n-1)...1 (on each next loop one time less) switches. so total time 
//complexity becomes n^2. b.In case of unsorted array, the number of switches may be less, however time complexity is still n^2
//as it passes n-1 times through the array and makes n-k switches (k from 0 to n). c. In case of sorted array it passes n-1 
//times through the array and makes no switches, so time complexity is n.


// 6. Create a function that will calculate the length of strings in an array and will
// output indexes of a strings having even length.
import scala.collection.mutable.ArrayBuffer
def printindex(arr:Array[String]):ArrayBuffer[Int] = {
  var len = arr.length
// create a new array with the same size that the original where we will put the lengths of all the strings
  var arroflen = new Array[Int](len)
  for (i <- 0 to len-1) {
    arroflen(i) = arr(i).length
  }
// create a new ArrayBuffer where we will put the indices of the elements from original list that have even length after checking 
//whether the lengths are even in the second array
  var arrofind = new ArrayBuffer[Int]()
  for (j <- 0 to len-1) {
    if(arroflen(j)%2 == 0)
    arrofind += arr.indexOf(arr(j))
  }
  return arrofind
}



// 7. Create function that will return the first 3 items in a list.
def funthree(lst:List[Any]):Unit = {
  for (i <- 0 to 2){
    println(lst(i))
    }
  }


// 8. Describe when it is more convenient to use array vs list vs set.
//Using arrays is convenient when you know initially the size of the sequence and the items in it can be change so you may have to 
// replace one with another (for example after making some calculations). 
// Lists are good to use in case the items in it are stable n and you will not need to change them, however they are not final and 
// you may have to add new elements, for example when you want to make a sequence regarding certain characteristics of entities 
// (memory space, color)
// Sets are good to use when you can't have duplicate values and again you can add items in it, for example ID number, etc.
 

// 9. Filter elements in an array of integers where >4 and <7.
import scala.collection.mutable.ArrayBuffer
def funfilter(arr:Array[Int]):ArrayBuffer[Int] = {
  var len = arr.length
// create new ArrayBuffer where will input only the elements from original array that satisfy the condition.
  var newarr = ArrayBuffer[Int]()
  for (i <- 0 to len-1) {
    if (arr(i)>4 && arr(i)<7) {
      newarr += arr(i)
    }
  }
  return newarr
}


//10. Write a recursive function called isPrime that returns a Boolean and lets you know whether or not a number is prime.
def isPrime(n:Int):Boolean = {
  var m = true
//m is return boolean variable, which is by default true, if a number is not prime and it is divided without remainder to 
//any i number between 2 and number n (not included) then m is false and it is not prime.
  for (i <- 2 to n-1) {
    if (n%i == 0) {m = false}
  }
  return m
  }


//11.Write a program that deletes duplicate elements from the array.
import scala.collection.mutable.ArrayBuffer
def deldup(array:Array[Any]):Unit = {
// first convert array to arraybuffer in order can delete items from it
  var arr = array.toBuffer
// arr1 are the differences between original array and an array of distinct items in it: if an item is repeated more than once it will count
// all repetition
  var arr1 = arr.diff(arr.distinct)
// to totally remove any item that is repeated and not only the repetitions, need to remove the array of repeating items from original one twice
  arr --= arr1
  arr --= arr1
  println(arr.mkString(" "))
}


//12.Given two strings, find the number of common characters between them.
//Example: For s1 = "aabcc" and s2 = "adcaa", the output should be commonCharacterCount(s1, s2) = 3.
def charfun(s1:String, s2:String):Unit = {
// convert strings to array in order can use array.diff method
  var arr1 = s1.toArray
  var arr2 = s2.toArray
// first difference will return all non-repeating elements, second difference will return repeating elements. The length of the last array will
// give our desired value
  var arr3 = arr1.diff(arr1.diff(arr2))
  var count = arr3.length
  println("commonCharacterCount(s1,s2)="+count)
}

