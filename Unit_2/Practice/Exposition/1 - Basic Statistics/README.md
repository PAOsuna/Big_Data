# Chi Square

Libraries are loaded first to do the test

```R
import org.apache.spark.ml.linalg.{Vector, Vectors}
import org.apache.spark.ml.stat.ChiSquareTest
import org.apache.spark.sql.SparkSession
```

Spark session starts

A new dataframe is created to which the data value will be assigned in the label and features columns

A chi value is created to which ChiSquare is applied through the libraries, to the data frame in its features and label columns


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
The libraries that we use in the map are declared

```R
import org.apache.spark.ml.linalg.{Matrix, Vectors}
import org.apache.spark.ml.stat.Correlation
import org.apache.spark.sql.Row
import org.apache.spark.sql.SparkSession
```

A value called data is created and a sequence of vectors is assigned as a value.

A dataframe is created that is assigned the value of a Tuple called Tuple1, the dataframe contains a column called feautures

A Row type value called coefficient1 of a matrix is assigned the value of the pearson correlation applied to the dataframe applied to its features column.

A Row type value called coefficient2 of a matrix is assigned the value of the spearman correlation applied to the dataframe applied to its features column.

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
The libraries that we use in the Sumarizer example are declared

```R
import org.apache.spark.ml.linalg.{Vector, Vectors}
import org.apache.spark.ml.stat.Summarizer
import org.apache.spark.sql.SparkSession
```
Summarizer methods are imported

Data value will contain two vectors

Df value to refer to "Feactures" and "weight"

Two values for the mean and variance; the metrics are selected (Mean, Variance)

Summary method that we will apply to Feactures and Weight and give it an alias

We select the Summary with its respective metric

It is given a the way we want to print print

We send to printing to show the values of the Mean and Variance

Second Value without using the Summary method

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