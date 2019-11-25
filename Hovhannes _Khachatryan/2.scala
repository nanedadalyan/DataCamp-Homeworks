
def recsum(n:Int):Int = {
    if (n < 1) 0 else n+recsum(n-1) // if n < 1 return 0 else add n with n-1
}
// running time: 1 step for n < 1, 1 step for return, 1 step for n+recsum(n-1), all these n times, total = 3n 








