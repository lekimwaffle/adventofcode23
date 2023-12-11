import java.io.File

fun main(){
    Solution().solve(File("day11-part2/src/input.txt").readLines())
}

class Solution{
    private val expansion: Long = 1000000
    fun solve(input: List<String>){
        val extraColumns = input[0].indices.filter { i -> input.all { it[i] == '.' } }
        val extraRows = input.indices.filter { i -> input[i].all { it == '.' } }

        val galaxies = input.flatMapIndexed { row, line -> line.mapIndexed { col, char -> Index2D(col, row) to char }.filter { it.second == '#' }}.map { it.first }
        val distances = galaxies.mapIndexed { i, g -> galaxies.subList(i + 1, galaxies.size).map { getDistance(g, it, extraColumns, extraRows) } }
        val sum = distances.sumOf { it.sum() }
        println(sum)
    }

    private fun getDistance(source: Index2D, destination: Index2D, spaceyColumns: List<Int>, spaceyRows: List<Int>): Long {
        val offsetSource = source + BigIndex2D(getExpansion(source.column, spaceyColumns), getExpansion(source.row, spaceyRows))
        val offsetDestination = destination + BigIndex2D(getExpansion(destination.column, spaceyColumns), getExpansion(destination.row, spaceyRows))
        return manhattan(offsetSource, offsetDestination)
    }

    private fun getExpansion(index: Int, space: List<Int>): Long {
        return space.count { it < index } * (expansion - 1)
    }
}