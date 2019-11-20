def countFromTo(from:List[Int]):Unit = {
  var p1 = from
//  Create an Array
//  Sort the Array
  p1 = p1.sorted
//  drop the first value which is the min
  p1 = p1.drop(1)
//  drop the Last value which is the Max
  p1 = p1.dropRight(1)
//  Get the sum
  var g = p1.sum
//  print it
  println(g)
}
countFromTo(List(23, 32,25 ,234 ,237, 1, 12, 7))
