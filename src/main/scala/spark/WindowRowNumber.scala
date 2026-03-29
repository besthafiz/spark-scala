package spark

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, concat_ws, rank, row_number}

object WindowRowNumber {

  //  Top salary by dept
  //  Rank employees within each department by salary (descending).
  //    Find the highest paid employee per department.
  //    Calculate a cumulative salary in each department.
  //  Top N per group
  //    Moving average per department
  //    Percent rank / ntile
  //    Running totals with conditions
  //  First/last occurrence per group
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("JoinExample")
    val spark = SparkSession.builder().config(conf).master("local[*]").getOrCreate()
    import spark.implicits._
    val data = Seq((123, "Abdul", "Hafiz", 1),
      (345, "Abdul", "Aziz", 1),
      (12, "Farhan", "Khan", 2),
      (34, "Rehan", "khan", 3)
    )
    val employee: DataFrame = data.toDF("id", "firstName", "lastName", "addressId")
    employee.show

    val data2 = Seq((1, "hamid manzil", "98989", "Jaipur", "Rajasthan", "India"),
      (2, "Laxminager", "1234", "Delhi", "Delhi", "India"),
      (3, "dharavi", "1234", "Mumbai", "Maharashtra", "India")
    )
    val address = data2.toDF("AddId", "Address", "PhoneNo", "City", "State", "Country")
    address.show(false)

    val deptData = Seq((1, 123, "IT", 40000), (2, 345, "IT", 20000), (3, 12, "HR", 30000), (4, 34, "HR", 40000))
    val dept = deptData.toDF("DeptId", "StaffId", "DeptName", "Salary")
    dept.show(false)
    val empAddDf = employee.as("emp").join(address.as("add"), col("add.AddId") === col("emp.addressId"), "left")
      .select("id", "firstName", "lastName", "Address")
    val finalDf = empAddDf.join(dept, col("id") === col("StaffId"), "left")
      .select("id", "firstName", "lastName", "Address", "DeptName", "Salary")
    // val finalDf=employee.as("e").join(address.as("a"),$"e.id"===$"a.Staff_ID","inner")
    finalDf.show(false)
    val win = Window.orderBy(col("Salary").desc)
    val deptSalaryDF = finalDf.select(col("*"), row_number().over(win).as("rank"))
    deptSalaryDF.show(false)

    deptSalaryDF.select(concat_ws(" ", col("firstName"), col("lastName")).as("Name"), col("DeptName"),
      col("Salary")).where("rank=1").distinct().show(false)

    spark.close()
  }
}
