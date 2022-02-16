![tec](https://i.imgur.com/DKIVS3c.png)

<center>

### TECNOLÓGICO NACIONAL DE MÉXICO

### INSTITUTO TECNOLÓGICO DE TIJUANA

### SUBDIRECCIÓN ACADÉMICA

### DEPARTAMENTO DE SISTEMAS Y COMPUTACIÓN

### SEMESTRE

### SEPTIEMBRE 2020 - ENERO 2021

### Datos Masivos

### BDD-1704TI9A

### REPOSITORIO DE LA MATERIA

## EQUIPO:

### SAÑUDO CAMACHO LEONARDO DANIEL 15212166

### OSUNA ENCISO PABLO ALEJANDRO 16210279

## DOCENTE:

### JOSE CHRISTIAN ROMERO HERNANDEZ

### TIJUANA, BAJA CALIFORNIA, MÉXICO

</center>
# Unit 3


* [Introducction](#introduction)
    * [Theoretical Framework](#Theoretical)
    * [Decision Tree Classifier](Decision-tree)
    * [Support Vector Machines](#SVM)
        * [Hyperplanes and support vectors](#Hyperplanes)
    * [Logistic Regression](#LR)
    * [Multilayer Perceptron](#MP)
* [Implementation](#Implementation)
    * [Apache Spark](#Spark)
        * [Apache Spark Benefits](#Benefits)
        * [Speed](#Speed)
        * [Easy to use](#Easy)
        * [A unified engine](#engine)
        * [Advantages and disadvantages](#AAD)
        * [Advantages](#Advantages)
        * [Disadvantages](#Disadvantages)
* [Results](#Results)
    * [Desicion tree](#Results-DT)
    * [Logistic Regression](#Results-LR)
    * [Multilayer Perceptron](#Results-MP)
    * [Support Vector Machine](#Results-SVM)
* [References](#References)

<div id='introducction'/>

## Introduction 

<p style="text-align: justify;">
Today it is surprising how large companies use learning algorithms to make recommendations to their users and to provide a better experience for them.
But how are these machine learning algorithms performed? In this work we will use different methods that will help us solve some problems, in this way we will be able to understand how some companies or web pages such as netflix or google use the recommendations based on the previous searches of their users. Apache spark will be used in the Scala programming language

<div id='Theoretical'/>

# Theoretical framework

In general, Machine Learning algorithms can be classified into two types:

- Supervised: these algorithms are trained with a set of data whose result is already known. That is, the model is trained with data from which the input and output are already known.
- Unsupervised: the model is trained with data that the result is not yet known. That is, the input is known but not the output of the data.

Spark MLlib provides both supervised and unsupervised learning algorithms that offer solutions to the three most used techniques in the world of Machine Learning:
![1](https://i.imgur.com/dBBSpds.png)

In this document we will be seeing only algorithms for classification using the spark mlib library, the algorithms that we will play in this work will be the following:
- Decision tree classifier
- Support Vector Machine
- Logistic Regression
- Multilayer Perceptron

<div id='Decision-tree'/>

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

##### Code

```r
val dt = new DecisionTreeClassifier().setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures")
val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labels)
val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, dt, labelConverter))
val model = pipeline.fit(trainingData)
val predictions = model.transform(testData)
predictions.select("predictedLabel", "y", "features").show(5)
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")
val accuracy = evaluator.evaluate(predictions)
println(s"Test Error = ${(1.0 - accuracy)}")
```

<div id='SVM'/>

## Support Vector Machines (SVM)
The goal of the support vector machine algorithm is to find a hyperplane in an N-dimensional space (N - the number of entities) that clearly classifies the data points.

![0*9jEWNXTAao7phK-5](https://i.imgur.com/gpHLiqp.png)

![0*0o8xIA4k3gXUDCFU](https://i.imgur.com/mBg6J4r.png)

To separate the two classes of data points, there are many possible hyperplanes that could be chosen. Our goal is to find a plane that has the maximum margin, that is, the maximum distance between the data points of both classes. Maximizing the margin distance provides some reinforcement so that future data points can be classified with more confidence.

<div id='Hyperplanes'/>

### Hyperplanes and support vectors

![1*ZpkLQf2FNfzfH4HXeMw4MQ](https://i.imgur.com/Qig1qbo.png)

Hyperplanes are decision boundaries that help classify data points. The data points that fall on both sides of the hyperplane can be attributed to different classes. Also, the dimension of the hyperplane depends on the number of entities. If the number of input features is 2, the hyperplane is just one line. If the number of input features is 3, the hyperplane becomes a two-dimensional plane. It becomes difficult to imagine when the number of entities exceeds 3.

![0*ecA4Ls8kBYSM5nza](https://i.imgur.com/hC3jOke.jpg)

Support vectors are data points that are closer to the hyperplane and influence the position and orientation of the hyperplane. Using these support vectors, we maximize the margin of the classifier. Removing the support vectors will change the position of the hyperplane. These are the points that help us build our SVM.

##### code
```r
val c1 = feat.withColumn("label",when(col("label").equalTo("1"),0).otherwise(col("label")))
val c2 = c1.withColumn("label",when(col("label").equalTo("2"),1).otherwise(col("label")))
val c3 = c2.withColumn("label",'label.cast("Int"))
val linsvc = new LinearSVC().setMaxIter(10).setRegParam(0.1)

val linsvcModel = linsvc.fit(c3)


println(s"Coefficients: ${linsvcModel.coefficients} Intercept: ${linsvcModel.intercept}")
```
<div id='LR'/>

## Logistic Regression

Logistic regression is a machine learning technique that comes from the field of statistics. Despite its name, it is not an algorithm to apply in regression problems, in which a continuous value is sought, but it is a method for classification problems, in which a binary value between 0 and 1 is obtained.

![Logistic-Function](https://i.imgur.com/iekVvmk.png)

With logistic regression, the relationship between the dependent variable, the statement to be predicted, with one or more independent variables, the set of characteristics available for the model, is measured. To do this, it uses a logistic function that determines the probability of the dependent variable. As previously mentioned, what is sought in these problems is a classification, so the probability has to be translated into binary values. What a threshold value is used for. For probability values ​​above the threshold value the statement is true and below it is false. Generally this value is 0.5, although it can be increased or decreased to manage the number of false positives or false negatives.
##### code
```r
val lr = new  LogisticRegression().setMaxIter(10).setRegParam(0.1)
val model = lr.fit(train)
val result = model.transform(test)
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")

println(s"Coefficients: ${model.coefficients}")
println(s"Intecept: ${model.intercept}")
println(s"Accuraccy = ${evaluator.evaluate(result)}")
```
<div id='MP'/>

## Multilayer Perceptron

Multi layer perceptron (MLP) is a supplement of feed forward neural network. It consists of three types of layers
- the input layer
- output layer 
- hidden layer. 

The input layer receives the input signal to be processed. The required task such as prediction and classification is performed by the output layer. An arbitrary number of hidden layers that are placed in between the input and output layer are the true computational engine of the MLP. Similar to a feed forward network in a MLP the data flows in the forward direction from input to output layer. The neurons in the MLP are trained with the back propagation learning algorithm. MLPs are designed to approximate any continuous function and can solve problems which are not linearly separable. The major use cases of MLP are pattern classification, recognition, prediction and approximation.
![1-s2.0-S0065245819300506-f14-03-9780128187562](https://i.imgur.com/vxAkYIm.jpg)![1*eloYEyFrblGHVZhU345PJw](https://i.imgur.com/aSSOitn.jpg)

##### code
```r
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)

val model = trainer.fit(train)

val result = model.transform(test)
val predictionAndLabels = result.select("prediction", "label")
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")
println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")
```
<div id='Implementation'/>

# Implementation

For the comparison of these four algorithms we use apache spark, with its extension to work with the scala language. Scala is a modern multi-paradigm programming language designed to express common programming patterns in a concise, elegant, and secure way. Thanks to the implementation of this language we can program an algorithm in a simpler way with a performance that surpasses other languages, because we work through a base with spark.

<div id='Tools'/>

#### Used Tools
![Collage sin título](https://i.imgur.com/UjHUsgz.jpg)

<div id='Spark'/>

## Apache Spark
![9b7e1b03-spark_0](https://i.imgur.com/lG1EAf6.png)

Apache Spark is today one of the most influential and important technologies in the world of Big Data. It is an open cluster computational system, unified, ultra-fast analysis engine for Big Data and Machine Learning.

Since its launch, Apache Spark has been rapidly adopted by companies in a wide range of industries. It has quickly become the largest open source community in big data, with more than 1,000 contributors from more than 250 technology organizations, making programming more accessible to data scientists.

<div id='Benefits'/>

## Apache Spark Benefits

<div id='Speed'/>

### Speed

Spark can be 100 times faster than Hadoop for large-scale data processing by exploiting in-memory computing and other optimizations. It is also fast when data is stored to disk, and currently holds the world record for large-scale disk sorting.

<div id='Easy'/>

### Easy to use

Spark has easy-to-use APIs for operating on large data sets. This includes a collection of more than 100 operators for transforming data and familiar data frame APIs for manipulating semi-structured data. APIs such as Java, Scala, Python and R. It is also known for its ease of use when creating algorithms that acquire all the knowledge of very complex data.

<div id='engine'/>

### A unified engine

Spark comes bundled with top-level libraries, including support for SQL queries, streaming data, machine learning, and graphics processing. These standard libraries increase developer productivity and can be seamlessly combined to create complex workflows.

Apache Spark consists of:

* Spark SQL: Structured and semi-structured data processing module. With this, it will be possible to transform and perform operations on RDDs or dataframes. Special for the treatment of data.

* Spark Core: Core of the framework. It is the base of libraries where the rest of the modules are supported.

* Spark MLLib: It is a very complete library that contains numerous Machine Learning algorithms, both for clustering, classification, regression, etc. It allows us, in a friendly way, to be able to use Machine Learning algorithms.

* Spark Streaming: It is the one that allows the ingestion of data in real time. If we have a source, for example Kafka or Twitter, with this module we can ingest the data from that source and dump it to a destination. Between the data ingestion and its subsequent dump, we can have a series of transformations.

* Spark Graph: Allows graph processing (DAG). It does not allow you to paint graphs, but rather allows you to create operations with graphs, with their nodes and edges, and carry out operations.

<div id='AAD'/>

## Advantages and disadvantages

<div id='Advantages'/>

### Advantages 
1.- Sparkes more powerful than hadoop: it allows us to develop large Big Data projects with less investment and achieving good results.

2.- It is a code platform.

3.- It's fast: Much more than Hadoop. It guarantees good productivity and greater interactivity.

4.- It can interpenetrate with another Big Data architecture: It can use HDFS files or YARN processes among others.

5.- Take care of developers: when a person is using this technology, they should only have in mind to develop the code or algorithm that they are implementing since with the programming languages ​​it offers and the easy working environment there is no other concern.

6.- It is made up of APIs for various languages ​​and jobs.

7.- It has an interactive console to be able to work more easily

8.- Great ally of Kappa Architecture that is made up of Kafka + Spark + NOSql + Scala. It is one of the architectures that are most in demand.

9.- Leads Big Data technologies: companies such as IBM, Huawei and Microsoft, among others, invest and integrate Spark in their work.

<div id='Disadvantages'/>

### Disadvantages

1.- You need more storage memory.

2.- As it uses heavy applications, it can decrease its performance

<div id='Results'/>

## Results

<div id='Results-DT'/>

#### Desicion tree
```r
test.1 

scala> val accuracy = evaluator.evaluate(predictions)
accuracy: Double = 0.890821613619541

test.2

scala> val accuracy = evaluator.evaluate(predictions)
accuracy: Double = 0.8916602145780333

test.3
scala> val accuracy = evaluator.evaluate(predictions)
accuracy: Double = 0.8903792286145227
```

<div id='Results-LR'/>

#### Logistic Regression
```r
test.1
scala> println(s"Accuraccy = ${evaluator.evaluate(result)}")
Accuraccy = 0.8852697397120838

test.2
scala> println(s"Accuraccy = ${evaluator.evaluate(result)}")
Accuraccy = 0.8851245427625849

test.3
scala> println(s"Accuraccy = ${evaluator.evaluate(result)}")
Accuraccy = 0.8836548445270234
```

<div id='Results-MP'/>

#### Multilayer Perceptron
```r
test.1
scala> println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")
Test set accuracy = 0.8848956335944776  

test.2
scala> println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")
Test set accuracy = 0.8854597917450759

test.3
scala> println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")
Test set accuracy = 0.8862768145753747 
```

<div id='Results-SVM'/>

#### Support Vector Machine
```r
println(s"Coefficients: ${linsvcModel.coefficients} Intercept: ${linsvcModel.intercept}")
Coefficients: [2.125897501491213E-6,0.013517727458849872,-7.514021888017163E-4,-2.7022337506408964E-4,-0.011177544540215354] Intercept: 1.084924165339881
```

<div id='References'/>

## References

Verjaga Felgueras Maria Elena (2018), Analisis de datos y extraccion de conocimientos utilizando Big Data.10 Enero 2021 . de Universidad de Jaén. Sitio web: http://tauja.ujaen.es/bitstream/10953.1/8380/1/MEMORIA.pdf

Sebastian Ilabaca. (06 Febrero, 2018). ¿Qué es Apache Spark?. 10 Enero, 2021, de Analytucs10 Sitio web: https://www.analytics10.com/que-es-apache-spark/

Rohith Gandhi. (07 Junio, 2018). Support Vector Machine — Introduction to Machine Learning Algorithms. 10 Enero, 2021, de Toward Data Science Sitio web: https://towardsdatascience.com/support-vector-machine-introduction-to-machine-learning-algorithms-934a444fca47

The Digital Twin Paradigm for Smarter Systems and Environments: The Industry Use Cases
S. Abirami, P. Chitra, in Advances in Computers, 2020
https://www.sciencedirect.com/topics/computer-science/multilayer-perceptron
</p>
