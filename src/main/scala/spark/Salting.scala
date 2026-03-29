package spark

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object Salting {
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
    val df=data.toDF("id","Value")
    df.show(false)
    val sums=df.groupBy("id").sum("Value")
    sums.show(false)
    df.groupBy("id").agg(sum("Value").as("Sum"),avg("Value").alias("Avg")).show(false)

    val df_salting=df.withColumn("salt",(rand()*5).cast("int"))
    df_salting.show(false)
    val salt_key_df=df_salting.withColumn("salt_key",concat(col("id"),lit("-"),col("salt")))
    salt_key_df.show(false)
    val saltedGrpd=salt_key_df.groupBy("salt_key").agg(sum("Value").as("partial"))
    saltedGrpd.show(false)
    val splitedDf=saltedGrpd.withColumn("key",split(col("salt_key"),"-")(0))
    splitedDf.groupBy("key").agg(sum("partial").alias("FinalSum")).show(false)






    spark.stop()
  }
}