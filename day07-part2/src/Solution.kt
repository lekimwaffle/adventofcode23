import java.io.File

fun main(){
    Solution().solve(File("day07-part2/src/input.txt").readLines())
}

class Solution{
    private val strength = mapOf('A' to 1, 'K' to 2, 'Q' to 3, 'J' to 14, 'T' to 5, '9' to 6, '8' to 7, '7' to 8, '6' to 9, '5' to 10, '4' to 11, '3' to 12, '2' to 13)
    fun solve(input: List<String>){
        val hands = input.map {
            val split = it.split(' ')
            Hand(split[0], Integer.parseInt(split[1]), getHandType(split[0]))
        }

        val ordered = hands.sortedWith(compareByDescending<Hand>{ it.type }
            .thenByDescending { strength[it.cards[0]] }
            .thenByDescending { strength[it.cards[1]] }
            .thenByDescending { strength[it.cards[2]] }
            .thenByDescending { strength[it.cards[3]] }
            .thenByDescending { strength[it.cards[4]] })

        val sum = ordered.map { it.points }.reduceIndexed { i, acc, hand -> acc + ((i + 1) * hand) }
        println(sum)
    }

    private fun getHandType(cards: String): HandType{
        var relevantCards = cards
        var distinct = relevantCards.toList().distinct()
        if(distinct.size > 1 && distinct.contains('J')) {
            val mostCommon = cards.filter { it != 'J' }.groupingBy { it }.eachCount().entries.sortedWith(
                compareByDescending { it.value })[0].key
            relevantCards = String(cards.map { if(it == 'J') mostCommon else it }.toCharArray())
            distinct = relevantCards.toList().distinct()
        }

        if(distinct.size == 1)
            return HandType.FiveOfAKind
        else if(distinct.size == 2) {
            if(relevantCards.count { it == distinct[0] } == 1 || relevantCards.count { it == distinct[0] } == 4)
                return HandType.FourOfAKind
            else if(relevantCards.count { it == distinct[0] } == 2 || relevantCards.count { it == distinct[0] } == 3)
                return HandType.FullHouse
        }
        else if(distinct.size == 3){
            if(distinct.any { d -> relevantCards.count { c -> c == d } == 3})
                return HandType.ThreeOfAKind
            else if (distinct.any { d -> relevantCards.count { c -> c == d } == 2})
                return HandType.TwoPair
        }
        else if(distinct.size == 4)
            return HandType.OnePair

        return HandType.HighCard
    }
}

data class Hand(val cards: String, val points: Int, val type: HandType)
enum class HandType {
    FiveOfAKind,
    FourOfAKind,
    FullHouse,
    ThreeOfAKind,
    TwoPair,
    OnePair,
    HighCard
}