import java.io.File

fun main(){
    Solution().solve(File("day06-part2/src/input.txt").readLines())
}

class Solution{
    fun solve(input: List<String>){
        val time = input[0].substring(5).replace(" ", "").toLong()
        val distance = input[1].substring(9).replace(" ", "").toLong()
        val left = (0..time).first { (time - it) * it > distance }
        val right = (0..time).reversed().first { (time - it) * it > distance }
        println((right - left) + 1)
    }
}