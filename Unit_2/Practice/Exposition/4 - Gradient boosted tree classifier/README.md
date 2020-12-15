# Gradient Boosted Tree Classification

```R
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.{GBTClassificationModel, GBTClassifier}
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}
```

The data file is loaded and parsed, turning it into a DataFrame.

```R

val data = spark.read.format("libsvm").load("sample_libsvm_data.txt")
```
You create the index tags and adding the metadata to the tag column.
The set must match the data to include all the labels in the index.

Categorical and indexed characteristics are automatically identified.

Sets a maxCategories so that entities with> 4 distinct values are treated as continuous.

The data is divided into test and training sets (30% reserved for testing).

Indexed tags are converted back to original tags.

Chain and GBT Indexers are indexed in the Pipeline.

Predictions predictions are made.

The sample rows are selected for display.

Select (prediction, true label) and calculate the test error.

```r
val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(data)

val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(data)

val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3))

val gbt = new GBTClassifier().setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures").setMaxIter(10).setFeatureSubsetStrategy("auto")

val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labels)

val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, gbt, labelConverter))

val model = pipeline.fit(trainingData)

val predictions = model.transform(testData)

predictions.select("predictedLabel", "label", "features").show(20)

val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")
val accuracy = evaluator.evaluate(predictions)
println(s"Test Error = ${1.0 - accuracy}")

val gbtModel = model.stages(2).asInstanceOf[GBTClassificationModel]
println(s"Learned classification GBT model:\n ${gbtModel.toDebugString}")
```