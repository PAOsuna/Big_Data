// def isEven(num:Int): Boolean = {
//     return num%2 == 0
// }
// def isEven(num:Int): num%2 == 0
// println(isEven(6))
// println(isEven(3))
// Practice 3, analyse the following code with your own words

def listEvens(list:List[Int]): String ={ //define listevens function
    for(n <- list){//cycle initialization, with a counter to go through it
        if(n%2==0){//condition if the reminder is equal to 0
            println(s"$n is even")//print the number is even 
        }else{
            println(s"$n is odd")//else print the number is odd
        }
    }
    return "Done"//return the text :"Done"
}

val l = List(1,2,3,4,5,6,7,8)//define de first list of numbers
val l2 = List(4,3,22,55,7,8)//define de second list of numbers 
listEvens(l) //run the function listevens with the first list
listEvens(l2)//run the function listevens with the second list

//3 7 afortunado

def afortunado(list:List[Int]): Int={//defube afortunado function 
    var res=0//create a variable equal to 0
    for(n <- list){//cycle initialization with a variable that is less than the length of the list
        if(n==7){// condition if n is equal to 7 
            res = res + 14//variable res is equal to itself plus 14
        }else{
            res = res + n//else variable res is equal to itself plus variable n 
        }
    }
    return res//return value of res
}

val af= List(1,7,7)//create a variable that contains a list
println(afortunado(af))//print afortunado function whit values of af variable 

def balance(list:List[Int]): Boolean={
    var primera = 0
    var segunda = 0

    segunda = list.sum

    for(i <- Range(0,list.length)){
        primera = primera + list(i)
        segunda = segunda - list(i)

        if(primera == segunda){
            return true
        }
    }
    return false 
}

val bl = List(3,2,1)
val bl2 = List(2,3,3,2)
val bl3 = List(10,30,90)

balance(bl)
balance(bl2)
balance(bl3)

def palindromo(palabra:String):Boolean ={
    return (palabra == palabra.reverse)
}

val palabra = "OSO"
val palabra2 = "ANNA"
val palabra3 = "JUAN"

println(palindromo(palabra))
println(palindromo(palabra2))
println(palindromo(palabra3))
