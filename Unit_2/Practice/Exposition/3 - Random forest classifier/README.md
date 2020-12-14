# Random Forest

Se importan las librerias para usar en el ejemplo
```R
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.{RandomForestClassificationModel, RandomForestClassifier}
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}
import org.apache.spark.ml.attribute.Attribute
```

Se carga y se analiza el archivo de datos para convertirlo en un DataFrame.

Se agregan etiquetas de índice para agregarlos a los metadatos de la columna de etiquetas.

El conjunto debe encajar con los datos para incluir todas las etiquetas en el índice.

Se identican automáticamente características categóricas e indexadas.

Se establece un maxCategories para que las entidades con > 4 o valores distintos se traten como continuas.

Se Dividen los datos en conjuntos de prueba y entrenamiento (el 30% se reserva para probar).

Se convierten las etiquetas indexadas de nuevo a etiquetas originales.

Se indexan la cadena y forest en el Pipeline.

Se hacen las predicciones.

Al final, se seleccionan las filas de ejemplo para mostrar.

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