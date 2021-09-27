 //Desarrollar las siguientes instrucciones en Spark con el leguaje de programación Scala, utilizando solo la documentacion de la librería de Machine Learning  Mllib de Spark y Google.

//1. Cargar en un dataframe Iris.csv que se encuentra en https://github.com/jcromerohdz/iris, elaborar la liempieza de datos necesaria para ser procesado por el siguiente algoritmo (Importante, esta limpieza debe ser por medio de un script de Scala en Spark) .
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.StringIndexer
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors

val data  = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("/home/leonardo/Documentos/DatosMasivos/Big_Data/Unit_2/Evaluation/Exam")

val label = new StringIndexer().setInputCol("species").setOutputCol("label")
val labeltransform = label.fit(data).transform(data)
labeltransform.show()

val Features = (new VectorAssembler (). setInputCols (Array ("sepal_length", "sepal_width", "petal_length", "petal_width")). setOutputCol ("features"))
val data2 = Features.transform (labeltransform)
data2.show()

    //a. Utilice la librería Mllib de Spark el algoritmo de Machine Learning correspondiente a multilayer perceptron
    
    import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
    import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator

//2. ¿Cuáles son los nombres de las columnas?
    data2.columns
res6: Array[String] = Array(sepal_length, sepal_width, petal_length, petal_width, species, label, features)

//3. ¿Cómo es el esquema?
    data2.printSchema()
root
 |-- sepal_length: double (nullable = true)
 |-- sepal_width: double (nullable = true)
 |-- petal_length: double (nullable = true)
 |-- petal_width: double (nullable = true)
 |-- species: string (nullable = true)
 |-- label: double (nullable = false)
 |-- features: vector (nullable = true)
//4. Imprime las primeras 5 columnas.
data2.show(5)
+------------+-----------+------------+-----------+-------+-----+-----------------+
|sepal_length|sepal_width|petal_length|petal_width|species|label|         features|
+------------+-----------+------------+-----------+-------+-----+-----------------+
|         5.1|        3.5|         1.4|        0.2| setosa|  2.0|[5.1,3.5,1.4,0.2]|
|         4.9|        3.0|         1.4|        0.2| setosa|  2.0|[4.9,3.0,1.4,0.2]|
|         4.7|        3.2|         1.3|        0.2| setosa|  2.0|[4.7,3.2,1.3,0.2]|
|         4.6|        3.1|         1.5|        0.2| setosa|  2.0|[4.6,3.1,1.5,0.2]|
|         5.0|        3.6|         1.4|        0.2| setosa|  2.0|[5.0,3.6,1.4,0.2]|
+------------+-----------+------------+-----------+-------+-----+-----------------+
only showing top 5 rows
//5. Usa el metodo describe () para aprender mas sobre los datos del  DataFrame.
data2.describe().show()
+-------+------------------+-------------------+------------------+------------------+---------+------------------+
|summary|      sepal_length|        sepal_width|      petal_length|       petal_width|  species|             label|
+-------+------------------+-------------------+------------------+------------------+---------+------------------+
|  count|               150|                150|               150|               150|      150|               150|
|   mean| 5.843333333333335| 3.0540000000000007|3.7586666666666693|1.1986666666666672|     null|               1.0|
| stddev|0.8280661279778637|0.43359431136217375| 1.764420419952262|0.7631607417008414|     null|0.8192319205190403|
|    min|               4.3|                2.0|               1.0|               0.1|   setosa|               0.0|
|    max|               7.9|                4.4|               6.9|               2.5|virginica|               2.0|
+-------+------------------+-------------------+------------------+------------------+---------+------------------+
//6. Haga la transformación pertinente para los datos categoricos los cuales seran nuestras etiquetas a clasificar.
val data3 = data2.select("features", "label")
data3.show()

val splits = data3.randomSplit(Array(0.7, 0.3), seed = 1234L)
val train = splits(0)
val test = splits(1)

println("training set =",train.count())

println("test set =",test.count())

//7. Construya el modelos de clasificación y explique su arquitectura.
val layers = Array[Int](4, 5, 4, 3)

val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)

val modelML = trainer.fit(train)
val result = modelML.transform(test)
    val predictionAndLabels = result.select("prediction", "label")
    val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")

//8. Imprima los resultados del modelo
println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")
