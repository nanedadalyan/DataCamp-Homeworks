/*
    Homework #2
    Harvey Bejanyan
*/


/**********************************************  Problem 1  ************************************************/
// Create function that calculates factorial without using recursion

// Non-recursive
def fact(n: Int): Int = {
    var num = n; var fact = 1
    if (n == 0) return fact
    else {
        for (i <- 1 to n-1) {
            fact *= num
            num -= 1
        } 
    }
    return fact
}

// Recursive
def recursiveFact(n: Int): Int = {
    if (n == 0) return 1
    else return n * recursiveFact(n-1)
}

time(assert(fact(5) == 120))  // running time: 301401 nanoseconds
time(assert(recursiveFact(5) == 120))  // running time: 6444 nanoseconds

/*  The recursive algorithm runs in significantly less time because the program
    has less steps to execute than the non-recursive one. Also, less memory is allocated since 
    variables are not even needed for the recursive program. However, if the input is a very large number,
    then the recursive program may crash because too many recursive calls will be pushed onto the
    stack before the base case is even reached. 
*/



/**********************************************  Problem 2  ************************************************/
// Calculate sum of n numbers using recursion

def recursiveSum(n: Int): Int = {
    if (n == 0) return 0
    else return n + recursiveSum(n-1)
}

time(assert(recursiveSum(6) == 21))  // running time: 14850



/**********************************************  Problem 3  ************************************************/
// Suppose you have an array with integers. 
// Create function/method that will create another data type containing number with it frequencies

import scala.collection.immutable.Map

def numFrequency(arr: Array[Int]): Map[Int, Int] = {
    var freqMap: Map[Int,Int] = Map()
    var distinct: Array[Int] = arr.distinct
    var freqCount = 0
    for(i <- 0 to distinct.length-1) {
        for (j <- 0 to arr.length-1) if (distinct(i) == arr(j)) freqCount += 1
        freqMap += (distinct(i) -> freqCount)
        freqCount = 0
    }
    return freqMap
}

assert(numFrequency(Array(1,1,1,2,2,3,4,5,5,5,5)) == Map(5 -> 4, 1 -> 3, 2 -> 2, 3 -> 1, 4 -> 1))



/**********************************************  Problem 4  ************************************************/
// Create function that will find out how many cases are in array where sum of numbers is 12

/* 
   This was taking me a while to figure out, and curiosity was driving me crazy
   so I eventually scoured the web looking for some hints.
   I ended up finding a clean and effective algorithm to use as inspiration 
   in writing this code, and so, I do not take full credit for the following
   
   Steps:
   It starts by adding the first element of our array into a list of the partial sum.
   The partial sum is passed into a recursive call along with the remaining elements that have not been considered yet.
   Elements keep getting recursively added one by one from the "remaining" array to the partial sum until the sum 
   reaches the target, passes the target, or the "remaining" array is exhausted. If the recursive call hits one of the 
   two base cases and returns due to reaching or passing the target, then it returns to the point before that last 
   element was added. Now, it is back in the previous caller's for-loop, where it increments past that last element which 
   caused it to reach or surpass the target. Now, again more elements are added recursively until one of the base cases 
   is triggered or the end of the array is reached. If the end of the array is reached then that recursive call is done 
   and it returns to its caller which, in turn, continues the process, or returns back to its own caller if it too has 
   reached the end of the array and exhausted its "remaining" array of elements. This one is a bit hard to explain in
   writing because of the recursive calls within the for-loops.
  
*/

def sumsOf12(arr: Array[Int], target: Int, partial: List[Int]) {
    var sum = partial.sum 
    var n = 0
    var remaining = Array[Int]()
    if (sum == target) println(s"$target  =  " + partial.mkString(" + "))     // Base case 1
    if (sum >= target) return          // Base case 2
                             
    for (i <- 0 to arr.length-1) {
        n = arr(i)
        remaining = arr.slice(i+1,arr.length)
        sumsOf12(remaining, target, partial :+ n)
    }
}

sumsOf12(Array(3,1,3,4,10,2,6,4), 12, List())



/**********************************************  Problem 5  ************************************************/
// Sort array in ascending order using different sorting methods (choose 2 different algos)

/*******  Algorithm #1 - Merge Sort  *******/

def merge(arr: Array[Int], l: Array[Int], r: Array[Int]): Array[Int] = {
    var i = 0; var j = 0
    while (i + j < arr.length) {   
        if(j == r.length || (i < l.length && l(i) < r(j))) { 
            arr(i + j) = l(i)
            i += 1; 
        } 
        else { 
            arr(i + j) = r(j)
            j += 1; 
        } 
    }
    return arr
}

def mergeSort(arr: Array[Int]): Array[Int] = {
    if (arr.length == 1) return arr
    var left = arr.slice(0, arr.length/2)
    var right = arr.slice(arr.length/2, arr.length)
    left = mergeSort(left)
    right = mergeSort(right)
    return merge(arr, left, right) 
}

val d = Array(10,4,3,8,6,9,11,15,13)
time(mergeSort(d))   // running time: 217439 nanoseconds

/*
    a. Reverse Order: (nlogn)     b. Unsorted: (nlogn)     c. Sorted: (nlogn) 
    
    Merge sort has a complexity of nlogn in all three cases because it always recursively
    divides the list into halves and then takes linear time to merge each of those halves. 
    The recursive dividing produces (logn+1) sets of halves, each takingn time to merge 
    in sorted order. Thus, it has (nlogn) complexity after dropping the low-order 1
*/


