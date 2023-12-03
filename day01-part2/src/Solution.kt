import java.io.File

val spelledDigits = mapOf("one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5, "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9)

fun main(){
    Solution().solve(File("day01-part2/src/input.txt").readLines())
}

class Solution{
    fun solve(input: List<String>){
        val sum = input.sumOf { line ->
            fun findDigit(range: IntProgression):Char {
                for(i in range) {
                    if (line[i].isDigit())
                        return line[i]
                    else {
                        val match = spelledDigits.keys.firstOrNull {
                            it.length + i <= line.length && line.substring(i, i + it.length) == it
                        }

                        if (match != null)
                            return spelledDigits[match]!!.digitToChar()
                    }
                }

                return Char.MIN_VALUE
            }

            val first = findDigit(line.indices)
            val second = findDigit(line.indices.reversed())
            Integer.parseInt("${first}${second}")
        }

        println(sum)
    }
}