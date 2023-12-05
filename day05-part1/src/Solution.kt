import java.io.File

fun main(){
    Solution().solve(File("day05-part1/src/input.txt").readLines())
}

class Solution{
    fun solve(input: List<String>){
        val seedRanges = input[0].substringAfter(": ")
            .split(' ')
            .map { SeedRange(it.toLong(), 1)}
        val sections = input
            .flatMapIndexed { index, x ->
                when {
                    index == 0 || index == input.lastIndex -> listOf(index)
                    x.isEmpty() -> listOf(index - 1, index + 1)
                    else -> emptyList()
                }
            }
            .chunked(2) { (from, to) -> input.slice(from..to) }
            .drop(1)
            .map { lines ->
                val titleSplit = lines[0].split('-', ' ')
                val ranges = lines.drop(1).map { line ->
                    val split = line.split(' ').map { it.toLong() }
                    SectionRange(split[0], split[1], split[2])
                }

                Section(titleSplit[0], titleSplit[2], ranges)
            }

        var eligibleSeedsRanges = seedRanges
        for(section in sections)
            eligibleSeedsRanges = eligibleSeedsRanges.map { transformRange(it, section) }

        val result = eligibleSeedsRanges.minOf { it.start }
        println(result)
    }

    private fun transformRange(seedRange: SeedRange, section: Section): SeedRange {
        val match = section.ranges.firstOrNull { Math.max(seedRange.start, it.sourceStart) <= Math.min(seedRange.start + seedRange.length - 1, it.sourceStart + it.length - 1) }
        return if(match != null) {
            val offset = match.destinationStart - match.sourceStart
            val left = Math.max(seedRange.start, match.sourceStart)
            val right = Math.min(seedRange.start + seedRange.length - 1, match.sourceStart + match.length - 1)
            SeedRange(left + offset, (right - left) + 1)
        } else
            seedRange
    }
}

data class SeedRange(val start: Long, val length: Long)
data class Section(val from: String, val to: String, val ranges: List<SectionRange>)
data class SectionRange(val destinationStart: Long, val sourceStart: Long, val length: Long)