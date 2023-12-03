import java.io.File

fun main(){
    Solution().solve(File("day02-part2/src/input.txt").readLines())
}

class Solution{
    fun solve(input: List<String>){
        var sum = 0
        for(line in input){
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

            fun getHighestColour(colour: String): Int {
                return colours.filter { it.colour == colour }.maxOf { it.amount }
            }

            sum += getHighestColour("red") * getHighestColour( "green") * getHighestColour("blue")
        }

        println(sum)
    }
}

data class Colour(val amount: Int, val colour: String)