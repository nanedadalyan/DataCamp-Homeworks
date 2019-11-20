//The for loop will be more efficient because there is no overhead of the method calls.
//every time you call the method from within itself you push more data onto the stack, without returning from the method you were in
//  (and thus without freeing up the stack space the calling method was occupying). As your recursion goes deeper the amount of memory increases.

//In contrast the for loop you wrote just uses the fixed amount of memory provided by one stack frame push.
//https://stackoverflow.com/questions/7537989/recursion-vs-for-loops-factorials-java (edited)

//def factorial (n : Integer){
//  var fact = 1
//  if(n==1){
//    return 1
//  }else{
//    for (i <- 1 to n){
//      fact *= i
//    }
//    print(fact)
//  }
//}
//factorial(4)