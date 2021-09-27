# Practice 3

## In the practice 3, we have to parse the following code with our own words

define listevens function, for loopinitialization, with a counter to go through it, condition if the reminder is equal to 0, print the number is even, else print the number is odd and return the text "Done"

```
def listEvens(list:List[Int]): String ={ 
    for(n <- list){
        if(n%2==0){
            println(s"$n is even")
        }else{
            println(s"$n is odd")
        }
    }
    return "Done"
}
```

define de first and second list of numbers
```
val l = List(1,2,3,4,5,6,7,8)
val l2 = List(4,3,22,55,7,8)
```

run the function listevens with the first list
```
listEvens(l) 
```

run the function listevens with the second
```
listEvens(l2)
```
Define afortunado function, create a variable equal to 0, cycle initialization with a variable that is less than the length of the list, condition if n is equal to 7, variable res is equal to itself plus 14, else variable res is equal to itself plus variable n and return values of res
```
def afortunado(list:List[Int]): Int={
    var res=0
    for(n <- list){
        if(n==7){
            res = res + 14
        }else{
            res = res + n
        }
    }
    return res
}
create a variable that contains a list
```

```
val af= List(1,7,7)
```

print afortunado function whit values of af variable 
```
println(afortunado(af))
```

define balance function and two variables, returns the sum of the non-null values in the list and save in segunda, use an for loop i, with a range 0 to list range, in the if condition, if first and second are equal, it returns a true and if the data is not the same, it returns a false

```
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
```

val bl = List(3,2,1)
val bl2 = List(2,3,3,2)
val bl3 = List(10,30,90)

balance(bl)
balance(bl2)
balance(bl3)

function to know if a word is a palindrome, if it is, it will return a boolean value
```
def palindromo(palabra:String):Boolean ={
    return (palabra == palabra.reverse)
}
```

val palabra = "OSO" //define words
val palabra2 = "ANNA"
val palabra3 = "JUAN"

println(palindromo(palabra))//print the result of function 
println(palindromo(palabra2))
println(palindromo(palabra3))
