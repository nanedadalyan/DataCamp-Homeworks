
import util.control.Breaks._
def commonCharacterCount(s1:String, s2:String):Int ={
var counter = 0                                           // create counter
var a = s1.toBuffer                                       // convert string to arrayBuffer 
var b = s2.toBuffer    
for(i <- a){                                               // iterate over array, if char i == char j, counter +1,
    breakable{                                             // b - char j (to avoid from multiple count) and break.   
    for(j <- b){                                           
        if (i == j){   
        counter += 1
            b-= j
            break
        }
    }
    }
}
    return counter
}




