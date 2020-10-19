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

def fib3(n:Int):Int={
var n : Int = 7
var a = 0
var b = 1
var c = 0
var k = 0 


    for(k <- 1 to n) {
        
        c = b + a
        a = b
        b = c 
    }
     return a
}
println(fib3(n))