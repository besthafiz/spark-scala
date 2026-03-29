package spark
import org.apache.spark.sql.SparkSession
object DataSkew {

    def main(args: Array[String]): Unit = {

      val spark = SparkSession.builder()
        .appName("Data Skew Example")
        .master("local[*]")
        .getOrCreate()

      import spark.implicits._

      val data = Seq(
        ("A", 1), ("A", 2), ("A", 3), ("A", 4), ("A", 5),
        ("B", 1),
        ("C", 1)
      )

      val df = data.toDF("key", "value")

      // Group by key (this will show skew problem)
      val result = df.groupBy("key")
        .sum("value")

      result.show()

      spark.stop()
    }
}
