// Practice_1/Practica 1
//1. Desarrollar un algoritmo en scala que calcule el radio de un circulo
val circu = 15
val pi = 2*3.1416
val rad = circu / pi


println(rad)

//2. Desarrollar un algoritmo en scala que me diga si un numero es primo
val numero = 3
var cont = 0
for(i <- Range(1, numero + 1)) {
 if( num % i == 0) {
   cont += 1
 }
}
if(cont != 2) {
 println("Numero no primo")
} else {
 println("El numero es primo ")
}
//3. Dada la variable bird = "tweet", utiliza interpolacion de string para
//   imprimir "Estoy ecribiendo un tweet"
val bird = "tweet"
val interpolar = "Estoy escribiendo un "+ bird