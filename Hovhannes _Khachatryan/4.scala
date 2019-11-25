
def sum12(arr:Array[Int]):Int = {
    var counter = 0
    var b = (0 until arr.length).toList.toSet[Int].subsets.map(_.toArray).toArray// create all combinations of subarray indexes,
    for (i <- 0 until b.length){                                                  // why not for elements? 
    for(j <- 0 until b(i).length){                                          // because if array has duplicates function doesn't
      b(i)(j) = arr(b(i)(j))                                              // work properly. 
    }                                                                     // Then swap each index with its value.
    }                                                                   // Now we have all possible subarray combinations. 
    
    for(k <- b){
        if(k.sum == 12)                                               // check if sum == 12 add 1 to counter
        counter += 1
    }
    return counter
}                                                          // The function works with duplicates as well as with negative values.




















