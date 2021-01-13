## Introduction

Today it is surprising how large companies use learning algorithms to make recommendations to their users and to provide a better experience for them.
But how are these machine learning algorithms performed? In this work we will use different methods that will help us solve some problems, in this way we will be able to understand how some companies or web pages such as netflix or google use the recommendations based on the previous searches of their users. Apache spark will be used in the Scala programming language

# Theoretical framework
In general, Machine Learning algorithms can be classified into two types:

- Supervised: these algorithms are trained with a set of data whose result is already known. That is, the model is trained with data from which the input and output are already known.
- Unsupervised: the model is trained with data that the result is not yet known. That is, the input is known but not the output of the data.

Spark MLlib provides both supervised and unsupervised learning algorithms that offer solutions to the three most used techniques in the world of Machine Learning:
![1](https://i.imgur.com/dBBSpds.png)

In this document we will be seeing only algorithms for classification using the spark mlib library, the algorithms that we will play in this work will be the following:
- Decision tree classifier
- Logistic Regression
- Multilayer Perceptron
- Support Vector Machine

## Decision Tree Classifier

Decision trees are popular methods for classification and regression tasks of machine learning.
Decision trees are widely used because they are easy to interpret, handle categorical characteristics, extend to the multi-class classification configuration, do not require scale of characteristics and are capable of capturing nonlinearities and characteristic interactions.
A decision tree is a simple representation to classify examples. It is a supervised machine learning where data is continuously divided according to a certain parameter.

A decision tree consists of:
- Nodes: test the value of a certain attribute.
- Borders / branch: corresponds to the result of a test and connects to the next node or sheet.
- Sheet nodes: terminal nodes that predict the result (represent class labels or class distribution).
![Parts-of-a-Decision-Tree](https://i.imgur.com/eUeEGCU.jpg)


*To understand the concept of Decision Tree, consider the previous example. Let's say you want to predict whether a person is fit or not, given their information such as age, eating habits, physical activity, etc. Decision nodes are questions like 'What is the age?', 'Do you exercise?', 'Do you eat a lot of pizzas? And the sheets represent results as 'fit' or 'not fit'*

There are two main types of decision trees:
- Classification trees.
What we have seen before is an example of a classification tree, where the result was a variable like 'fit' or 'not fit'. Here the decision variable is categorical / discrete.
Such a tree is constructed through a process known as binary recursive partition. This is an iterative process of dividing data into partitions and then dividing them further into each of the branches.

- Regression trees.
Decision trees where the objective variable can take continuous values ​​(typically real numbers) are called regression trees. (for example, the price of a house or the length of a patient's stay in a hospital)

## Support Vector Machines (SVM)
El objetivo del algoritmo de máquina vectorial de soporte es encontrar un hiperplano en un espacio N-dimensional (N — el número de entidades) que clasifique claramente los puntos de datos.

![0*9jEWNXTAao7phK-5](https://i.imgur.com/gpHLiqp.png)

![0*0o8xIA4k3gXUDCFU](https://i.imgur.com/mBg6J4r.png)

Para separar las dos clases de puntos de datos, hay muchos hiperplanos posibles que se podrían elegir. Nuestro objetivo es encontrar un plano que tenga el margen máximo, es decir, la distancia máxima entre los puntos de datos de ambas clases. Maximizar la distancia de margen proporciona algún refuerzo para que los puntos de datos futuros se puedan clasificar con más confianza.

### Hiperplanos y vectores de soporte

![1*ZpkLQf2FNfzfH4HXeMw4MQ](https://i.imgur.com/Qig1qbo.png)

Los hiperplanos son límites de decisión que ayudan a clasificar los puntos de datos. Los puntos de datos que caen a ambos lados del hiperplano se pueden atribuir a diferentes clases. Además, la dimensión del hiperplano depende del número de entidades. Si el número de entidades de entrada es 2, el hiperplano es solo una línea. Si el número de entidades de entrada es 3, el hiperplano se convierte en un plano bidimensional. Se hace difícil imaginar cuando el número de entidades supera 3.

![0*ecA4Ls8kBYSM5nza](https://i.imgur.com/hC3jOke.jpg)

Los vectores de soporte son puntos de datos que están más cerca del hiperplano e influyen en la posición y orientación del hiperplano. Usando estos vectores de soporte, maximizamos el margen del clasificador. La eliminación de los vectores de soporte cambiará la posición del hiperplano. Estos son los puntos que nos ayudan a construir nuestra SVM.

## Logistic Regression

La regresión logística es una técnica de aprendizaje automático que proviene del campo de la estadística. A pesar de su nombre no es un algoritmo para aplicar en problemas de regresión, en los que se busca un valor continuo, sino que es un método para problemas de clasificación, en los que se obtienen un valor binario entre 0 y 1.

Con la regresión logística se mide la relación entre la variable dependiente, la afirmación que se desea predecir, con una o más variables independientes, el conjunto de características disponibles para el modelo. Para ello utiliza una función logística que determina la probabilidad de la variable dependiente. Como se ha comentado anteriormente, lo que se busca en estos problemas es una clasificación, por lo que la probabilidad se ha de traducir en valores binarios. Para lo que se utiliza un valor umbral. Los valores de probabilidad por encima del valor umbral la afirmación es cierta y por debajo es falsa. Generalmente este valor es 0,5, aunque se puede aumentar o reducir para gestionar el número de falsos positivos o falsos negativos.

# Implementacion

## Apache Spark

Apache Spark es hoy en día una de las tecnologías más influyente y de importancia en el mundo del Big Data. Es un sistema computacional de clústeres abiertos, motor de análisis unificado, ultrarrápido para Big Data y Machine Learning.

Desde su lanzamiento, Apache Spark , ha sido rápidamente adoptado por empresas en una amplia gama de industrias. Se ha convertido rápidamente en la mayor comunidad de código abierto en big data, con más de 1000 colaboradores de más de 250 organizaciones tecnológicas, lo que hace la programación más accesible para los científicos de datos.

## Beneficios de Apache Spark

### Velocidad

Spark puede ser 100 veces más rápido que Hadoop para el procesamiento de datos a gran escala al explotar la computación en memoria y otras optimizaciones. También es rápido cuando los datos se almacenan en el disco, y actualmente tiene el récord mundial para la clasificación en disco a gran escala.

### Facilidad de uso

Spark tiene API fáciles de usar para operar en grandes conjuntos de datos. Esto incluye una colección de más de 100 operadores para transformar datos y APIs de marcos de datos familiares para manipular datos semiestructurados. APIs como Java, Scala, Phyton y R. También es conocido por su facilidad de uso a la hora de crear algoritmos que adquieren todo el conocimiento de datos muy complejos.

### Un motor unificado

Spark viene empaquetado con bibliotecas de nivel superior, que incluyen soporte para consultas SQL, transmisión de datos, aprendizaje automático y procesamiento de gráficos. Estas bibliotecas estándar aumentan la productividad del desarrollador y se pueden combinar sin problemas para crear flujos de trabajo complejos.

Apache Spark se compone de :

* Spark SQL: Módulo de procesamiento de datos estructurados y semi-estructurados. Con esto se podrá transformar y realizar operaciones sobre los RDD o los dataframes. Especial para el tratamiento de los datos.

* Spark Core: Núcleo del framework. Es la base de librerías donde se apoya el resto de los módulos.

* Spark MLLib: Es una librería muy completa que contiene numerosos algoritmos de Machine Learning, tanto de clusterización, clasificación, regresión, etc. Nos permite, de una forma amigable, poder utilizar algoritmos de Machine Learning.

* Spark Streaming: Es el que permite la ingesta de datos en tiempo real. Si tenemos una fuente, por ejemplo Kafka o Twitter, con este módulo podemos ingestar los datos de esa fuente y volcarlos a un destino. Entre la ingesta de datos y su volcado posterior, podemos tener una serie de transformaciones.

* Spark Graph: Permite el procesamiento de grafos (DAG). No permite pintar grafos, sino que permite crear operaciones con grafos, con sus nodos y aristas, e ir realizando operaciones.

## Ventajas y Desventajas

### VENTAJAS 
1.- Sparkes más potenteque hadoop: nos permite desarrollar grandes proyectos Big Datacon menos inversión y consiguiendo buenos resultados.

2.- Es una plataforma de código.

3.- Es  rápido: Mucho  más  que  Hadoop.  Garantiza  una  buena  productividad  y  mayor interactividad.

4.- Puede  compenetrarse  con  otra  arquitectura Big  Data: Puede  utilizar  ficheros  de HDFS o procesos de YARN entre otras.

5.- Cuida  a  los  desarrolladores:  cuando una  persona  está  utilizando  esta  tecnología únicamente  debe  de  tener  en  mente  desarrollar  el  código  u  algoritmo  que  esté implementando  ya  que con  los  lenguajes  de  programación  que  ofrece  y  el  fácil entorno de trabajo no existe otra preocupación.

6.- La componen APIs para varios lenguajes y trabajos.

7.- Tiene una consola interactiva para poder trabajar con mayor facilidad

8.- Gran aliado de la Arquitectura Kappa que la conforman Kafka + Spark + NOSql + Scala. Es una de las arquitecturas que más se demandan.

9.- Encabeza  tecnologías Big  Data: compañías  como IBM,  Huawei  y  Microsoft  entre otras invierten e integran a Spark en sustrabajos.

### DESVENTAJAS

1.- Necesita más memoria de almacenamiento.2.Como utiliza aplicaciones pesadas, puede disminuir su rendimiento

Referencias

Verjaga Felgueras Maria Elena (2018), Analisis de datos y extraccion de conocimientos utilizando Big Data.10 Enero 2021 . de Universidad de Jaén. Sitio web: http://tauja.ujaen.es/bitstream/10953.1/8380/1/MEMORIA.pdf

Sebastian Ilabaca. (06 Febrero, 2018). ¿Qué es Apache Spark?. 10 Enero, 2021, de Analytucs10 Sitio web: https://www.analytics10.com/que-es-apache-spark/

Rohith Gandhi. (07 Junio, 2018). Support Vector Machine — Introduction to Machine Learning Algorithms. 10 Enero, 2021, de Toward Data Science Sitio web: https://towardsdatascience.com/support-vector-machine-introduction-to-machine-learning-algorithms-934a444fca47
