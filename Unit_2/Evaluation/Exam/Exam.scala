 //Desarrollar las siguientes instrucciones en Spark con el leguaje de programación Scala, utilizando solo la documentacion de la librería de Machine Learning  Mllib de Spark y Google.

//1. Cargar en un dataframe Iris.csv que se encuentra en https://github.com/jcromerohdz/iris, elaborar la liempieza de datos necesaria para ser procesado por el siguiente algoritmo (Importante, esta limpieza debe ser por medio de un script de Scala en Spark) .
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.StringIndexer
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors

val data  = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("/home/leonardo/Documentos/DatosMasivos/Big_Data/Unit_2/Evaluation/Exam")

val label = new StringIndexer().setInputCol("species").setOutputCol("label")
val labeltransform = label.fit(data).transform(data)
labeltransform.show()

val Features = (new VectorAssembler (). setInputCols (Array ("sepal_length", "sepal_width", "petal_length", "petal_width")). setOutputCol ("features"))
val data2 = Features.transform (labeltransform)
data2.show()

    //a. Utilice la librería Mllib de Spark el algoritmo de Machine Learning correspondiente a multilayer perceptron
    
    import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
    import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator

//2. ¿Cuáles son los nombres de las columnas?
    data2.columns
res6: Array[String] = Array(sepal_length, sepal_width, petal_length, petal_width, species, label, features)

//3. ¿Cómo es el esquema?
    data2.printSchema()
root
 |-- sepal_length: double (nullable = true)
 |-- sepal_width: double (nullable = true)
 |-- petal_length: double (nullable = true)
 |-- petal_width: double (nullable = true)
 |-- species: string (nullable = true)
 |-- label: double (nullable = false)
 |-- features: vector (nullable = true)
