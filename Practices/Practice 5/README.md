## Practice with 15 Spark Default Functions for Dataframes

> + 1.- Group the dataframe unse the specified columns, count return the numbers in the dataframe and show display the top 20 rows of the dataframes
```
df.groupBy("High").count().show()
df.groupBy("Low").count().show()
df.groupBy("Date").count.show()
```
> + 2.- Return a new dataframe that contains only the unique rows from this dataframe
```
df.dropDuplicates()
```

> + 3.- show the first row

```
df.first()
```

> + 4.- dtypes function Returns all column names and their data types as an array
```
df.dtypes
```

> + 5.- Sort function, sort on one or more columns, the default its sorts by ascending order
```
df.sort("low").show()
df.sort("high", "Date").show()
```

> + 6.- Take funcion, returns the first n numbers rows in the Dataset.
```
df.take(20)
```

> + 7.- Filter function, Filters rows using the given condition.
```
df.filter($"Open" > 500).show()
```

> + 8.- OrderBy function, returns a new Dataset sorted by the given expressions.
```
df.orderBy(asc("Open")).show()
```

> + 9.- drop function, returns a new Dataset with a column dropped. This version of drop accepts a Column rather than a name. This is a no-op if the Dataset doesn't have a column with an equivalent expression.
```
df.na.drop().show()
```

> + 10. Returns a DataFrameNaFunctions for working with missing data
```
df.na.show()
```

> + 11.- Returns the first n rows.
```
df.head()
```

> + 12.- Returns the number of rows in the Dataset.
```
df.count("Open")
```

> + 13.- Returns true if the Dataset is empty.
```
df.isEmpty
```

> + 14.- Returns true if the collect and take methods can be run locally
```
df.isLocal
```

> + 15.- Returns true if this Dataset contains one or more 
sources that continuously return data as it arrives
```
df.isStreaming
```
