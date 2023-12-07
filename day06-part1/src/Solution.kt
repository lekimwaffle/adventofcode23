import java.io.File

fun main(){
    Solution().solve(File("day06-part1/src/input.txt").readLines())
}

class Solution{
    fun solve(input: List<String>){
        val times = input[0].substring(5).split(' ').filter{ it.isNotEmpty() }.map { Integer.parseInt(it)  }
        val distances = input[1].substring(9).split(' ').filter{ it.isNotEmpty() }.map { Integer.parseInt(it)  }
        val sum = distances.mapIndexed { i: Int, distance: Int ->
            val time = times[i]
            val left = (0..time).first { (time - it) * it > distance }
            val right = (0..time).reversed().first { (time - it) * it > distance }
            (right - left) + 1
        }.reduce { acc, i -> acc * i}
        println(sum)
    }
}