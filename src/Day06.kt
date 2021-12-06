fun main() {
    fun part1(input: String): Int {
        val fishLives = input.split(",").map { it.toInt() }.toMutableList()
        repeat(79) {
            fishLives.mapInPlace { it - 1 }
            val nbOfNewFish = fishLives.count { it == 0 }
            repeat(nbOfNewFish) {
                fishLives.add(9)
            }
            fishLives.replaceAll { if (it == 0) 7 else it }
        }
        return fishLives.size
    }

    fun part2(input: String): Long {
        val fishLives = input.split(",").map { it.toInt() }
        var ages = LongArray(9)
        for (life in fishLives) ages[life]++

        repeat(256) {
            val newFish = LongArray(9)
            for (i in 1 until 9)
                newFish[i-1] = ages[i]

            newFish[8] = ages[0]
            newFish[6] += ages[0]
            ages = newFish
        }
        return ages.sum()
    }

//    val testInput = readSingleLineInput("Day06_test")
//    check(part1(testInput) == 5934)
//    check(part2(testInput) == 26984457539)

    val input = readSingleLineInput("Day06")
    println(part1(input))
    println(part2(input))
}