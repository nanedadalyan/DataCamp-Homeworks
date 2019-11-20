/*
    Homework #1
    Harvey Bejanyan
*/

// Problem 1
import scala.io.StdIn
def shoppingBasket() {
    var basket: Array[String] = Array.fill(5)("empty")
    var index = 0
    var item = ""
    println("Welcome to your shopping basket! You can add up to five items\n")
    try {
        while (item != "done") {
            item = readLine("Add an item to your basket or type 'done' to exit: ")
            if ( item != "done") {
                basket(index) = item
                index += 1
            }
        }
    } 
    catch {
        case a: IndexOutOfBoundsException => println("Basket is full")
    } 
    finally {
        println("\nHere are the items in your basket:")
        print(basket.mkString(", "))
    }
}

// Problem 2
def stringToInt(x: String): Int = {
    var y = 0
    try {
        y = x.toInt
        println(y)
    }
    catch {
        case a: Exception => println("Cannot convert the entered String to an Int")
    }
    return y
}

// Problem 3
import scala.io.StdIn
def recursiveAlphabet(start: Int, end: Int, downwards: Boolean, seq: String): String = {
    var num = start; var sequence = seq
    println(sequence)
    if (num != 1 && downwards == true) {
        sequence = sequence.dropRight(1)
        return recursiveAlphabet(num-1, end, true, sequence)
    }
    else if (num >= 1 && num < end) {
        sequence = sequence + (num+65).toChar 
        return (recursiveAlphabet(num+1, end, false, sequence))
    }
    else {
        return sequence
    }
}

def calculateSequence() = {
    var letters = ""
    var n = readLine("Enter an integer between 1 and 26: ").toInt
    if (n >=1 && n <= 26) {
        for (i <- 1 to n) letters = letters + (i+64).toChar
        recursiveAlphabet(n, n, true, letters)
    }
    else {
        throw new Exception("The number you entered is not between 1 and 26")
    }
}

// Problem 4
var letters = List[Char]()
for (i <- 97 to 122) {
    letters = letters :+ i.toChar
}

// Problem 5
def modifiedAvg(list: List[Int]): Int = {
    var newList = List[Int]()
    newList = list.filter(_ > list.min)
    newList = newList.filter(_ < list.max)
    return (newList.sum / newList.size)
}

// Problem 6
import scala.io.StdIn
def leftRotation() = {
    var num = ""
    val Array(a, b) = readLine("Input 1: ").split(" ") 
    val n = a.toInt; val d = b.toInt
    var numbers = new Array[String](n)
    if (n < 1 || n > 100000 || d < 1 || d > n ) {
        throw new Exception("n and d values are not valid")
    }
    numbers = readLine("Input 2: ").split(" ")
    for (i <- 1 to d) {
        num = numbers(0)
        numbers = numbers.drop(1)
        numbers = numbers :+ num
    }
    println(numbers.mkString(" "))
}

// Problem 7
def repeatedString(s: String, n: Int) {
    var repeatsOfA = 0
    val multiple = (n / s.length)
    val additionalChars = n % s.length
    var newString = new StringBuilder(s)
    if(multiple >= 2) {
        for (i <- 1 to multiple-1) {
            newString.append(s)
        }
    }
    newString.append(s.substring(0, additionalChars)) 
    for (i <- 0 to newString.length-1) {
        if(newString(i) == 'a') repeatsOfA += 1
    }
    println(s"There are $repeatsOfA occurrences of 'a' in the substring: $newString")
}


