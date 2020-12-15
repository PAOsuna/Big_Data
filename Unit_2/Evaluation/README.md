Develop the following instructions in Spark with the Scala programming language, using only the documentation of the Machine Learning Mllib library from Spark and Google.

1. Load into an Iris.csv dataframe found at https://github.com/jcromerohdz/iris, elaborate the necessary data to be processed by the following algorithm (Important, this cleaning must be through a Scala script in Spark).

```R
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.StringIndexer
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors

val data  = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("/home/leonardo/Documentos/DatosMasivos/Big_Data/Unit_2/Evaluation/Exam")

val data  = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("iris.csv")

val label = new StringIndexer().setInputCol("species").setOutputCol("label")

val labeltransform = label.fit(data).transform(data)
labeltransform.show()

val Features = (new VectorAssembler (). setInputCols (Array ("sepal_length", "sepal_width", "petal_length", "petal_width")). setOutputCol ("features"))
val data2 = Features.transform (labeltransform)
data2.show()
```
a. Use the Spark Mllib library the Machine Learning algorithm corresponding to multilayer perceptron
```R
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
``` 
2. What are the column names?
```R
    data2.columns
res6: Array[String] = Array(sepal_length, sepal_width, petal_length, petal_width, species, label, features)
```
3. How is the scheme?
```R
    data2.printSchema()
root
 |-- sepal_length: double (nullable = true)
 |-- sepal_width: double (nullable = true)
 |-- petal_length: double (nullable = true)
 |-- petal_width: double (nullable = true)
 |-- species: string (nullable = true)
 |-- label: double (nullable = false)
 |-- features: vector (nullable = true)
```
4. Print the first 5 columns.
```r
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
```
5. Use the describe () method to learn more about the data in the DataFrame.
```R
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
```
6. Make the relevant transformation for the categorical data which will be our labels to be classified.
```R
val data3 = data2.select("features", "label")
data3.show()

val splits = data3.randomSplit(Array(0.7, 0.3), seed = 1234L)
val train = splits(0)
val test = splits(1)

println("training set =",train.count())

println("test set =",test.count())
```
7. Build the classification model and explain its architecture.
```R
val layers = Array[Int](4, 5, 4, 3)

val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)

val modelML = trainer.fit(train)
val result = modelML.transform(test)
    val predictionAndLabels = result.select("prediction", "label")
    val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")
```

8. Print model results
```R
println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")
```