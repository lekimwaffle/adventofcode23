import java.io.File

fun main(){
    Solution().solve(File("day08-part1/src/input.txt").readLines())
}

class Solution{
    private val regex = Regex("(\\w{3}) = \\((\\w{3}), (\\w{3})\\)")

    fun solve(input: List<String>){
        val instructions = input[0]
        val nodes = input.subList(2, input.size).associate {
            val match = regex.find(it)!!
            match.groupValues[1] to Nav(match.groupValues[2], match.groupValues[3])
        }

        var steps = 0L
        var node = "AAA"
        while(true){
            val instruction = instructions[(steps % instructions.length).toInt()]
            node = if(instruction == 'L') nodes[node]!!.l else nodes[node]!!.r
            steps++
            if(node == "ZZZ")
                break
        }

        println(steps)
    }
}

data class Nav(val l: String, val r: String)