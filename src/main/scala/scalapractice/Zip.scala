package scalapractice

object Zip {
  def main(args: Array[String]): Unit = {
    val data1 = Seq("a", "b", "c")
    val data2 = Seq(1, 2)

    val zipped = data1.zip(data2)
    println(zipped)
  }
}
