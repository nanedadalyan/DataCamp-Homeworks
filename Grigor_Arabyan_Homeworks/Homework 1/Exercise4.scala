import scala.collection.mutable.ListBuffer
var my_list = ListBuffer[Char]()
// var p1 = List()
for (i <- 'a' to 'z'){
  my_list += i
}
println(my_list)
//Adding numbers in ListBuffer ant printing them