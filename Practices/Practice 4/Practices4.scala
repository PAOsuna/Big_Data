//Fibonacci 1
def fib1(n:Int):Int = if(n<2) 
    1
    else fib(n-1)+fib(n-2)

(fib1(5))

//fib in which int returns an int is equal if n is less than 2, returns 1, else n minus 1, plus, the fib of n minus 2

//Fibonacci 2
import scala.math.sqrt
def sqrt(25)
pow(5.0,2.0)
pow 5

def fib2(n:Int):Int = if (n<2) 1 else 
def p: Double = ((1 + sqrt(5))/2) 
def j: Int = (p(pow n) - ((1 - p)pow n) / (sqrt (5))) 3

(fib2(5))

sqrt (5)

math.pow(5)

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