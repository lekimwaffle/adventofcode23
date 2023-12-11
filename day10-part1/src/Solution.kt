import java.io.File

fun main(){
    Solution().solve(File("day10-part1/src/input.txt").readLines())
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
                'S' -> listOf(Position(position.col, position.row - 1), Position(position.col, position.row + 1), Position(position.col - 1, position.row), Position(position.col + 1, position.row))
                else -> listOf()
            }.filter { pipes.containsKey(it) }
            return surroundings
        }

        fun getConnections(position: Position): List<Position>{
            return getSurroundings(position).filter { getSurroundings(it).contains(position) }
        }

        val start = pipes.firstNotNullOf { if(it.value == 'S')  it.key else null }
        val knownNetwork = mutableMapOf<Position, Int>()
        val yetToCheck = mutableListOf<Pair<Position, Int>>()
        fun traverse(start: Position, step: Int): List<Pair<Position, Int>>{
            if(knownNetwork.containsKey(start) && knownNetwork[start]!! <= step)
                return listOf()

            val connections = getConnections(start)
            knownNetwork[start] = step
            return connections.map { Pair(it, step +1) }
        }
        yetToCheck.add(Pair(start, 0))
        while(yetToCheck.isNotEmpty()){
            val first = yetToCheck.first()
            yetToCheck.remove(first)
            yetToCheck.addAll(traverse(first.first, first.second))
        }

        println(knownNetwork.values.max())
    }
}

data class Position(val col: Int, val row: Int)