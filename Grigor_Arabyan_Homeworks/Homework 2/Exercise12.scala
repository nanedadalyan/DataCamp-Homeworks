var s1 = "aabcc"
var s2 = "adcaa"
def commonLetters(firstS: String, secondS: String) : Unit = {
  println(firstS, secondS)
  // Creating an Array buffer from the second string to be able to delete letters to get the right count
  var buf = collection.mutable.ArrayBuffer((secondS.split("")):_*)
  // Creating the counter
  var counter = 0
  //   iterate over first string, find if there is any match in  Array buffer add 1 to count and delete that element
  //   making sure to convert j into string because in Arraybuffer letters are in string type but j was char  type
  for (j <- firstS){
    if ( buf.contains(j.toString) ){
      buf -=  j.toString
      counter +=1
    }
  }
  print("commonLetters(s1, s2) = " + counter + "." )
}
