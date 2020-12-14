# Chi Square

Primero se cargan las librerias para hacer el test 

```R
import org.apache.spark.ml.linalg.{Vector, Vectors}
import org.apache.spark.ml.stat.ChiSquareTest
import org.apache.spark.sql.SparkSession
```

Se inicia la sesion de spark

Se crea un nuevo dataframe al cual se le asignara el valor data en las columnas label y features

se crea un valor chi al que se le aplica ChiSquare mediamente las librerias, al data frame en sus columnas features y label


```R
object ChiSquareTestExample {

  def main(args: Array[String]): Unit = {val spark = SparkSession.builder.appName("ChiSquareTestExample").getOrCreate()

    import spark.implicits._

    // Se declara un valor de nombre data al que se le asgina una secuencia de vectores dense
    val data = Seq(
      (0.0, Vectors.dense(0.5, 10.0)),
      (0.0, Vectors.dense(1.5, 20.0)),
      (1.0, Vectors.dense(1.5, 30.0)),
      (0.0, Vectors.dense(3.5, 30.0)),
      (0.0, Vectors.dense(3.5, 40.0)),
      (1.0, Vectors.dense(3.5, 40.0))
    )

    val df = data.toDF("label", "features")
    val chi = ChiSquareTest.test(df, "features", "label").head 
    println(s"pValues = ${chi.getAs[Vector](0)}")
    println(s"degreesOfFreedom ${chi.getSeq[Int](1).mkString("[", ",", "]")}")
    println(s"statistics ${chi.getAs[Vector](2)}")
    // 

    spark.stop()
  }
}
```

# Correlation
Se declaran las librerias que usaremos en la correlacion

```R
import org.apache.spark.ml.linalg.{Matrix, Vectors}
import org.apache.spark.ml.stat.Correlation
import org.apache.spark.sql.Row
import org.apache.spark.sql.SparkSession
```

Se crea un valor llamado data al que se le asigna una secuencia de vectores como valor.

Se crea un dataframe al cual se le asigna el valor de una Tupla llamada Tuple1, el dataframe contiene una columna llamada feautures

A un valor tipo Fila llamado coefficiente1 de una matriz se le asigna el valor de la correlacion de pearson aplicada en el dataframe aplicada a su columna features

Un valor tipo Fila llamado coefficiente2 de una matriz se le asigna el valor de la correlacion de spearman aplicada en el dataframe aplicada a su columna features

```R
object CorrelationExample {

  def main(args: Array[String]): Unit = {val spark = SparkSession.builder.appName("CorrelationExample").getOrCreate()
  
    import spark.implicits._

    val data = Seq(
      Vectors.sparse(4, Seq((0, 1.0), (3, -2.0))), // (0,3,1,-2)
      Vectors.dense(4.0, 5.0, 0.0, 3.0),
      Vectors.dense(6.0, 7.0, 0.0, 8.0),
      Vectors.sparse(4, Seq((0, 9.0), (3, 1.0))) // (0,3,9,1)
    )


    val df = data.map(Tuple1.apply).toDF("features")
    val Row(coeff1: Matrix) = Correlation.corr(df, "features").head
    println(s"Pearson correlation matrix:\n $coeff1") 
    val Row(coeff2: Matrix) = Correlation.corr(df, "features", "spearman").head
    println(s"Spearman correlation matrix:\n $coeff2") 
    

    spark.stop()
  }
}
```

# Summarizer
Se declaran las librerias que usaremos en el ejemplo de Sumarizer

```R
import org.apache.spark.ml.linalg.{Vector, Vectors}
import org.apache.spark.ml.stat.Summarizer
import org.apache.spark.sql.SparkSession
```
Se importan los metodos de Summarizer

Valor data contendra dos vectores 

Valor df para hacer referencia a "Feactures" y "weight"

Dos valores para la media y varianza; se seleccionan las metricas (Mean,Variance)

Metodo summary que se lo aplicaremos a  Feactures y Weight y le damos un alias

Seleccionamos el Summary con su respectiva metrica

Se le da una la forma en la que queremos imprimir imprimir

Mandamos a la impresion para mostrar los valores de la Mean y Varianza 

Segundo Valor sin usar el metodo Summary 

```R
object SummarizerExample {def main(args: Array[String]): Unit = {val spark = SparkSession.builder.appName("SummarizerExample").getOrCreate()

    import spark.implicits._
    //Importar Summarizer 
    import Summarizer._


    val data = Seq(
      (Vectors.dense(2.0, 3.0, 5.0), 1.0),
      (Vectors.dense(4.0, 6.0, 7.0), 2.0)
    )

    val df = data.toDF("features", "weight")
    val (meanVal, varianceVal) = df.select(metrics("mean", "variance")
      .summary($"features", $"weight").as("summary"))
      .select("summary.mean", "summary.variance")
      .as[(Vector, Vector)].first()
    println(s"with weight: mean = ${meanVal}, variance = ${varianceVal}")
    val (meanVal2, varianceVal2) = df.select(mean($"features"), variance($"features"))
      .as[(Vector, Vector)].first()
      
    println(s"without weight: mean = ${meanVal2}, sum = ${varianceVal2}")

    spark.stop()
  }
}
```