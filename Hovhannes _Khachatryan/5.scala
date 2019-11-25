
import scala.util.control.Breaks._
def bubbleSort(arr:Array[Int]):Array[Int]={
    var len = arr.length
    var swap = false
    breakable {
        for(i <- 0 until len-1){             // we itereate over array and compare each element with it's next,
        for(j <- 0 until len - i - 1){       // if greater, we swap the elements. We continue until the array is sorted.
            if(arr(j)>arr(j+1)){
               val temp = arr(j)          
                arr(j) = arr(j+1)
                arr(j+1) = temp
                swap = true
        }
}
            if(swap == false){
                break
            }
}
}
    return arr
}
//  If array is sorted, the running time is O(n), because we iterate only once. In other cases we have O(n^2) time complexity.

def selectionSort(arr:Array[Int]):Array[Int]={
    var len = arr.length
    for(i <-0 until len){
        var min = arr(i)
        for(j<- i+1 until len){                  // In each iteration we find minimum of Array(i...n) and place it in the
            if(min>arr(j)){                       // begining. i = 0,1,2..n
               val temp = arr(j)
                arr(j) = arr(i)
                arr(i) = temp 
            }
        }
}
    return arr
}
// In all cases selection sort algorithm has O(n^2) time complexity because none of the loops depend on the data in the array.




