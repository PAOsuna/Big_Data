#Evaluation 1

import org.apache.spark.sql.SparkSession
 
### 1. Start a simple Spark session.

```
val spark = SparkSession.builder().getOrCreate() 
```

### 2. Upload Netflix Stock CSV file, have Spark infer the data types.

```
val df = spark.read.option("header", "true").option("inferSchema","true")csv("/home/leonardo/DatosMasivos/Big_Data/Evaluation") 
```
### 3. What are the column names?
Question 3 we show the columns

```
df.columns 
```
### 4. How is the scheme?
Question 4 we print the scheme

```
df.printSchema()
```
### 5. Print the first 5 columns.

```
df.show(5)  
```
### 6. Use describe () to learn about the Data Frame.

```
df.describe().show()
```

### 7. Create a new dataframe with a new column called “HV Ratio” which is the relationship between the price in the “High” column versus the “Volume” column of shares traded for a day. (Hint: It is a column operation). 

```
val df2 = df.withColumn("HV Ratio", df("High")+df("Volume")) 
df2.show()
```

### 8.- What day had the highest peak in the “Close” column?
```
df.orderBy($"Close".desc).show(1)
```

### 9.- Write in your own words in a comment of your code. What is the meaning of the Close column "Close"?

La columna close representa el valor en el mercado con el que cerro el dia la plataforma de netflix.

### 10.- What is the maximum and minimum of the “Volume” column?

```
df.select(max("Volume")).show()
df.orderBy(desc("Volume")).show(1)

df.select(min("Volume")).show()
df.orderBy(asc("Volume")).show(1)
```

### 11.- With Syntax Scala / Spark $ answer the following:
Hint: Basically very similar to the dates session, you will have to create another dataframe to answer some of the items.

#### a. How many days was the “Close” column less than $ 600?

```
df.filter($"close"<600).count()
```

#### b. What percentage of the time was the “High” column greater than $ 500?

```    
val porcentaje1 = df.filter($"High">500).count()
val porcentaje2 = df.filter($"High">0).count()
val porcentaje3:Double = porcentaje1 * 100
porcentaje3 / porcentaje2
```
#### c. What is the Pearson correlation between column "High" and column "Volume"?

```
df.select(corr("High", "Volume")).show
```
#### d. What is the maximum in the “High” column per year?
 
```
import spark.implicits._
val year1 = df.withColumn("Year", year(df("Date")))
val year2 = year1.select($"Year", $"High").groupBy("Year").max()
year2.select($"Year", $"max(High)").orderBy(asc("Year")).show()
```

#### e. What is the “Close” column average for each calendar month?

```
val mes1 = df.withColumn("Mes", month(df("Date")))
val mes2 = mes1.select($"Mes", $"Close").groupBy("Mes").avg()
mes2.select($"Mes", $"avg(Close)").orderBy(asc("Mes")).show()
```