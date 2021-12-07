import kotlin.math.abs

fun main() {
    fun part1(input: String): Int {
        val positions = input.split(",").map { it.toInt() }
        var smallestAmountOfFuel = Int.MAX_VALUE
        for (pos1 in positions) {
            var sum = 0
            for (pos2 in positions) {
                if (pos1 == pos2) continue
                sum += abs(pos1 - pos2)
            }
            if (sum < smallestAmountOfFuel)
                smallestAmountOfFuel = sum
        }
        return smallestAmountOfFuel
    }

    fun part2(input: String): Int {
        val positions = input.split(",").map { it.toInt() }
        val minPosition = positions.minOf { it }
        val maxPosition = positions.maxOf { it }
        var smallestAmountOfFuel = Int.MAX_VALUE
        for (pos1 in minPosition .. maxPosition) {
            var sum = 0
            for (pos2 in positions) {
                if (pos1 == pos2) continue
                sum += (abs(pos1-pos2)*(abs(pos1-pos2)+1))/2
            }
            if (sum < smallestAmountOfFuel)
                smallestAmountOfFuel = sum
        }
        return smallestAmountOfFuel
    }

//    val testInput = readSingleLineInput("Day07_test")
//    check(part1(testInput) == 37)
//    check(part2(testInput) == 168)

    val input = readSingleLineInput("Day07")
    println(part1(input))
    println(part2(input))
}