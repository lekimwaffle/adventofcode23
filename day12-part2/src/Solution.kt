import java.io.File

fun main(){
    Solution().solve(File("day12-part2/src/input.txt").readLines())
}

class Solution{
    private val cache = hashMapOf<Pair<String, List<Int>>, Long>()
    fun solve(input: List<String>){
        var records = input.map {line ->
            val split = line.split(' ')
            Record(split[0], split[1].split(',').map { Integer.parseInt(it) })
        }

        records = records.map { Record(it.conditions + "?" + it.conditions + "?" + it.conditions + "?" + it.conditions + "?" + it.conditions, listOf(it.groups, it.groups, it.groups, it.groups, it.groups).flatten()) }
        val sum = records.withIndex().sumOf { calculate(it.value.conditions, it.value.groups)}
        println(sum)
    }

    private fun calculate(conditions: String, groups: List<Int>): Long{
        return cache.getOrPut(conditions to groups){
            var count = 0L
            val reservedEndBits = groups.sum() + groups.size - 1
            for(i in 0..conditions.length - reservedEndBits){
                if((0..<i).any { conditions[it] == '#'})
                    continue
                else if(i + groups[0] < conditions.length && conditions[i + groups[0]] == '#')
                    continue

                if(conditions.substring(i, i + groups[0]).all { it == '#' || it =='?' }){
                    if(groups.size == 1 && ((i + groups[0])..<conditions.length).any { conditions[it] == '#'})
                        continue

                    if(groups.size == 1)
                        count++
                    else
                        count += calculate(conditions.substring(i + groups[0] + 1), groups.drop(1))
                }
            }

            count
        }
    }
}

data class Record(val conditions: String, val groups: List<Int>)