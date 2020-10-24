import org.apache.spark.sql.SparkSession
 
val spark = SparkSession.builder().getOrCreate() // Pregunta 1 Comienze una simple sesion de spark

val df = spark.read.option("header", "true").option("inferSchema","true")csv("/home/leonardo/DatosMasivos/Big_Data/Evaluation") //inferimos el esquema

df.columns // Pregunta 3 mostramos las columnas

df.printSchema()//Pregunta 4 imprimimos el esquema

df.show(5)  // Pregunta 5 mostramos las 5 primeras filas

df.describe().show()// Pregunta 6 funcion describe

val df2 = df.withColumn("HV Ratio", df("High")+df("Volume")) //Pregunta 7 creacion de nuevo dataframe 
df2.show()