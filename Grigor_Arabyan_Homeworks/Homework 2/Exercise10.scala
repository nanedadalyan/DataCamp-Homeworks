var zar = Array(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15
  ,16,17,18,19,20,21,22,23)
// Looping over the array getting each integer, than counting all numbers that we can divide on that
// we count if the count i
def isPrime(myval:  Array[Int]) : Unit = {
  println(myval.mkString(" "))
  for (each_digit <- myval){
    var counter = 0
    for (i <- 1 to each_digit){
      if (each_digit%i == 0){
        counter += 1
      }
    }
    if (counter == 2){
      println(each_digit, "iseven")
    }
  }
}
isPrime(zar)