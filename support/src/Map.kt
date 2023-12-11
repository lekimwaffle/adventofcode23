class Map(var lowerBound: Int, var rightBound: Int, var upperBound: Int = 0, var leftBound: Int = 0, var step: Int = 1) {
    fun neighboursOf(index2D: Index2D, includeDiagonals: Boolean = false, filterValidate: Boolean = true): List<Index2D>{
        val neighbours = listOf(
            leftOf(index2D),
            rightOf(index2D),
            topOf(index2D),
            bottomOf(index2D))

        if(includeDiagonals){
            neighbours.plus(listOf(
                topLeftOf(index2D),
                topRightOf(index2D),
                bottomLeftOf(index2D),
                bottomRightOf(index2D))
            )
        }

        return if(filterValidate) neighbours.filter(::validate) else neighbours
    }

    fun leftOf(index2D: Index2D): Index2D = Index2D(index2D.column - step, index2D.row)
    fun topLeftOf(index2D: Index2D): Index2D = Index2D(index2D.column - step, index2D.row - step)
    fun rightOf(index2D: Index2D): Index2D = Index2D(index2D.column + step, index2D.row)
    fun topRightOf(index2D: Index2D): Index2D = Index2D(index2D.column + step, index2D.row - step)
    fun topOf(index2D: Index2D): Index2D = Index2D(index2D.column, index2D.row - step)
    fun bottomLeftOf(index2D: Index2D): Index2D = Index2D(index2D.column - step, index2D.row + step)
    fun bottomOf(index2D: Index2D): Index2D = Index2D(index2D.column, index2D.row + step)
    fun bottomRightOf(index2D: Index2D): Index2D = Index2D(index2D.column + step, index2D.row + step)
    fun validate(index2D: Index2D): Boolean = index2D.column >= leftBound && index2D.column <= rightBound && index2D.row >= upperBound && index2D.row <= lowerBound

    companion object {
        fun fromInput(input: List<String>): Map {
            return Map(input.indices.last, input.maxOf { it.indices.last })
        }
    }
}