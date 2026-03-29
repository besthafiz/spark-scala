import org.apache.spark.SparkConf
import org.apache.spark.sql.functions._
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.expressions.Window
object JoinExample {

  def main(args: Array[String]): Unit = {

    val conf=new SparkConf().setAppName("JoinExample")
    val spark=SparkSession.builder().config(conf).master("local[*]").getOrCreate()
    import spark.implicits._
    val data=Seq((123,"Abdul","Hafiz",1),
      (345,"Abdul","Aziz",1),
      (12,"Farhan","Khan",2),
      (34,"Rehan","Aziz",3)
    )
    val employee: DataFrame = data.toDF("id", "firstName", "lastName","addressId")
    employee.show

    val data2=Seq((1,"hamid manzil","98989","Jaipur","Rajasthan","India"),
      (2,"Laxminager","1234","Delhi","Delhi","India"),
      (3,"dharavi","1234","Mumbai","Maharashtra","India")
    )
    val address=data2.toDF("AddId","Address","PhoneNo","City","State","Country")
    address.show(false)

    val deptData=Seq((1,123,"IT",10000),(2,345,"IT",20000),(3,12,"HR",30000),(4,34,"HR",40000))
    val dept=deptData.toDF("DeptId","StaffId","DeptName","Salary")
    dept.show(false)
    val empAddDf=employee.as("emp").join(address.as("add"),col("add.AddId")===col("emp.addressId"),"left")
      .select("id", "firstName", "lastName","Address")
    val finalDf=empAddDf.join(dept,col("id")===col("StaffId"),"left")
      .select("id", "firstName", "lastName","Address","DeptName","Salary")
   // val finalDf=employee.as("e").join(address.as("a"),$"e.id"===$"a.Staff_ID","inner")
    finalDf.show(false)

    spark.close()


    //val employee
  }
}
