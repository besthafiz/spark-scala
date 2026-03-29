package spark
import org.apache.spark.sql.{SparkSession,DataFrame}
import org.apache.spark.sql.functions._
import org.apache.spark.SparkConf
object Pivot {
  def main(args: Array[String]): Unit = {
    val  spark =SparkSession.builder().master("local[*]").appName("Pivot")
      .config(new SparkConf()).getOrCreate()
    val data = Seq(
      ("Alice", "Math", 80),
      ("Alice", "Math", 40),
      ("Alice", "English", 90),
      ("Bob", "Math", 70),
      ("Bob", "English", 60),
      ("Charlie", "Math", 85)
    )
    import spark.implicits._
    val result=data.toDF("student","subject","marks")
    result.show()
   val pivot= result.groupBy("student").pivot("subject").agg(sum("marks"))
    pivot.show(false)

  }
}
