//-------------Task 1 ———————————————————————-------------------------------------------
def grid(r:Int, c:Int):Unit={
  for (i <- 1 to r)
    println("_"*c)
}

grid(4, 7)


//-------------Task 2 ———————————————————————-------------------------------------------

def reverse(arr:Array[Int]):Array[Int]={
  var temp=0
  for (i <- 0 to (arr.length/2)){
    temp = arr((arr.length-1)-i)
    arr((arr.length-1)-i)=arr(i)
    arr(i)=temp
    
  }
  return arr;
}

var arr = Array(2, 4, 7, 2, 4, 5)
reverse(arr)

//-------------Task 3 ———————————————————————-------------------------------------------

def consecutiveLength(arr:Array[Int]):Int={
  var temp = 0
  var len = arr.length
  var lst:List[Int] = List()
  for (i <- 0 until len-1){
    if(arr(i) > arr(i+1)){
      temp = arr(i)
      arr(i) = arr(i+1)
      arr(i+1) = temp
    }
  }
  temp = 1
  for (i <- 0 until len-1){
    if(arr(i)==arr(i+1)-1)
      temp+=1
    else
      lst = lst:+temp
  }
  temp = lst(0)
  for (i <- 1 to lst.length-1){
    if (lst(i)>lst(i-1))
      temp = lst(i)
  }
  return temp
}

var arr = Array(1, 3, 5, 6, 7, 9, 8, 10, 11, 34, 55)
println(consecutiveLength(arr))

//-------------Task 4 ———————————————————————-------------------------------------------

def minMax(lst:List[Int]):Unit = {
  var minimum = lst(0)
  var maximum = lst(0)
  for (i <- 1 to lst.length-1){
    if (lst(i)>maximum)
      maximum = lst(i)
    if (lst(i) < minimum)
      minimum = lst(i)
  }
  return println(minimum, maximum)
}

var lst = List(1, 3, 5, 6, 7, 9, 8, 10, 11, 34, 55)
println(minMax(lst))

//-------------Task 5 ———————————————————————-------------------------------------------

def evenOdd(arr:Array[Int]):Array[Int]={
  var arrSorted = new Array[Int](arr.length)
  var len = arr.length
  var left = 0
  for (i <- 0 to arr.length-1){
    if (arr(i)%2 == 0){
      arrSorted(left) = arr(i)
      left += 1
    }
    else{
      arrSorted(len-(i+1 - left)) = arr(i)
    }
  }
  return arrSorted
}

var arr = Array(1, 3, 5, 6, 7, 9, 8, 10, 11, 34, 55)
println(evenOdd(arr).mkString(","))

//-------------Task 6 ———————————————————————-------------------------------------------

def armstrong(n:Int, arr:Array[Int]): Unit ={
  var testcase = 0
  for (i <- arr){
    var hun = i/100 // 371/100 = 3 371%100 - 3
    var tenth = i/10 - 10*hun //371/10 37
    var digit = i%10 
    if(scala.math.pow(hun, 3) + scala.math.pow(tenth, 3) + scala.math.pow(digit, 3) == i)
    println(i + " yes")
  else
    println(i + " no")
}
}


var arr = Array(371)
armstrong(1, arr)

//-------------Task 7 ———————————————————————-------------------------------------------

def arrAverage(arr:Array[Int]):Int={
  var sum = 0
  var count = 0
  for (i <- arr){
    sum += i
    count += 1
  }
  return (sum/count)
}

var arr = Array(1, 1000000, 5, 6, 7, 9, 8, 10, 11, 34, 55)
arrAverage(arr)

//-------------Task 8 ———————————————————————-------------------------------------------

def sortCheck(arr:Array[Int]):Boolean={
  var sorted:Boolean = true
  for (i <- 0 to arr.length-2){
    if(arr(i)>arr(i+1))
      sorted = false
  }
  return sorted
}

var arr = Array(1, 2, 3, 4)
sortCheck(arr)
