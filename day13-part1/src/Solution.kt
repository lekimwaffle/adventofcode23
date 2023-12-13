import java.io.File

fun main(){
    Solution().solve(File("day13-part1/src/input.txt").readLines())
}

class Solution{
    fun solve(input: List<String>){
        val patterns = input
            .fold(mutableListOf(ArrayList<String>())) { list, item -> list.apply {
                if (item.isEmpty())
                    add(ArrayList())
                else
                    last().add(item) }
            }
            .map { pattern ->
                val columns = List(pattern[0].length) { MutableList(pattern.size) { false } }
                val rows = List(pattern.size) { MutableList(pattern[0].length)  { false }}
                for((row, line) in pattern.withIndex()){
                    for ((col) in line.withIndex().filter { it.value == '#' }){
                            columns[col][row] = true
                            rows[row][col] = true
                    }
                }

                Pattern(rows, columns)
            }

        var sum = 0
        for(pattern in patterns){
            var foundRow = false
            for(rowIndex in (1..<pattern.rows[0].size)){
                if(pattern.rows.all { verifyMirrored(it, it.reversed(), rowIndex) })
                {
                    sum += rowIndex
                    foundRow = true
                    break
                }
            }

            if(foundRow)
                continue

            for(colIndex in (1..<pattern.columns[0].size)){
                if(pattern.columns.all { verifyMirrored(it, it.reversed(), colIndex) })
                {
                    sum += colIndex * 100
                    break
                }
            }
        }

        println(sum)
    }

    private fun verifyMirrored(input: List<Boolean>, reversed: List<Boolean>, index: Int): Boolean {
        val smallest = Math.min(index, input.size - index)
        val takeInput = input.subList(index - smallest, index)
        val dropReversed = reversed.subList(input.size - smallest - index, input.size - index)
        return takeInput == dropReversed
    }
}

data class Pattern(val rows: List<List<Boolean>>, val columns: List<List<Boolean>>)