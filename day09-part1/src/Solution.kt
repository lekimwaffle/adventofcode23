import java.io.File

fun main(){
    Solution().solve(File("day09-part1/src/input.txt").readLines())
}

class Solution{
    fun solve(input: List<String>){
        var sum = 0
        val sequences = input.map { line -> line.split(' ').map { Integer.parseInt(it) } }
        for(sequence in sequences)
            sum += resolveSequence(sequence)

        println(sum)
    }

    private fun resolveSequence(sequence: List<Int>): Int{
        val comparisons = sequence.windowed(2, 1).map { it[1]-it[0] }
        if(comparisons.all { it == comparisons[0] })
            return comparisons[0] + sequence.last()

        return resolveSequence(comparisons) + sequence.last()
    }
}
