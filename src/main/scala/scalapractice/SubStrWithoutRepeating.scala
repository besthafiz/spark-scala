package scalapractice

object SubStrWithoutRepeating {

    def lengthOfLongestSubstring(s: String): Int = {
      var charMap = Map[Char, Int]()
      var left = 0
      var maxLength = 0
//val str = "abcabcbb"
      for (right <- 0 until s.length) {
        val currentChar = s(right)
        println(currentChar)
        if (charMap.contains(currentChar)) {
          left = Math.max(left, charMap(currentChar) + 1)
        }

        charMap += (currentChar -> right)
        maxLength = Math.max(maxLength, right - left + 1)
      }

      maxLength
    }

    def main(args: Array[String]): Unit = {
      val str = "abcabcbb"
      println(lengthOfLongestSubstring(str))  // Output: 3
    }


}
