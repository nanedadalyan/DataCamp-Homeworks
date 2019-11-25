
def factorial (n:Int):BigInt = {
    var a = 1                            // create variable a equal to 1
    if (n < 2) 1 else for(i <- 2 to n){ // if n < 1 return 1 else multiply a by i
        a = a*i
    }
    a
}

/* 1. Space complexity. The function creates additional variable
   2. running time. 1 step for creating variable a, 1 step for checking n < 2, n steps for i is in Range(2,n), 
   n-1 steps for loop, 
   1 step for each '=' and '+' within a loop, and 1 step for returning a: total steps = 1 + 1 + n + 2*(n-1) + 1 = 3n+1 */    

def recfactoral(n:Int):BigInt = {
    if (n < 2) 1 else n*recfactoral(n-1)
}

/* 1.space complexity. The algorithm doesn't create any variable.
   2. running time. 1 step for (n<2), 1 step for n*recfactorial(n-1), each n times, total steps = 3n */








