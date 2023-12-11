data class Index2D(val column: Int, val row: Int, val defaultStep: Int = 1) {
    fun toBigIndex(): BigIndex2D {
        return BigIndex2D(column.toLong(), row.toLong())
    }

    operator fun plus(b: Index2D): Index2D {
        return Index2D(column + b.column, row + b.row)
    }

    operator fun plus(b: BigIndex2D): BigIndex2D {
        return toBigIndex() + b
    }
}

class Index2DComparator : Comparator<Index2D>{
    override fun compare(o1: Index2D, o2: Index2D): Int {
        if(o1.row > o2.row)
            return 1

        if(o1.row == o2.row && o1.column > o2.column)
            return 1

        if(o1.row == o2.row && o1.column == o2.column)
            return 0

        return -1
    }
}