/*******  Algorithm #2 - Insertion Sort  *******/

def swap(a: Array[Int], i1: Int, i2: Int) = { 
    val tmp = a(i1) 
    a(i1) = a(i2)
    a(i2) = tmp 
}

def insertionSort(arr: Array[Int]) = {
    var j = 0
    for (i <- 1 to arr.length-1){
        j = i
        while(j > 0 && arr(j) < arr(j-1)){
            swap(arr, j, j-1)
            j -= 1
        }
    }
}

val e = Array(10,4,3,8,6,9,11,15,13)
time(insertionSort(e))   // running time: 4288720 nanoseconds

/*
a. Reverse Order: (n^2) -->   During every iteration of the inner loop, the left-most element 
                              of the unsorted section will have to be compared and swapped with 
                              every element of the sorted section until it reaches the front.
                                  
b. Unsorted: (n^2) -->   Even in the average case, the inner loop still compares and swaps the 
                         right-most element from the unsorted section with elements to the left 
                         of it until there are no more values greater than itself.
                            
c. Sorted: (n) -->   N complexity because the right-most element of the sorted section is only 
                     compared once to the left-most element of the unsorted section, thus, 
                     n comparisons.
*/



/**********************************************  Problem 6  ************************************************/
// Create a function that will calculate the length of strings in an array and
// will output indexes of strings having even length

def evenLength(arr: Array[String]): List[Int] = {
    var indexes = List[Int]()
    for (i <- 0 to arr.length-1) if (arr(i).length % 2 == 0) indexes = indexes :+ i
    println("Indexes are: " + indexes.mkString(", "))
    return indexes
}

assert(evenLength(Array("hello", "play", "from", "bye")) == List(1, 2))



/**********************************************  Problem 7  ************************************************/
// Create function that will return the first 3 items in a list

def firstThree(list: List[Int]): String = {
    var s = list(0).toString
    for (i <- 1 to 2) if (list(i) != null) s = s + ", " + list(i).toString
    return s
}

assert(firstThree(List(1, 2, 3, 4, 5, 6, 7)) == "1, 2, 3")



/**********************************************  Problem 8  ************************************************/
// Describe when it is more convenient to use array vs list vs set.
/*
    Array -  Since arrays are stored as a single block of memory, they are fast and efficient when mutable.
             However, if you want to change it to an immutable array, the array becomes much less efficient.
             Therefore, it is best to use "Arrays" when the values of the elements need to be updated, but
             the number of elements do not.
    
    List -  Lists in Scala are immutable singly-linked lists, which make it efficient to add new elements to
            it since the last node in the lists can be assigned to point to the new element. This way a whole new 
            structure doesn't have to be created in order to make space for an additional element like it would
            for an array. Therefore, it is best to use "Lists" when the values of the elements do not need to be
            updated, but the number of the elements do.
    
    Set -  Sets are best to be used when the values need to be distinct, since duplicates are not allowed in sets. 
           Although sets are immutable by default, they can also become mutable by importing 
           "scala.collection.mutable.Set" 
*/



/**********************************************  Problem 9  ************************************************/
// Filter elements in an array of integers where >4 and <7.

var arr = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
var list = List[Int]()
for (i <- 0 to arr.length-1) {
    if (arr(i) > 4 && arr(i) < 7) list = list :+ arr(i)
}



/**********************************************  Problem 10  ************************************************/
// Write a recursive function called isPrime that returns a Boolean and lets you know 
// whether or not a number is prime

def isPrime(n: Int, div: Int): Boolean = {
    if (n <= 2) {
        if (n == 2) return true   // Base case 1
        else return false
    }
    
/*  Base case 2 optimizes the program by cutting out unnecessary iterations.
    If div has incremented up to the 'square root of n' and no divisors have yet been found, 
    then none exist for 'n' either. No need to check any further  */
    
    if (div > math.sqrt(n)) return true   // Base case 2
    
    if (n % div == 0) return false  // Base case 3 
    
    return isPrime(n, div + 1)    // Check the next possible divisor
}

assert(isPrime(23, 2) == true)
assert(isPrime(101, 2) == true)
assert(isPrime(21,2) == false)
assert(isPrime(200, 2) == false)



/**********************************************  Problem 11 ************************************************/
// Write a program that deletes duplicate elements from the array

import scala.collection.mutable.ArrayBuffer
def removeDuplicates(arr: Array[Any]): Array[Any] = {
    var distinct = arr.toBuffer; var j = 0
    
    for (i <- 0 to distinct.length) {
        while (j < distinct.length && i < distinct.length) {
            if (j == i || distinct(j) != distinct(i)) j += 1
            else if (distinct(j) == distinct(i)) distinct.remove(j)
        }
        j = 0
    }  

    var newArray = distinct.toArray
    return newArray
}

removeDuplicates(Array(9,5,1,5,2,3,4,2,1,2,4,10,5,4,3,2,1,3,2,1,10,9,7,4,3,2))



/**********************************************  Problem 12  ************************************************/
// Given two strings, find the number of common characters between them

def findCommonChars(first: String, second: String): Int = {
    var hasMatched: Array[Int] = Array.fill(second.length)(0)
    for (i <- 0 to first.length-1) {
        for (j <- 0 to second.length-1) {
            if (first(i) == second(j) && hasMatched(j) != 1) {
                hasMatched(j) = 1
            } 
        }
    }
    return hasMatched.sum
}

assert(findCommonChars("apple", "pear") == 3)
assert(findCommonChars("apple", "apple") == 5)


