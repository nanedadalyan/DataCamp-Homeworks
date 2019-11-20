var zar = Array (1,2,3,4,5,6,7,8,9,4,6,2,11)
println(zar)
def fourtoseven (myval: Array[Int]) : Unit = {
  println(myval.mkString(" "))
  for (each_integer <- myval){
    if(each_integer > 4 && each_integer < 7 ){
      println(each_integer)
    }
  }
}
fourtoseven(zar)
Collapse




