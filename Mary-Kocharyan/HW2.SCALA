//Exercise 1

/*In terms of space and time complexity,I think that iterative method is more preferable, because the recursive method has time complexity
of O(n), as the function does n recursive calls, and the space complexity is O(n), because after every call we return a 
new answer,untill the recursion is over.

In the iteartive one we have time complexity of again O(n), because we have a "for" loop and n times of multiplications,
but here the space complexity is O(1),as we only take one variable(in my code it's called "factor",I have another variable
"f", which is the same as our argument "n", because n has val type, and it can't be reassigned),so all the multiplications are
done using only this variable.*/

def fact(n:Int): Int={
  var factor = 1
  var f = n
  while (f > 0) {
    factor = factor * f 
    f = f - 1
  }
  return factor
}

fact(5)

//Exercise 2

/*This code is used to calculate the sum of 1 to n numbers recursively. If the number is 1 or less, the program returns us 
that number, otherwise the initial sum is n, and during every call we add n-1 to the sum. When n becomes 1, 
the function adds 1 to the sum and returns it.*/

def summ(n:Int): Int={
  if (n <= 1){
  return n
  } else {
    return n + summ(n-1)
  }
}


summ(7)

//Exercise 3

/*To create all numbers with their frequencies I have used a mutable Map. For each element of the array the program creates a 
key with value 1, if the key is already created and we find the same element for the second, third or etc. time, every time we
add 1 to its value.*/

import scala.collection.mutable.Map

def frequencies(arr:Array[Int]): Map[Int, Int] = {
  var newmap = Map[Int,Int]()
  arr.foreach {
    number=> if (newmap.contains(number)) {
      newmap(number) +=1}
    else { newmap(number) = 1}
      }
return newmap
  }

var arr = Array(1,1,1,2,3,6,5,8,8,8,7)
frequencies(arr)


//Exercise 4

/*The program calculates sums of all possible combinations, and if the sum is 12, then the counter is added by one.In the end we 
return count.This is a recursive function which every time takes one value as a sum and adds the remaining numbers, checking if
the sum is 12.*/

var count = 0
def subsum(arr:Array[Int], l:Int, r:Int, sum:Int = 0):Int = {
  if( l > r) {
    if (sum == 12){
      count = count +1
    }
  }
  else{
  subsum(arr, l+1, r, sum + arr(l))
  subsum(arr, l+1, r,sum)
  }
  return(count)
}

var arr = Array(5, 4, 3,8)
var n = arr.length
subsum(arr, 0, n - 1) 


//Exercise 5

/* 1.In Selection sort I think the time complexity is always O(n**2), because no matter the initial array is sorted or not,
it finds the minimum of the array using two nested loops.

2. For Insertion sort the best case is when the array is already sorted,because after the program checks that
the element is greater than the one on its left side, it passes to the next element doing only n times of comparisons.
So the best case is O(n).
The worst case is when the initial array is in a descending order,because after comparing the element with its left one, it 
continues reaching the very beginning of the array and inserts the element there. Every next element is
compared with the whole array situated in its left, so the time complexity is O(n**2).
In the avreage both algorithms have average time complexity O(n**2)*/

def selection(arr:Array[Int]):Array[Int]={
  var n = arr.length
  for (i <- 0 to n-1){
    var min = i
    for (j <- i+1 to n-1){
    if (arr(min) > arr(j)){
    var temp = arr(min)
      arr(min) = arr(j)
      arr(j) = temp
    }
}
  }
  return arr
}


def insertion(arr:Array[Int]): Array[Int] = {
  for ( i <- 1 to arr.length-1){
  var b = arr(i)
  var j = i
  while (j > 0 && arr( j - 1) > b) {
    var temp = arr(j-1)
    arr(j-1) = arr(j)
    arr(j) = temp
    j-=1
  }
  }
  return arr
}

var a1 = Array(7,6,5,4,3,2,1)
var a2 = Array(2,1,7,5,4,6,3)
var a3 = Array(1,2,3,4,5,6,7)

selection(a1)
selection(a2)
selection(a3)
insertion(a1)
insertion(a2)
insertion(a3)

//Exercise 6

// This function creates an empty array, and using a for loop it appends the index of the string with even length to the array 

def even(ar: Array[String]): Array[Int] ={
  var n:(Array[Int]) = Array()
  for (i <- 0 to ar.length - 1){
    if (ar(i).length % 2 ==0)
    n = n:+ i
  }
  return n
} 


//Exercise 7
//Here I have used the take() method, which is a way of slicing arrays or lists.

def firsthree(arr:List[Int]): List[Int] ={
  return arr.take(3)
}

//Exercise 8

/*Lists are immutable, but have a dynamic size and keep order.I think Lists are usable with time series. For example if we want to 
examine exchange rates and to know what value it takes after a second,lists are good to use.

Arrays are collections with mutable values and fixed size, they keep order and can have duplicates. In terms of conviniency
I think arrays are very usable, because though their size is fixed, we can append new values to them by reassigning. 
For example if we want to keep the names of our employees we can use an Array and change it in the future.

Sets are collections which don't contain duplicates and have no order I think Sets can be usable when we are interested in the 
values, not in their frequency,quantity or order. For example if we have a data and we want to get the max and min, we can put it 
in a set, so it will contain no duplicates and we will get the max or min more easily.*/ 


//Exercise 9

//An empty array is taken, and if the number is > 3 and <7, it is added to the new array.

def filtered(arr:Array[Int]): Array[Int] ={
  var filt:(Array[Int]) = Array()
  arr.foreach( num=> if (num > 4 && num < 7){
    filt = filt :+ num
  }
              )
  return filt
}


//Exercise 10

/*At the starting point of the function the divisor = 0. Checking the condition it takes value n-1, and n is devided by n-1 untill
it is devided with no remainder. When meeting this case, the program returns false(the number is not prime), but if there is no number
before our n that n is divided by without ramainder,it returns true, so the n is prime.*/

def isPrime(n:Int, d:Int = 0): Boolean ={
  var div = d
  if(div == 0) {
    div = n - 1
  }
  if (div >= 2){
    if (n%div == 0) {
      false
    } else {
      isPrime(n,div-1)
    }
} else {
    true
  }
}

//Exercise 11

/* This program is used to remove the elements which have duplicates,meaning that it removes the element at all, without keeping
one sample. I have used a mutable map, which indicates the frequencies of the elements.The program then returns only the keys with
frequencies qeual to 1.*/

import scala.collection.mutable.Map

def duplicate_remove(arr:Array[Int]): Array[Int] = {
  var newarr:Array[Int] = Array()
  var newmap = Map[Int,Int]()
  arr.foreach {
    number=> if (newmap.contains(number)) {
      newmap(number) +=1}
    else { newmap(number) = 1}
      }
newmap.keys.foreach{ key=> 
  if (newmap(key) == 1) {
    newarr = newarr:+ key
  } 
                    }
  return newarr
  }


//Exercise 12

/*To find the number of common characters of two strings I have used two substractions. When we use s2-s1 it returns us the 
characters that are not common. And if we use s2-(s2-s1),it gives us the remaining common characters.*/

def common(s1:String, s2:String): Int={
  return ((s2 diff (s2 diff s1)).length)
}
