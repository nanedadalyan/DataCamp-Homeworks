


def iseven(arr:Array[String]):Unit = {
for(i <- arr){                               // loop in array
    if(i.toCharArray.length%2==0){          // toCharArray.length generates array of chars and calculates length
        println(arr.indexOf(i))             // indexOf finds index of string i
    }
}
}


















