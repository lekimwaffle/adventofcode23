import java.io.File

fun main(){
    Solution().solve(File("day03-part1/src/input.txt").readLines())
}

class Solution{
    fun solve(input: List<String>){
        val numbers = mutableListOf<Number>()
        val symbols = mutableListOf<Symbol>()
        var x = 0
        for((y, line) in input.withIndex()){
            while(x < line.length){
                if( line[x] == '.'){
                    x++
                } else if (line[x].isDigit()){
                    val number = line.substring(x).takeWhile { it.isDigit() }
                    numbers.add(Number(x, y, x + number.length - 1, Integer.parseInt(number)))
                    x += number.length
                } else {
                    symbols.add(Symbol(x, y, line[x]))
                    x++
                }
            }
            x = 0
        }

        val sum = symbols.sumOf { symbol ->
            val matches = numbers.filter { symbol.y >= it.y - 1 && symbol.y <= it.y + 1 && symbol.x >= it.x - 1 && symbol.x <= it.xEnd + 1 }
            matches.sumOf { it.value }
        }
        println(sum)
    }
}

data class Number(val x: Int, val y: Int, val xEnd: Int, val value: Int)
data class Symbol(val x: Int, val y: Int, val value: Char)