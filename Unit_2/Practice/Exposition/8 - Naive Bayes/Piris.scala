 
// Nosotros usaremos el tipico ejemplo de la planta iris, esta es la planta con mayor numero de especies.
// Buscamos reconocer 3 tipos de iris(Iris Setosa(0), Iris Versicolour (1), Iris Virginica(2)).
// Mendiante variables obtenidas(La longitud y la anchura del Sepalo y la longitud y la anchura del petalo).
package spark.ml.cookbook.chapter6
import org.apache.spark.mllib.linalg.{Vector, Vectors} 
import org.apache.spark.mllib.regression.LabeledPoint 
import org.apache.spark.mllib.classification.{NaiveBayes, NaiveBayesModel}
import org.apache.spark.mllib.evaluation.{BinaryClassificationMetrics, MulticlassMetrics, MultilabelMetrics, binary}
import org.apache.spark.sql.{SQLContext, SparkSession}
import org.apache.log4j.Logger
import org.apache.log4j.Level

//Importamos nuestra Data Preparada
val data = sc.textFile("iris-data-prepared.txt")

//Lo transformamos en un DataSet, en el cual tomaremos la columna de species como etiqueta de referencia
//sepal_length,sepal_width,petal_length,petal_width, estas columnas las transformamos en vectores.
val NaiveBayesDataSet = data.map { line => val 
columns = line.split(',')
LabeledPoint(columns(4).toDouble,
Vectors.dense(
columns(0).toDouble,
columns(1).toDouble,
columns(2).toDouble,
columns(3).toDouble))
}

//Limpiamos nuestro Dataset eliminando duplicados
println(" Total number of data vectors =", 
NaiveBayesDataSet.count())

val distinctNaiveBayesData = NaiveBayesDataSet.distinct() 
println("Distinct number of data vectors = ", 
distinctNaiveBayesData.count())

//Imprimimos nuestros datos para visualizar como los estamos guardando
distinctNaiveBayesData.collect().take(10).foreach(println(_))

//Dividimos nuestros datos aleatoriamente para crear un dataset de entramiento y uno para hacer pruebas(70% - 30%)
val allDistinctData =
distinctNaiveBayesData.randomSplit(Array(.80,.20),10L)
val trainingDataSet = allDistinctData(0)
val testingDataSet = allDistinctData(1)

println("number of training data =",trainingDataSet.count())
println("number of test data =",testingDataSet.count())

//Creamos nuestro modelo con las funciones de NaiveBayes que nos ofrecen los paquetes de scala y lo entrenamos con nuestro dataset de entrenamiento
val myNaiveBayesModel = NaiveBayes.train(trainingDataSet)

//A nuestro dataset de Testeo cada uno de sus valores los va leyendo nuestro modelo y este tratara de predecir y los comparara.
val predictedClassification = testingDataSet.map( x => 
 (myNaiveBayesModel.predict(x.features), x.label))

//Metricas
val metrics = new MulticlassMetrics(predictedClassification)

//Matrix de Confusion  
 val confusionMatrix = metrics.confusionMatrix 
 println("Confusion Matrix= n",confusionMatrix)

//Metricas de precision
 val myModelStat=Seq(metrics.precision)
 myModelStat.foreach(println(_))
