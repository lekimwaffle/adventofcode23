import java.io.File

fun main(){
    Solution().solve(File("day10-part2/src/input.txt").readLines())
}

class Solution{
    fun solve(input: List<String>){
        val pipes = input.flatMapIndexed { row, line ->
            line.mapIndexed { col, char ->
                Position(col, row) to char
             }.filter { it.second != '.' }}
            .associate { it.first to it.second }

        fun getSurroundings(position: Position): List<Position>{
            val surroundings =  when(pipes[position]){
                '|' -> listOf(Position(position.col, position.row - 1), Position(position.col, position.row + 1))
                '-' -> listOf(Position(position.col - 1, position.row), Position(position.col + 1, position.row))
                'L' -> listOf(Position(position.col + 1, position.row), Position(position.col, position.row - 1))
                'J' -> listOf(Position(position.col - 1, position.row), Position(position.col, position.row - 1))
                '7' -> listOf(Position(position.col - 1, position.row), Position(position.col, position.row + 1))
                'F' -> listOf(Position(position.col + 1, position.row), Position(position.col, position.row + 1))
                'S' -> listOf(Position(position.col, position.row - 1), Position(position.col, position.row + 1), Position(position.col - 1, position.row), Position(position.col + 1, position.row)). filter { getSurroundings(it).contains(position) }
                else -> listOf()
            }.filter { pipes.containsKey(it) }
            return surroundings
        }

        fun getConnections(position: Position): List<Position>{
            return getSurroundings(position).filter { getSurroundings(it).contains(position) }
        }

        val start = pipes.firstNotNullOf { if(it.value == 'S')  it.key else null }
        val mainLoop = mutableListOf<Position>()
        val yetToCheck = mutableListOf<Position>()
        fun traverse(start: Position): List<Position>{
            if(mainLoop.contains(start))
                return listOf()

            mainLoop.add(start)
            return getConnections(start)
        }

        yetToCheck.add(start)
        while(yetToCheck.isNotEmpty()){
            val first = yetToCheck.first()
            yetToCheck.remove(first)
            yetToCheck.addAll(traverse(first))
        }

        var count = 0
        for (y in input.indices) {
            var inside = false
            for (x in input.first().indices) {
                if (Position(x, y) in mainLoop && input[y][x] in "|LJS")
                    inside = !inside

                if (Position(x, y) !in mainLoop && inside)
                    count++
            }
        }

        println(count)
    }
}

data class Position(val col: Int, val row: Int)