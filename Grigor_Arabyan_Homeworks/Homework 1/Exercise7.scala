def removingfunc(users_string: String, listlen: Int):Unit = {
  var occurences = collection.mutable.Map[Char, Integer]()
//​ i am getting the stirng and numbers as inputs then i divide the n on the length of the string
//  so i will se how many times i can multiple the string , when i multiple and get the long string which is repeating
//  i am creating an empty map, and loop over my long string checking if the letter exists in map
//  add 1 to it's value , if not create a new key with a value 0
//  In the end printing the Map
  if (users_string.length >= listlen){
    return "It is impossible to run"
  } else {
    var words_len = users_string.length
    var multiply_times = listlen/words_len
    var extended_string = users_string * multiply_times
    for (each_letter <- extended_string){
      if (occurences.contains(each_letter)){
        occurences(each_letter) += 1
      }else{
        occurences += (each_letter -> 0)
      }
    }
  }
  print(occurences)
}
removingfunc("adidas", 23)