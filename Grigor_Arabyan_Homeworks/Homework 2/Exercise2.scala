println(23)
var num_arr = Array(1,3,4,6,7,10,11,8)
// count sum forward
def findsum(someval: Array[Int], n: Int) : Integer = {
  if (n == someval.length){
    return 0
//   base case if we reach end of array
  }else{
    return someval(n) + findsum(someval, n + 1)
//    # recursive call increment n by one
  }}
findsum(num_arr,0)