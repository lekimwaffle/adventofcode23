import java.io.File

fun main(){
    Solution().solve(File("day08-part2/src/input.txt").readLines())
}

class Solution{
    private val regex = Regex("(\\w{3}) = \\((\\w{3}), (\\w{3})\\)")

    fun solve(input: List<String>){
        val instructions = input[0]
        val nodes = input.subList(2, input.size).associate {
            val match = regex.find(it)!!
            match.groupValues[1] to Nav(match.groupValues[2], match.groupValues[3])
        }

        val allSteps = mutableListOf<MutableList<Long>>()
        for((i, startingNode) in nodes.keys.filter { it[2] == 'A' }.withIndex()){
            var steps = 0L
            var found = 0
            var node = startingNode
            allSteps.add(mutableListOf())
            while(found <= 1){
                val instruction = instructions[(steps % instructions.length).toInt()]
                node = if(instruction == 'L') nodes[node]!!.l else nodes[node]!!.r
                steps++
                if(node[2] == 'Z'){
                    found++
                    if (found == 1)
                        allSteps[i].add(steps)
                    else
                        allSteps[i].add(steps - allSteps[i][0])
                }
            }
        }

        val longestStep = allSteps.maxBy { it[0] }
        var i = 0
        var totalSteps = 0L
        while(totalSteps == 0L) {
            i++
            val steps = longestStep[0] + (longestStep[1] * i)
            if(allSteps.all { (steps - it[0]) % it[1] == 0L })
                totalSteps = steps
        }

        println(totalSteps)
    }
}

data class Nav(val l: String, val r: String)