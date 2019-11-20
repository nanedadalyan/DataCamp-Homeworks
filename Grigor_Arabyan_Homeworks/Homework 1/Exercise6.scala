import scala.collection.mutable.ArrayBuffer
// I am getting an input and the number how much should i remove
//i am generating an array from 1 to the given number
// then i am calling the numbers in decreasing order
def removingFunc(listlen: Int, rotationnumb: Int):Unit = {
  println(listlen, rotationnumb)
  var num_arr = ArrayBuffer[Integer]()
  for (i <- 1 to listlen){
    num_arr += i
  }
  var temporrary_arr = num_arr
  println(num_arr, "that ar")
  println(temporrary_arr, "tempArr")

  for (j <- 0 to num_arr.length -1){
    if ((j - rotationnumb) <= -1){
      print(j, "J is this")
      print(j - rotationnumb, "to see what is in minus")
      var index = j
      var some_var = (j - rotationnumb).abs
      print(some_var,"this is our abs")
      print(temporrary_arr(num_arr.length - some_var) ,"this is going to become")
      print(num_arr(j),"this")
      temporrary_arr(num_arr.length - some_var) = num_arr(j)
    }else{
      temporrary_arr(j - rotationnumb) = num_arr(j)
      print(j, "J is this in else")
    }
    print(temporrary_arr)
  }
}
removingFunc(7,4)