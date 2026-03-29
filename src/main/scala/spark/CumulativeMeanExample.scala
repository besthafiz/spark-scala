package spark

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._

object CumulativeMeanExample {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Cumulative Mean Example")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._

    // Sample data
    val data = Seq(2, 4, 6, 8)

    // Create DataFrame with index
    val df = data.zipWithIndex.map { case (value, index) => (index, value) }
      .toDF("id", "value")

    // Define window (ordered by id)
    val windowSpec = Window.orderBy("id")
      .rowsBetween(Window.unboundedPreceding, Window.currentRow)

    // Calculate cumulative sum and count
    val result = df.withColumn("cumulative_sum", sum("value").over(windowSpec))
      .withColumn("cumulative_count", count("value").over(windowSpec))
      .withColumn("cumulative_mean", col("cumulative_sum") / col("cumulative_count"))

    // Show result
    result.show()

    spark.stop()
  }
}