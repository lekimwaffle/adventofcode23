import java.io.File

fun main(){
    Solution().solve(File("day02-part1/src/input.txt").readLines())
}

class Solution{
    fun solve(input: List<String>){
        val games = mutableListOf<Game>()
        for(line in input){
            val id = Integer.parseInt(line.substring(5).takeWhile { it.isDigit() })
            var i = line.takeWhile { it != ':' }.count()
            val colours = mutableListOf<Colour>()
            while(line.length > i){
                if(!line[i].isDigit() && !line[i].isLetter())
                    i++
                else{
                    val number = line.substring(i).takeWhile { it.isDigit() }
                    i += number.length + 1
                    val colour = line.substring(i).takeWhile { it.isLetter() }
                    i += colour.length
                    colours.add(Colour(Integer.parseInt(number), colour))
                }
            }

            fun getHighestColour(colour: String): Int{
                return colours.filter { it.colour == colour }.maxOf { it.amount }
            }

            games.add(Game(id, getHighestColour("red"), getHighestColour("green"), getHighestColour("blue")))
        }

        val sum = games.filter { it.red <= 12 && it.green <= 13 && it.blue <= 14 }.sumOf { it.id }

        println(sum)
    }
}

data class Game(val id: Int, var red:Int, var green: Int, var blue: Int)
data class Colour(val amount: Int, val colour: String)