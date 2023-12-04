import java.io.File

fun main(){
    Solution().solve(File("day04-part2/src/input.txt").readLines())
}

class Solution{
    fun solve(input: List<String>){
        val cards = MutableList(input.size) { index -> index to 1}.toMap(mutableMapOf())
        for((index, line) in input.withIndex()){
            val numbers = line.substringAfter(':').split('|').map { seq -> seq.split(" ").filter { it != "" }.map { Integer.parseInt(it) } }
            val winners = numbers[1].count { numbers[0].contains(it) }
            if(winners > 0)
                for(ii in 1..winners)
                    cards[ii + index] = cards[ii + index]!! + cards[index]!!
        }

        println(cards.values.sum())
    }
}