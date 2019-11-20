var your_arr = Array (1,2,3,3,3,4,5,5,200,200,200,25,25,25,25)
​
def frequencies (yourvalue: Array[Int]) : Unit = {
  // Create an empty Map wich takes keys and values as integers
  var frequences_map = collection.mutable.Map[Int, Int]()
  //   Iterate over our Array
  for (each_digit <- your_arr ){
    //     if the digit exist as a key in array add to it's value 0
    if (frequences_map.contains(each_digit)){
      frequences_map(each_digit) +=1
    }else{
      // else create a key with that integer from array and add 1 to it's value
      frequences_map += (each_digit -> 0)
    }
  }
  print(frequences_map)
}
frequencies(your_arr)
