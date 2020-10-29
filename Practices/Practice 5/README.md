# Practice 5

Practice with 15 Default Spark Functions for Dataframes

#### 1.- The GroupBy function the Dataframe without the specified columns, count returns the numbers in the dataframe and shows the top 20 rows of the data frames.

```
df.groupBy ("High"). count (). show ()
df.groupBy ("Low"). count (). show ()
df.groupBy ("Date"). count.show ()
```

#### 2.- the dropDuplicates function, returns a new data frame that contains only the unique rows of this Dataframe

```
df.dropDuplicates ()
```

#### 3.- The First function shows the first row

```
df.first ()
```

#### 4.- The dtypes function returns all column names and their data types as an array

```
df.dtypes
```

#### 5.- Sort function, sorts in one or more columns, by default it is classified in ascending order

```
df.sort ("low"). show ()
df.sort ("high", "Date"). show ()
```

#### 6.- The Take function, returns the first n rows of numbers in the data set.

```
df.take (20)
```

#### 7.-Filter function, filter rows using the given condition.

```
df.filter ($ "Open"> 500) .show ()
```

#### 8.-OrderBy function, returns a new data set ordered by the given expressions.
```
df.orderBy (asc ("Open")). show ()
```

#### 9.-drop function, returns a new data set with a deleted column. This version of drop accepts a column instead of a name. This is an inoperative operation if the dataset does not have a column with an equivalent expression

```
df.na.drop (). show ()
```
#### 10.- Returns a DataFrameNaFunctions to work with missing data

```
df.na.show ()
```

#### 11.- Head, returns the first n rows.

```
df.head ()
```

#### 12.- The Count function returns the number of rows in the data set.

```
df.count ("Open")
```

#### 13.-Returns true if the data set is empty.

```
df.isEmpty
```

#### 14.-Returns true if the collect and take methods can be executed locally

```
df.isLocal
```

#### 15.- Returns true if this data set contains one or more sources that continuously return data as it arrives

```
df.isStreaming
```