import jetbrains.letsPlot.coordFixed
import jetbrains.letsPlot.export.ggsave
import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.letsPlot
import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val (points, folds) = input.formatInput()
        val (axis, point) = folds.first().split(" ").last().split("=")

        val pointsAfterFold = doFold(axis, point.toInt(), points)
        return pointsAfterFold.size
    }

    fun part2(input: List<String>) {
        val (points, folds) = input.formatInput()
        var pointsAfterFold = mutableSetOf<Pair<Int, Int>>()
        pointsAfterFold.addAll(points.toList())
        for (fold in folds) {
            val (axis, point) = fold.split(" ").last().split("=")
            pointsAfterFold = doFold(axis, point.toInt(), pointsAfterFold.toList())
        }
        val xs = pointsAfterFold.map { it.first }
        val ys = pointsAfterFold.map { -it.second }

        val data = mapOf<String, List<*>>("x" to xs, "y" to ys)
        val plot = letsPlot(data) + geomPoint(size = 10) { x = "x"; y = "y" }
        ggsave(plot + coordFixed(.99), "out.svg")
    }

//    val testInput = readInput("Day12_test")
//    check(part1(testInput) == 17)
//    part2(testInput)
//    check(part2(testInput) == 7)

    val input = readInput("Day12")
    println(part1(input))
    part2(input)
}

private fun List<String>.formatInput(): Pair<List<Pair<Int, Int>>, List<String>> {
    val split = this.indexOf("")
    val points = this.subList(0, split).map {
        val (x, y) = it.split(",").map { i -> i.toInt() }
        Pair(x, y)
    }
    val folds = this.subList(split+1, this.size)
    return Pair(points, folds)
}

fun doFold(axis: String, point: Int, points: List<Pair<Int, Int>>): MutableSet<Pair<Int, Int>> {
    val pointsAboveFold = points.filter { if (axis == "x") it.first < point else it.second < point }
    val pointsBelowFold = points.filter { if (axis == "x") it.first > point else it.second > point }

    val pointsAfterFold = mutableSetOf<Pair<Int, Int>>()
    pointsAfterFold.addAll(pointsAboveFold)
    for (p in pointsBelowFold) {
        val newPoint = if (axis == "x")
            Pair(abs(p.first - 2 * point.toInt()), p.second)
        else
            Pair(p.first ,abs(p.second - 2 * point.toInt()))

        pointsAfterFold.add(newPoint)
    }
    return pointsAfterFold
}
