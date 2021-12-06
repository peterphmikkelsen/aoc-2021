import java.io.File
import kotlin.math.max
import kotlin.math.min

fun main() {
    fun part1(input: List<Line>): Int {
        val filteredInput = input.filter { it.p1.x == it.p2.x || it.p1.y == it.p2.y }
        val (x, y) = filteredInput.maxXandY()

        val lineMap = Array(y+1) { IntArray(x+1) }
        for (line in filteredInput) {
            val x1 = line.p1.x; val x2 = line.p2.x
            val y1 = line.p1.y; val y2 = line.p2.y

            if (x1 == x2) {
                for (i in min(y1, y2)..max(y1, y2))
                    lineMap[i][x1]++
            } else if (y1 == y2) {
                for (i in min(x1, x2)..max(x1, x2))
                    lineMap[y1][i]++
            }
        }

        var counter = 0
        for (row in lineMap)
            counter += row.count { it >= 2 }

        return counter
    }

    fun part2(input: List<Line>): Int {
        val (x, y) = input.maxXandY()

        val lineMap = Array(y+1) { IntArray(x+1) }
        for (line in input) {
            val x1 = line.p1.x; val x2 = line.p2.x
            val y1 = line.p1.y; val y2 = line.p2.y

            if (x1 == x2) {
                for (i in min(y1, y2)..max(y1, y2))
                    lineMap[i][x1]++
            } else if (y1 == y2) {
                for (i in min(x1, x2)..max(x1, x2))
                    lineMap[y1][i]++
            } else {
                val xs = (if (x1 > x2) (x1 downTo x2) else (x1 .. x2)).toList()
                val ys = (if (y1 > y2) (y1 downTo y2) else (y1 .. y2)).toList()

                for (i in xs.indices)
                    lineMap[ys[i]][xs[i]]++
            }

        }
        var counter = 0
        for (row in lineMap)
            counter += row.count { it >= 2 }

        return counter
    }

    val testInput = readLineInput("Day05_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readLineInput("Day05")
    println(part1(input))
    println(part2(input))
}

fun List<Line>.maxXandY(): Point {
    var x = -1; var y = -1
    for (line in this) {
        if (line.p1.x > x) x = line.p1.x
        if (line.p2.x > x) x = line.p2.x
        if (line.p1.y > y) y = line.p1.y
        if (line.p2.y > y) y = line.p2.y
    }
    return Point(x, y)
}

fun readLineInput(name: String): List<Line> {
    val lines = mutableListOf<Line>()
    val fileReader = File("src", "$name.txt").bufferedReader()
    var line = fileReader.readLine()
    while (line != null) {
        val (startPoint, endPoint) = line.split(" -> ")
        val (x1, y1) = startPoint.split(",").map { it.toInt() }
        val (x2, y2) = endPoint.split(",").map { it.toInt() }
        val p1 = Point(x1, y1); val p2 = Point(x2, y2)
        lines.add(Line(p1, p2))
        line = fileReader.readLine()
    }
    return lines
}

data class Line(val p1: Point, val p2: Point)

data class Point(var x: Int = 0, var y: Int = 0): Comparable<Point> {

    override fun compareTo(other: Point): Int {
        return if (x == other.x)
            if (y - other.y > 0) -1 else 1
        else
            if (x - other.x < 0) -1 else 1
    }
}