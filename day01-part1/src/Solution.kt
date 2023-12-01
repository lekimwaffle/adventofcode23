import java.io.File

fun main(){
    Solution().Solve(File("day01-part1/src/input.txt").readLines())
}

class Solution{
    fun Solve(input: List<String>){
        val digits = input.map { it.filter { it.isDigit() } }
        var sum = 0
        for(line in digits)
            sum += Integer.parseInt("${line.first()}${line.last()}")

        println(sum)
    }
}