
def isPrime(n:Int,i:Int = 2):Boolean={         
    if(n <= 2){                                  // Base case if n==2, true, if n < 2 false,
        return(n == 2)}
    else if(n % i == 0){                          // The rest is follows:
        return false}                            // if the remainder of n divided by (from 2  to sqrt(n-1)), equals to 
    else if (i * i > n){                         // zero return false else true
        return true
    }
    else return isPrime(n,i+1)
    }
    





