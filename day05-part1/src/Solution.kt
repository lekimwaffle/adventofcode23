import java.io.File

fun main(){
    Solution().solve(File("day05-part1/src/input.txt").readLines())
}

class Solution{
    fun solve(input: List<String>){
        val seeds = input[0].substringAfter(": ")
            .split(' ')
            .map { SeedRange(it.toLong(), 1) }

        val maps = input.subList(2, input.size)
            .fold(mutableListOf(ArrayList<String>())) { list, item -> list.apply {
                if (item.isEmpty()) add(ArrayList())
                else last().add(item) }
            }
            .map {
                it.subList(1, it.size).map { line ->
                    val split = line.split(' ').map { s -> s.toLong() }
                    SectionRange(split[0], split[1], split[2])
                }
            }

        val result = maps.fold(seeds) { acc, section -> acc.map { transformRange(it, section) }.flatten() }.minOf { it.start }
        println(result)
    }

    private fun transformRange(seedRange: SeedRange, section: List<SectionRange>): List<SeedRange> {
        return section.map { SectionInterval(Math.max(seedRange.start, it.sourceStart), Math.min(seedRange.start + (seedRange.length - 1), it.sourceStart + (it.length - 1)), it) }
            .filter { it.left <= it.right }
            .map { match ->
                val offset = match.section.destinationStart - match.section.sourceStart
                SeedRange(match.left + offset, (match.right - match.left) + 1)
            }
            .ifEmpty { listOf(seedRange) }
    }
}

data class SeedRange(val start: Long, val length: Long)
data class SectionRange(val destinationStart: Long, val sourceStart: Long, val length: Long)
data class SectionInterval(val left: Long, val right: Long, val section: SectionRange)