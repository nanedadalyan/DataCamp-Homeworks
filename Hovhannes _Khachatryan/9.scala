
import scala.collection.mutable.ArrayBuffer
def filter (arr:Array[Int]):Array[Int] = {
    var b =  new ArrayBuffer[Int]            // create empty arrayBuffer
    for(i <- 0 until arr.length){            // itereate over array 
        if(arr(i) > 4 && arr(i) < 7){        // if condition add to arrayBuffer b
            b += arr(i)
        }
    }
    return b.toArray                           // return array
}


