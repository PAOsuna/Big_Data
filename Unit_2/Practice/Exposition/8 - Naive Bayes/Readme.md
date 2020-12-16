# Naive Bayes

We import the necessary libraries.

Load the data saved in LIBSVM format as a DataFrame.

Separate data into training and test sets (30% reserved for testing)

Train a Naive Bayes model.

Select the sample rows to display.

We select (prediction, certain label) and calculate test errors

```R
import org.apache.spark.ml.classification.NaiveBayes
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator

val data = spark.read.format("libsvm").load("C:/Users/Sebas/Desktop/sample_libsvm_data.txt")
data.show(2)

val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3), seed = 1234L)

val model = new NaiveBayes().fit(trainingData)

val predictions = model.transform(testData)
predictions.show()

val evaluator = new MulticlassClassificationEvaluator().setLabelCol("label").setPredictionCol("prediction").setMetricName("accuracy")

val accuracy = evaluator.evaluate(predictions)

println(s"Test set accuracy = $accuracy")
```

# Piris

We import the libraries

We import our Prepared Data

We transform it into a DataSet, in which we will take the species column as a reference label sepal_length, sepal_width, petal_length, petal_width, these columns we transform into vectors.

We clean our Dataset eliminating duplicates

We print our data to see how we are saving it

We divide our data randomly to create an input dataset and one for testing (70% - 30%)

We create our model with the NaiveBayes functions offered by the scala packages and train it with our training dataset

Our test dataset is read by each one of its values and it will try to predict and compare them.


```R
// 
package spark.ml.cookbook.chapter6
import org.apache.spark.mllib.linalg.{Vector, Vectors} 
import org.apache.spark.mllib.regression.LabeledPoint 
import org.apache.spark.mllib.classification.{NaiveBayes, NaiveBayesModel}
import org.apache.spark.mllib.evaluation.{BinaryClassificationMetrics, MulticlassMetrics, MultilabelMetrics, binary}
import org.apache.spark.sql.{SQLContext, SparkSession}
import org.apache.log4j.Logger
import org.apache.log4j.Level

val data = sc.textFile("iris-data-prepared.txt")

val NaiveBayesDataSet = data.map { line => val 
columns = line.split(',')
LabeledPoint(columns(4).toDouble,
Vectors.dense(
columns(0).toDouble,
columns(1).toDouble,
columns(2).toDouble,
columns(3).toDouble))
}

println(" Total number of data vectors =", 
NaiveBayesDataSet.count())

val distinctNaiveBayesData = NaiveBayesDataSet.distinct() 
println("Distinct number of data vectors = ", 
distinctNaiveBayesData.count())

distinctNaiveBayesData.collect().take(10).foreach(println(_))

val allDistinctData =
distinctNaiveBayesData.randomSplit(Array(.80,.20),10L)
val trainingDataSet = allDistinctData(0)
val testingDataSet = allDistinctData(1)

println("number of training data =",trainingDataSet.count())
println("number of test data =",testingDataSet.count())

val myNaiveBayesModel = NaiveBayes.train(trainingDataSet)

val predictedClassification = testingDataSet.map( x => 
 (myNaiveBayesModel.predict(x.features), x.label))


val metrics = new MulticlassMetrics(predictedClassification)
  
 val confusionMatrix = metrics.confusionMatrix 
 println("Confusion Matrix= n",confusionMatrix)

 val myModelStat=Seq(metrics.precision)
 myModelStat.foreach(println(_))
```