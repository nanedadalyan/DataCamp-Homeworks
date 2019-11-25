
def dis(arr:Array[Int]):Array[Int] = {
    var b = arr.toBuffer                                       // create arrayBuffer equal to arr
    for (i <- 0 to arr.length-2){
        for(j <- i+1 to arr.length-1){                         // loop over array and compare elements
            if(arr(i) == arr(j) && (b.count(_ == arr(i)) > 1)){    // if in input array are duplicate elements and
                                                                   // in buffer array that element is more than 1,
                                                                   // substract that element from buffer.
                b -= arr(i)
            }
        }
    }
    return b.toArray                                               // return array
}












