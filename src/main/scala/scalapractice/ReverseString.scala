package scalapractice

object ReverseString {
  def main(args: Array[String]): Unit = {
    val str = "Hello World"
    var reversed = ""

    for (i <- str.length - 1 to 0 by -1) {
      reversed += str(i)
    }

    println(reversed)  // Output: "dlroW olleH"

    val reversed2 = str.foldLeft("")((acc, c) => c + acc)

    println(reversed2)

  }
}
