


def frequencies(arr:Array[Int]):scala.collection.mutable.Map[Int,Int]={
val b = arr.distinct                                 // create unique Array of input Array
val counter = Array.fill(b.length)(0)                // create Array of size b with 0s
val map = scala.collection.mutable.Map[Int,Int]()    // create mutable empty map of type[Int,Int] 
for(i <- 0 until b.length){                          // two nested loops: if value in b is in arr, counter += 1 
    for (j <- 0 until arr.length){
        if (b(i) == arr(j)) counter(i) += 1
    }
    map += (b(i) -> counter(i))                     // add b and counter to map
}
    return map
}













