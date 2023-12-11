class BigMap(val lowerBound: Long, val rightBound: Long, val upperBound: Long = 0, val leftBound: Long = 0, var step: Long = 1L) {
    fun neighboursOf(index2D: BigIndex2D, includeDiagonals: Boolean = false, filterValidate: Boolean = true): List<BigIndex2D>{
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

    fun leftOf(index2D: BigIndex2D): BigIndex2D = BigIndex2D(index2D.column - step, index2D.row)
    fun topLeftOf(index2D: BigIndex2D): BigIndex2D = BigIndex2D(index2D.column - step, index2D.row - step)
    fun rightOf(index2D: BigIndex2D): BigIndex2D = BigIndex2D(index2D.column + step, index2D.row)
    fun topRightOf(index2D: BigIndex2D): BigIndex2D = BigIndex2D(index2D.column + step, index2D.row - step)
    fun topOf(index2D: BigIndex2D): BigIndex2D = BigIndex2D(index2D.column, index2D.row - step)
    fun bottomLeftOf(index2D: BigIndex2D): BigIndex2D = BigIndex2D(index2D.column - step, index2D.row + step)
    fun bottomOf(index2D: BigIndex2D): BigIndex2D = BigIndex2D(index2D.column, index2D.row + step)
    fun bottomRightOf(index2D: BigIndex2D): BigIndex2D = BigIndex2D(index2D.column + step, index2D.row + step)
    fun validate(index2D: BigIndex2D): Boolean = index2D.column >= leftBound && index2D.column <= rightBound && index2D.row >= upperBound && index2D.row <= lowerBound
}