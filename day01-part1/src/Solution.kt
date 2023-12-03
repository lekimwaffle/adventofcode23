import java.io.File

fun main(){
    Solution().solve(File("day01-part1/src/input.txt").readLines())
}

class Solution{
    fun solve(input: List<String>){
        val sum = input.sumOf {
            val digits = it.filter { it.isDigit() }
            Integer.parseInt("${digits.first()}${digits.last()}")
        }
        println(sum)
    }
}