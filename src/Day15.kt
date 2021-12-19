import java.util.PriorityQueue

fun main() {
    fun part1(input: List<List<Int>>): Int {
        return dijkstra(input)
    }

    fun part2(input: List<List<Int>>): Int {
        val increasedBoard = increaseBoard(input)
        return dijkstra(increasedBoard)
    }

    val testInput = readGridInput("Day15_test")
//    val testPart2Input = readInput("Day15_part2_test")
//    for (i in testPart2Input.indices)
//        println(increaseBoard(testInput)[i].joinToString("") == testPart2Input[i])

    check(part1(testInput) == 40)
    check(part2(testInput) == 315)

    val input = readGridInput("Day15")
    println(part1(input))
    println(part2(input))
}

private fun dijkstra(input: List<List<Int>>): Int {
    val visited = mutableSetOf<Pair<Int, Int>>()
    val distance = mutableMapOf<Pair<Int, Int>, Int>()
    val q = PriorityQueue<Pair<Pair<Int, Int>, Int>>( compareBy { it.second } )
    q.add(Pair(Pair(0, 0), 0))
    distance[Pair(0, 0)] = 0

    while (q.isNotEmpty()) {
        val currentNode = q.remove()
        visited.add(currentNode.first)
        if (currentNode.first == Pair(input.lastIndex, input[0].lastIndex)) break

        val neighbors = currentNode.first.neighbors()
        for (n in neighbors) {
            val withInFirstAxis = n.first >= 0 && n.first <= input.lastIndex
            val withInSecondAxis = n.second >= 0 && n.second <= input[0].lastIndex
            if (withInFirstAxis && withInSecondAxis) {
                if (n in visited) continue

                val alt = distance[currentNode.first]!! + input[n.first][n.second]
                if (alt < distance.getOrDefault(n, Int.MAX_VALUE)) {
                    distance[n] = alt
                    q.add(Pair(n, alt))
                }
            }
        }
    }
    return distance[Pair(input.lastIndex, input[0].lastIndex)]!!
}

private fun Pair<Int, Int>.neighbors(): List<Pair<Int, Int>> {
    return listOf(
        Pair(this.first - 1, this.second),
        Pair(this.first + 1, this.second),
        Pair(this.first, this.second - 1),
        Pair(this.first, this.second + 1)
    )
}

private fun increaseBoard(input: List<List<Int>>): List<List<Int>> {
    val increasedBoard = mutableListOf<List<Int>>()
    for (row in input)
        increasedBoard.add(row.incrementAndExtend(4))

    val temp = mutableListOf<List<Int>>()
    repeat(4) { i ->
        for (row in increasedBoard)
            temp.add(row.map { if (it + i + 1 > 9) (it + i + 1) % 9 else it + i + 1 })
    }
    increasedBoard.addAll(temp)
    return increasedBoard
}

private fun List<Int>.incrementAndExtend(times: Int): List<Int> {
    var newList = this.map { if (it + 1 == 10) 1 else it+1 }
    var currentList = this
    repeat(times) {
        currentList = currentList + newList
        newList = newList.map { if (it + 1 == 10) 1 else it+1 }
    }
    return currentList
}