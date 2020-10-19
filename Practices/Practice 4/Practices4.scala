//Fibonacci 1

//lgoritm recursive version 
val n=10 //define value of n, this is the fibonacci series
def fib1(n:Int):Int={// define fib1 function whit one condition
    if(n<2)// if n less than 2 return n value
    {
        return n
    }
    else //else return fib n - 1 + fib n - 2
    {
        return(fib1(n-1)+fib1(n-2))
    }
}
println(fib1(n))
//fib in which int returns an int is equal if n is less than 2, returns 1, else n minus 1, plus, the fib of n minus 2

//Fibonacci 2 dont work

val n = 8 //define value of fibonacci in val n
var phi=((1+math.sqrt(5))/2) //in variable phi we assign 1 + square root of 5 and all divide by 2
var j=((math.pow(phi,n)-math.pow((1-phi),n))/(math.sqrt(5))) // In var j we assign the value of var phi squared - (1 - phi)squared and divided by square root of 5


def fib2(n:Double) : Double ={//define de fib2 function 
if (n<2){//if n is less than 2 return value of n, because fib 0 = 0 and fib 1 = 1
return n
}
else {//else return j value

    return j
}
}
println(fib2(n))//print final result of fib2 function

//Algorithm 3 Iterative version

def fib3(n:Int):Int={// define fib3 function and receives a value n
var n : Int = 7 // fibonacci number 
var a = 0 //variable 1 
var b = 1 //variable 2
var c = 0 //variable 3 



    for(k <- 1 to n) {//initialize the cycle k from 1 to n 
        
        c = b + a // c(0) equal to b(1) plus a(0), result c=1 
        a = b // a takes value of b
        b = c  // b takes value of c 
    }
     return a //return a value 
}
println(fib3(n))//print final result of fib3 function 

//Algoritm 4 iterative version 2 variables

def fib4(n:Int):Int={ //define fib4 function 
var n : Int = 6 //define fibonacci number, in this case 6
var a = 0 //define variable 1
var b = 1 //define variable 2 

for (k <- 1 to n){//initialize the cycle for k to n 
    b = b + a //b is 1, so b is equal to b(1) plus a(0), result 1
    a = b - a //a is 0 , so a is equal to 1 - 0, result 1 and the cycle starts again with new values, in this case b=1 and a=1 
}
return a //return a value 
}
println(fib4(n))//print the result of fib4 function


def fib5(n:Int):Int={//define fib5 function 
    var n = 7 //define n value
    var vector = new Array[Int](n+1) //create array of n+1 positions
    vector(0) = 0 // We set the position 0 and 1 of the vector to 0 and 1 respectively, since fibonacci of 0 = 0 and 1 = 1
    vector(1) = 1
    if(n< 2){ //if value of n is less to 2 return the same value of n
        return n 
    }
    for( k <- 2 to n){ //for loop to loop through vector values
        vector(k) = vector(k - 1) + vector(k - 2) //fibonacci operation with vector values 
        //since the value is 7, the fibonacci value of 6 = 8 and the fibonacci value of 5 = 5 are added and the result is 13
    }
    return vector(n)//return the value of vector in n position
}
println(fib5(n))//print the final result of fib5 function