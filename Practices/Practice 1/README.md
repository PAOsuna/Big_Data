# Practice 1

#### 1. Desarrollar un algoritmo en scala que calcule el radio de un círculo

Declaramos la variable circu qué sería el valor del área del círculo, y realizamos la operación adecuada con el despeje de la fórmula.
Por último imprimimos el valor del radio.


```
val circu = 15
val pi = 2*3.1416
val rad = circu / pi


println(rad)
```

#### 2. Desarrollar un algoritmo en scala que me diga si un número es primo

declaramos el numero que revisaremos y el contador, teniendo en cuenta que un numero es primo si solo puede dividirse entre si mismo y 1 y qué el residuo sea 0 se inicia un bucle que si se cumplen ciertas condiciones y nos dirá si el numero es o no es primo, 

```
val numero = 3
var cont = 0
for(i <- Range(1, número + 1)) {
 if( num % i == 0) {
   cont += 1
 }
}
if(cont != 2) {
 println("Numero no primo")
} else {
 println("El numero es primo ")
}
```

#### 3. Dada la variable bird = "tweet", utiliza interpolación de string para imprimir "Estoy escribiendo un tweet"

asignamos el valor tweet a la variable bird y la interpolamos con el texto estoy escribiendo un en la variable interpolar

```
val bird = "tweet"
val interpolar = "Estoy escribiendo un "+ bird
```

#### 4. Dada la variable mensaje = "Hola Luke yo soy tu padre!" utiliza slilce para extraer la secuencia "Luke"

asignamos el valor a la variable mensaje, utilizamos la función slice para traer los datos del rango 5 a 9

```
val mensaje = "Hola Luke yo soy tu padre!"
mensaje slice (5,9)
```

#### 5. Cual es la diferencia entre value y una variable en scala?


el objeto asignado a un valor no puede ser reemplazado, y el objeto asignado a una var puede, sin embargo, dicho objeto puede tener su estado interno modificado.


#### 6. Dada la tupla (2,4,5,1,2,3,3.1416,23) regresa el numero 3.1416 

creamos la tupla, y mandamos llamar la posición 7, en este caso 3.1416

```
val x = List(2,4,5,1,2,3,3.1416,23)
x(7)
```
