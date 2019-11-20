import scala.collection.mutable.ArrayBuffer
// First create an Array
//then create a function, where it gets 2 values - > arr  and K (which is
// the sum that we want to check for)
​
var your_arr = Array (1,2,3,3,3,4,5,6)
def comboz (arr: Array[Int], K: Int): Unit = {
  println(arr, K)
  // let's see what does the variable below
  //   the toSet[Int] converts our Array into sets, with subsets we create multiple sets in our List
  // whith all possible combos, then we make them into Lists with map(_.toList) and then we
  //   make the whole thing into list with toList command
  var into_subsets = arr.toSet[Int].subsets.map(_.toList).toList
  //   Iterate on every sub List
  for (each_pair <- into_subsets ){
    //    Since we can't call .sum method on empty list we avoid the first List which is empty
    if (each_pair.length != 0){
      //    Check if sum of sublist equals to our imput number
      if(each_pair.sum == K){
        //    If yes print it :)
        print(each_pair)
      }
    }
  }
}
comboz(your_arr, 18)