import kotlin.math.abs

fun manhattan(source: Index2D, destination: Index2D): Int{
    return abs(destination.column - source.column) + abs(destination.row - source.row)
}

fun manhattan(source: BigIndex2D, destination: BigIndex2D): Long{
    return abs(destination.column - source.column) + abs(destination.row - source.row)
}