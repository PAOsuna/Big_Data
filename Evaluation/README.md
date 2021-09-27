# Unit 3
1. Import a simple Spark session.
```r
import org.apache.spark.sql.SparkSession
```

2. Use lines of code to minimize errors
```r
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)
```

3. Create an instance of the Spark session
```r
val spark = SparkSession.builder().getOrCreate()
```

4. Import the Kmeans library for the clustering algorithm.
```r
import org.apache.spark.ml.clustering.KMeans
```

5. Load the Wholesale Customers Data dataset
```r
val dataset = spark.read.option("header","true").option("inferSchema","true").csv("Wholesale customers data.csv")

feature_data.show()
+-----+-----+-------+------+----------------+----------+
|Fresh| Milk|Grocery|Frozen|Detergents_Paper|Delicassen|
+-----+-----+-------+------+----------------+----------+
|12669| 9656|   7561|   214|            2674|      1338|
| 7057| 9810|   9568|  1762|            3293|      1776|
| 6353| 8808|   7684|  2405|            3516|      7844|
|13265| 1196|   4221|  6404|             507|      1788|
|22615| 5410|   7198|  3915|            1777|      5185|
| 9413| 8259|   5126|   666|            1795|      1451|
|12126| 3199|   6975|   480|            3140|       545|
| 7579| 4956|   9426|  1669|            3321|      2566|
| 5963| 3648|   6192|   425|            1716|       750|
| 6006|11093|  18881|  1159|            7425|      2098|
| 3366| 5403|  12974|  4400|            5977|      1744|
|13146| 1124|   4523|  1420|             549|       497|
|31714|12319|  11757|   287|            3881|      2931|
|21217| 6208|  14982|  3095|            6707|       602|
|24653| 9465|  12091|   294|            5058|      2168|
|10253| 1114|   3821|   397|             964|       412|
| 1020| 8816|  12121|   134|            4508|      1080|
| 5876| 6157|   2933|   839|             370|      4478|
|18601| 6327|  10099|  2205|            2767|      3181|
| 7780| 2495|   9464|   669|            2518|       501|
+-----+-----+-------+------+----------------+----------+
only showing top 20 rows
```

6. Select the following columns: Fresh, Milk, Grocery, Frozen, Detergents_Paper, Delicassen and call this set feature_data
```r
val feature_data = dataset.select($"Fresh", $"Milk", $"Grocery", $"Frozen", $"Detergents_Paper", $"Delicassen")
```

7. Import Vector Assembler and Vector
```r
import org.apache.spark.ml.feature.{VectorAssembler,StringIndexer,VectorIndexer,OneHotEncoder}
import org.apache.spark.ml.linalg.Vectors
```
8. Create a new Vector Assembler object for the feature columns as an input set, remembering that there are no labels
```r
val assembler = new VectorAssembler().setInputCols(Array("Fresh", "Milk", "Grocery", "Frozen", "Detergents_Paper", "Delicassen")).setOutputCol("features")
```
9. Use the assembler object to transform feature_data
```r
val traning = assembler.transform(feature_data)
```

10. Create a Kmeans model with K = 3
```r
val kmeans = new KMeans().setK(3).setSeed(1L)
val model = kmeans.fit(traning)
```

11. Evaluate the groups using Within Set Sum of Squared Errors WSSSE and print the centroids.
```r
val WSSSE = model.computeCost(traning)
println(s"Within Set Sum of Squared Errors = $WSSSE")

Within Set Sum of Squared Errors = 8.095172370767671E10

println("Cluster Centers: ")
model.clusterCenters.foreach(println)

[7993.574780058651,4196.803519061584,5837.4926686217,2546.624633431085,2016.2873900293255,1151.4193548387098]
[9928.18918918919,21513.081081081084,30993.486486486487,2960.4324324324325,13996.594594594595,3772.3243243243246]
[35273.854838709674,5213.919354838709,5826.096774193548,6027.6612903225805,1006.9193548387096,2237.6290322580644]
```