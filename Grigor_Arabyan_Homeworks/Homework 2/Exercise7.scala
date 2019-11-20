var my_string = Array("Banan", "Apple", "Trees","Love", "motivation", "Energy")
// pass array through the function and using   .take(3)  print first three
// items of list
def firstThree(myvar: Array[String]) : Unit = {
  //   println(myvar.mkString(" "))
  println((myvar.take(3)).mkString(" "))
}
firstThree(my_string)