# Multilayer Perceptron Classifier

MultilayerPerceptronClassifier and MulticlassClassificationEvaluator are imported

Spark session is imported

Creation of the MultilayerPerceptronClassifier object

The main function is defined which has as a parameter an Array of type string

The SparkSession class object is created, and the app is named MultilayerPerceptronClassifierExample

The data in libsvm format is loaded from the file as a DataFrame

Data is divided into training and testing

The layers of the neural network are specified:
The input layer is of size 4 (characteristics), two intermediate layers one of size 5 and the other of size 4 and 3 of output (the classes)

Training parameters are set

The precision of the test data is calculated

The model accuracy is printed

```R

import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator

import org.apache.spark.sql.SparkSession

/**
 * An example for Multilayer Perceptron Classification.
 */

object MultilayerPerceptronClassifierExample {

  def main(): Unit = {

    val spark = SparkSession
      .builder
      .appName("MultilayerPerceptronClassifierExample")
      .getOrCreate()

    val data = spark.read.format("libsvm")
      .load("sample_multiclass_classification_data.txt")

    val splits = data.randomSplit(Array(0.6, 0.4), seed = 1234L)
    val train = splits(0)
    val test = splits(1)

    val layers = Array[Int](4, 5, 4, 3)

    val trainer = new MultilayerPerceptronClassifier()
      .setLayers(layers)
      .setBlockSize(128)
      .setSeed(1234L)
      .setMaxIter(100)

    val model = trainer.fit(train)

    val result = model.transform(test)
    val predictionAndLabels = result.select("prediction", "label")
    val evaluator = new MulticlassClassificationEvaluator()
      .setMetricName("accuracy")

    println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")

    spark.stop()
  }
}
```