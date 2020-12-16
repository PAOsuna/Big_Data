
###### We import a spark session with the logistic regression library, create the session and read the csv file and print the results.
```
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.sql.SparkSession

import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

val spark = SparkSession.builder().getOrCreate()

val data  = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("advertising.csv")

data.printSchema()
```
###### We print an example line
```
data.head(1)

val colnames = data.columns
val firstrow = data.head(1)(0)
println("\n")
println("Example data row")
for(ind <- Range(0, colnames.length)){
    println(colnames(ind))
    println(firstrow(ind))
    println("\n")
}
```
###### We add a column called "Hour" taken from the existing column: "Timestamp"
```
val timedata = data.withColumn("Hour",hour(data("Timestamp")))

val logregdata = timedata.select(data("Clicked on Ad").as("label"), $"Daily Time Spent on Site", $"Age", $"Area Income", $"Daily Internet Usage", $"Hour", $"Male")
```
###### We import the vector libraries sembler and vectors to convert the data to a column called features that we will save in the assembler variable.
```import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors

val assembler = (new VectorAssembler()
                  .setInputCols(Array("Daily Time Spent on Site", "Age","Area Income","Daily Internet Usage","Hour","Male"))
                  .setOutputCol("features"))
```

###### We send the data to be trained giving 70% training data and 30% as test data
```
val Array(training, test) = logregdata.randomSplit(Array(0.7, 0.3), seed = 12345)
```

###### We import the pipeline library and create an object of the class logisticRegression, then we generate the pipeline that will contain the values of assembler and lr and finally we create the pipeline model where we will send the training data and the results model where we will transform the test data.
```
import org.apache.spark.ml.Pipeline

val lr = new LogisticRegression()

val pipeline = new Pipeline().setStages(Array(assembler, lr))

val model = pipeline.fit(training)

val results = model.transform(test)
```
###### We import the multiclassmetrics library with which we will take the prediction and label columns that are double value and we will transform them to rdd.We create the metrics object where we will send the predictionandlabels data and finally we print the confusion matrix and the precision.
```
import org.apache.spark.mllib.evaluation.MulticlassMetrics

val predictionAndLabels = results.select($"prediction",$"label").as[(Double, Double)].rdd

val metrics = new MulticlassMetrics(predictionAndLabels)

println("Confusion matrix:")
println(metrics.confusionMatrix)

metrics.accuracy 
```
