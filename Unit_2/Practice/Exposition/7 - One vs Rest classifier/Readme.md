# One vs Rest Classifier
We import the libraries

We load the data file.

We generate the training / test division.

We instantiate the base classifier

An instance of the One Vs Rest classifier is created.

Train the multiclass model.

Score the model on the test data.

The evaluator is obtained.

a classification error calculation is made on the test data.


```R
import org.apache.spark.ml.classification.{LogisticRegression, OneVsRest}
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator

var inputData = spark.read.format("libsvm").load("/opt/spark/data/mllib/sample_multiclass_classification_data.txt")

val Array(train, test) = inputData.randomSplit(Array(0.8, 0.2))

val classifier = new LogisticRegression().setMaxIter(10).setTol(1E-6).setFitIntercept(true)

val ovr = new OneVsRest().setClassifier(classifier)

val ovrModel = ovr.fit(train)

val predictions = ovrModel.transform(test)

val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")

val accuracy = evaluator.evaluate(predictions)
println(s"Test Error = ${1 - accuracy}")
```