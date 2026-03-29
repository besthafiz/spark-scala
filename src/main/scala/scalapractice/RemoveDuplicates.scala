package scalapractice


object RemoveDuplicates {

  def removeDuplicates(s: String): String = {
    var seen = Set[Char]()
    var result = ""

    for (c <- s) {
      if (!seen.contains(c)) {
        result += c       // keep first occurrence
        seen += c         // mark character as seen
      }
    }

    result
  }

  def main(args: Array[String]): Unit = {
    val str = "aabbccddeeff"
    println(removeDuplicates(str))  // Output: "abcdef"
  }
}
