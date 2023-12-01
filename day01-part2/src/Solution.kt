import java.io.File

val spelledDigits = mapOf("one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5, "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9)

fun main(){
    Solution().Solve(File("day01-part2/src/input.txt").readLines())
}

class Solution{
    fun Solve(input: List<String>){
        val sum = input.sumOf {
            val line = it
            var first: Char? = null
            var i = 0
            while(first == null){
                first = if(it[i].isDigit()) it[i]
                else {
                    spelledDigits[spelledDigits.keys.firstOrNull {
                        it.length + i <= line.length && line.substring(i, i + it.length) == it
                    }]?.digitToChar()
                }

                i++
            }

            var second: Char? = null
            i = line.length - 1
            while(second == null){
                second = if(it[i].isDigit()) it[i]
                else {
                    spelledDigits[spelledDigits.keys.firstOrNull {
                        it.length + i <= line.length && line.substring(i, i + it.length) == it
                    }]?.digitToChar()
                }

                i--
            }

            Integer.parseInt("${first}${second}")
        }

        println(sum)
    }
}