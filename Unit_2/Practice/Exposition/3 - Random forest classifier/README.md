# Random Forest

The libraries are imported to use in the example

```R
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.{RandomForestClassificationModel, RandomForestClassifier}
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}
import org.apache.spark.ml.attribute.Attribute
```

The data file is loaded and parsed into a DataFrame.

Index tags are added to add to the tag column metadata.

The set must match the data to include all the labels in the index.

Categorical and indexed characteristics are automatically identified.

A maxCategories is set so that entities with> 4 or different values are treated as continuous.

The data is divided into test and training sets (30% reserved for testing).

Indexed tags are converted back to original tags.

The chain and forest are indexed in the Pipeline.

Predictions are made.

At the end, the sample rows are selected to display.

```R
val data = spark.read.format("libsvm").load("sample_libsvm_data.txt")

val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(data)
val indexed =  labelIndexer.transform(data)

val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(data)


val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3))


val rf = new RandomForestClassifier().setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures").setNumTrees(10)


val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labels) 
StringIndexer
val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, rf, labelConverter))

val model = pipeline.fit(trainingData)

val predictions = model.transform(testData)

predictions.select("predictedLabel", "label", "features").show(5)
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")
val accuracy = evaluator.evaluate(predictions)
println(s"Test Error = ${(1.0 - accuracy)}")

val rfModel = model.stages(2).asInstanceOf[RandomForestClassificationModel]

println(s"Learned classification forest model:\n ${rfModel.toDebugString}")

```