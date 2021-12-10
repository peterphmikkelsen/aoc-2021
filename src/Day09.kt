fun main() {
    fun part1(input: List<List<Int>>): Int {
        return input.findLowPoints().sumOf { input[it.first][it.second]+1 }
    }

    fun part2(input: List<List<Int>>): Int {
        val lowPoints = input.findLowPoints()
        val checked = mutableSetOf<Pair<Int, Int>>()
        return lowPoints.map { dfs(it.first, it.second, checked, input) }.sortedDescending().take(3).reduce { acc, i -> acc * i }
    }

    val testInput = read2DArrayInput("Day09_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 1134)

    val input = read2DArrayInput("Day09")
    println(part1(input))
    println(part2(input))
}

fun List<List<Int>>.findLowPoints(): List<Pair<Int, Int>> {
    val lowPoints = mutableListOf<Pair<Int, Int>>()
    for (i in 1 until this.size-1) {
        for (j in 1 until this[1].size-1) {
            if (checkNeighbors(this, i, j))
                lowPoints.add(Pair(i, j))
        }
    }
    return lowPoints
}

fun dfs(x: Int, y: Int, checked: MutableSet<Pair<Int, Int>>, input: List<List<Int>>): Int {
    if (input[x][y] == 9 || checked.contains(Pair(x,y))) return 0
    checked.add(Pair(x,y))
    return dfs(x, y+1, checked, input) + dfs(x, y-1, checked, input) + dfs(x+1, y, checked, input) + dfs(x-1, y, checked, input) + 1
}

fun checkNeighbors(input: List<List<Int>>, x: Int, y: Int): Boolean {
    return neighbors(x, y).all { input[it.first][it.second] > input[x][y] }
}

fun neighbors(x: Int, y: Int): List<Pair<Int, Int>> {
    return listOf(Pair(x-1, y-1), Pair(x-1,y), Pair(x-1,y+1), Pair(x,y+1), Pair(x+1,y+1), Pair(x+1,y), Pair(x+1,y-1), Pair(x,y-1))
}

fun read2DArrayInput(name: String): List<List<Int>> {
    val input = readInput(name)
    val output = mutableListOf<List<Int>>()
    // Pad with 9 all around the input
    output.add("9".repeat(input[0].length+2).toCharArray().map { it.toString().toInt() })
    for (line in input) {
        val num = line.toCharArray().map { it.toString().toInt() }.toMutableList()
        num.add(9)
        num.add(0, 9)
        output.add(num)
    }
    output.add("9".repeat(input[0].length+2).toCharArray().map { it.toString().toInt() })
    return output
}