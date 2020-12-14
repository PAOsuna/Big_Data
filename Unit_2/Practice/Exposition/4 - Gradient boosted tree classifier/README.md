# Gradient Boosted Tree Classification

```R
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.{GBTClassificationModel, GBTClassifier}
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}
```

Se Cargan y se analiza el archivo de datos, convirtiéndolo en un DataFrame.

```R
// 
val data = spark.read.format("libsvm").load("sample_libsvm_data.txt")
```
Se crean las etiquetas de índice y agregando los metadatos a la columna de etiquetas.
El conjunto debe encajar con los datos para incluir todas las etiquetas en el índice.

Se Identifica automáticamente las características categóricas e indexadas.

Establece un maxCategories para que las entidades con > 4 de valores distintos que se traten como continuas.

Se Dividen los datos en conjuntos de prueba y entrenamiento (el 30% se reserva para probar).

Se convierten las etiquetas indexadas de nuevo a etiquetas originales.

Los Indexadores en cadena y GBT se indexan en el Pipeline.

Se Hacen las predicciones predicciones.

Se seleccionan las filas de ejemplo para mostrarlas.

Selecciona (predicción, etiqueta verdadera) y calcule el error de prueba.

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