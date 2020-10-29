# Evaluation 1

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

With the column function, we show the columns of the DataFrame
```
df.columns 
```
### 4. How is the scheme?
with printSchema, we print the DataFrame in schema form, along with its data type

```
df.printSchema()
```
### 5. Print the first 5 columns.

With Show we show the top 20 rows of the DataFrame, but in this case we will only show 5 columns
```
df.show(5)  
```
### 6. Use describe () to learn about the Data Frame.
With the Describe function, we show the Data Frame and also some data such as the count, mean, min, max and the standard deviation.
```
df.describe().show()
```

### 7. Create a new dataframe with a new column called “HV Ratio” which is the relationship between the price in the “High” column versus the “Volume” column of shares traded for a day. (Hint: It is a column operation). 

with withColumn, we will create a new Dataframe, which is called HV Ratio, and we will add the High and Volume column of the Dataframe, with this a new column and a new result will be created
```
val df2 = df.withColumn("HV Ratio", df("High")+df("Volume")) 
df2.show()
```

### 8.- What day had the highest peak in the “Close” column?
With Order By, we select Close and order it in descending order and we will show the first column
```
df.orderBy($"Close".desc).show(1)
```

### 9.- Write in your own words in a comment of your code. What is the meaning of the Close column "Close"?

The close column represents the market value with which the netflix platform closed the day.

### 10.- What is the maximum and minimum of the “Volume” column?

To show the maximum of the Volume column, it was done in two ways, the first is with select and max, where we will put the name of the column, the second way is with OrderBy, we sort in descending order, add the Volume column and show the first value
```
df.select(max("Volume")).show()
df.orderBy(desc("Volume")).show(1)
```

To show the minimum of the Volume column, it was done in two ways, the first is with select and min, where we will put the name of the column, the second way is with OrderBy, we sort in ascending order, add the Volume column and show the first value
```
df.select(min("Volume")).show()
df.orderBy(asc("Volume")).show(1)
```

### 11.- With Syntax Scala / Spark $ answer the following:
Hint: Basically very similar to the dates session, you will have to create another dataframe to answer some of the items.

#### a. How many days was the “Close” column less than $ 600?

With the Filter function we use it to filter the Close data that are less than 600 and we will count them, to know how much data is in that range
```
df.filter($"close"<600).count()
```

#### b. What percentage of the time was the “High” column greater than $ 500?
First we will obtain the data greater than 500 using the Filter function and we will count them with Count, then we will extract 100% of all the data, our data greater than 500 we will multiply them by 100 and we will divide this result with 100% of the data and thus we will obtain the percentage
```    
val porcentaje1 = df.filter($"High">500).count()
val porcentaje2 = df.filter($"High">0).count()
val porcentaje3:Double = porcentaje1 * 100
porcentaje3 / porcentaje2
```
#### c. What is the Pearson correlation between column "High" and column "Volume"?
To get the pearson correlation, we will use Select, with the corr function where we will put the 2 columns, in this case it is High and Volume, and we will show the content
```
df.select(corr("High", "Volume")).show
```
#### d. What is the maximum in the “High” column per year?

With WithColumn, we will create a new column, which will be called Year, and we will take the year data from the date column,
then, with a select we will take the largest data from the high column, grouping them by years, at the end of our two columns we will result in Year and High, we will sort it in ascending order by year and display it

```
import spark.implicits._
val year1 = df.withColumn("Year", year(df("Date")))
val year2 = year1.select($"Year", $"High").groupBy("Year").max()
year2.select($"Year", $"max(High)").orderBy(asc("Year")).show()
```

#### e. What is the “Close” column average for each calendar month?

With WithColumn, we will create a new column, which will be called Month, and we will take the month data from the date column,
then, with a select we will take the avg data from the Close column, grouping them by month, at the end of our two columns we will get Month and Close, we will sort it in ascending order by Month and display it

```
val mes1 = df.withColumn("Mes", month(df("Date")))
val mes2 = mes1.select($"Mes", $"Close").groupBy("Mes").avg()
mes2.select($"Mes", $"avg(Close)").orderBy(asc("Mes")).show()
```