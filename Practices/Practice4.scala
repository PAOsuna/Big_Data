//Practice with 15 Spark Default Functions for Dataframes

1.-
// Group the dataframe unse the specified columns, count return the numbers in the dataframe and show display the top 20 rows of the dataframes
df.groupBy("High").count().show()
df.groupBy("Low").count().show()
df.groupBy("Date").count.show()

2.-
// Return a new dataframe that contains only the unique rows from this dataframe
df.dropDuplicates()

3.-
//show the first row
df.first()

4.-
// dtypes function Returns all column names and their data types as an array
df.dtypes

5.-
//Sort function, sort on one or more columns, the default its sorts by ascending order
df.sort("low").show()
df.sort("high", "Date").show()

