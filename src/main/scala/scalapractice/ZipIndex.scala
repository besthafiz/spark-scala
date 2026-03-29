package scalapractice

object ZipIndex {
  def main(args: Array[String]): Unit = {
    val data = Seq(2, 4, 6, 8)
    println(data.zipWithIndex)
    val dataWithIndex=data.zipWithIndex.map{
      case (value,index)=>(index,value)
    }
    println(dataWithIndex)
  }

}
