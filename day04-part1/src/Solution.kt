import java.io.File

fun main(){
    Solution().solve(File("day04-part1/src/input.txt").readLines())
}

class Solution{
    fun solve(input: List<String>){
        var sum = 0
        for(line in input){
            val numbers = line.substringAfter(':').split('|').map { seq -> seq.split(" ").filter { it != "" }.map { Integer.parseInt(it) } }
            val winners = numbers[1].count { numbers[0].contains(it) }
            if(winners > 0)
                sum += 1 shl winners - 1
        }

        println(sum)
    }
}