var my_string = Array("Banan", "Apple", "Trees","Love", "motivation", "Energy")
def stringCounter(someval: Array[String]): Unit = {
  //   Loop over the Array
  for (each_string <- someval){
    // ete mnacorde 0 a  tpel ed string@, erkarutyun@ , indexe u  asel vor even a
    if (each_string.length%2==0){
      println(each_string, each_string.length,"Index of "+someval.indexOf(each_string),"iseven" )
    }else{
      //       Ete voch uxxaki tpel string@ u erkarutyun@
      println(each_string, each_string.length)
    }
  }
}
stringCounter(my_string)