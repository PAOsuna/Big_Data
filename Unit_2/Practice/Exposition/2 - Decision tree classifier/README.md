# Desicion Tree

Libraries are imported in order

```R
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.DecisionTreeClassificationModel
import org.apache.spark.ml.classification.DecisionTreeClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}

import org.apache.spark.sql.SparkSession

```
The dataframe is loaded in LIBSVM format

index tags and metadata are added to the tag column.

The dataset must fit to include all the labels in the index.

Categorical characteristics are automatically identified and then indexed.

The data is divided into test and training sets (30% reserved for testing).

Indexed tags are converted back to original tags.

The chain and tree in the pipeline are indexed.

Predictions are made predictions.

Sample rows are chosen to display.

The test error is calculated.

The classification of the tree model is shown in stages

```R
object DecisionTree {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .appName("dtree")
      .getOrCreate()

val data = spark.read.format("libsvm").load("sample_libsvm_data.txt")

val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(data)

val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(data)


val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3))


val dt = new DecisionTreeClassifier().setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures")

val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labels)


val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, dt, labelConverter))


val model = pipeline.fit(trainingData)


val predictions = model.transform(testData)

predictions.select("predictedLabel", "label", "features").show(5)


val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")

val accuracy = evaluator.evaluate(predictions)
println(s"Test Error = ${(1.0 - accuracy)}")


val treeModel = model.stages(2).asInstanceOf[DecisionTreeClassificationModel]
println(s"Learned classification tree model:\n ${treeModel.toDebugString}")

  }
}
```