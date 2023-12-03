import java.io.File

fun main(){
    Solution().solve(File("day02-part1/src/input.txt").readLines())
}

class Solution{
    private val gameRegex = Regex("Game (\\d+)")
    private val colourRegex = Regex("(\\d+ \\w+)")

    fun solve(input: List<String>){
        val games = mutableListOf<Game>()
        for(line in input){
            val idMatch = gameRegex.find(line)
            val id = Integer.parseInt(idMatch?.groups?.get(1)?.value)
            val colours = colourRegex.findAll(line).map {
                val split = it.groupValues[1].split(" ")
                Colour(Integer.parseInt(split[0]), split[1])
            }.toList()

            games.add(Game(id, getHighestColour(colours, "red"), getHighestColour(colours, "green"), getHighestColour(colours, "blue")))
        }

        val sum = games.filter { it.red <= 12 && it.green <= 13 && it.blue <= 14 }.sumOf { it.id }

        println(sum)
    }

    private fun getHighestColour(colours: List<Colour>?, colour: String): Int{
        return colours?.filter { it.colour == colour } ?.maxBy { it.amount }?.amount ?: 0
    }
}

data class Game(val id: Int, var red:Int, var green: Int, var blue: Int)
data class Colour(val amount: Int, val colour: String)