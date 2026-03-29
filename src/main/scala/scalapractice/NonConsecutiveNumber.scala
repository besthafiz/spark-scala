package scalapractice

object NonConsecutiveNumber {

  def main(arr:Array[String]): Unit = {
      val nums=Array(1,1,3,3,4,4,5,5,2,6,2)
      val map=scala.collection.mutable.Map[Int,Int]()
      for(i <- nums){
        map(nums(i))=map.getOrElse(nums(i),0)+1
      }
      println(map)
//      var value=0
//      for(j<-map){
//        if(j._2>1){
//          return j._1
//        }
//      }
  }
}
