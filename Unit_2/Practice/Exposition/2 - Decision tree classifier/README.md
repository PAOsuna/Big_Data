# Desicion Tree

Se importan las librerian en orden

```R
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.DecisionTreeClassificationModel
import org.apache.spark.ml.classification.DecisionTreeClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}

import org.apache.spark.sql.SparkSession

```
Se carga el dataframe en formato LIBSVM

se agregan etiquetas de índice y metadatos a la columna de etiquetas.

El conjunto de datos deben encajar para incluir todas las etiquetas en el índice.

Se identifican automáticamente características categóricas y luego se indexan.

Se dividen los datos en conjuntos de prueba y entrenamiento (el 30% se reserva para probar).

Se convierten las etiquetas indexadas de nuevo en etiquetas originales.

Se indexan las cadena y árbol en el pipeline.

Se hacen las predicciones las predicciones.

Se elegen las filas de ejemplo para mostrar. 

Se Calcula el error de prueba.

Se Mostran por etapas la clasificación del modelo de árbol

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