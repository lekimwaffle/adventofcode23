data class BigIndex2D(val column: Long, val row: Long, val defaultStep: Long = 1L) {
    operator fun plus(b: BigIndex2D): BigIndex2D{
        return BigIndex2D(column + b.column, row + b.row)
    }
}

class BigIndex2DComparator : Comparator<BigIndex2D>{
    override fun compare(o1: BigIndex2D, o2: BigIndex2D): Int {
        if(o1.row > o2.row)
            return 1

        if(o1.row == o2.row && o1.column > o2.column)
            return 1

        if(o1.row == o2.row && o1.column == o2.column)
            return 0

        return -1
    }
}