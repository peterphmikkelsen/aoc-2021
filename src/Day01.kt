fun main() {
    fun part1(input: List<Int>): Int {
        var increased = 0
        for (i in 1 until input.size) {
            val current = input[i]
            val prev = input[i-1]
            if (current > prev) increased++
        }
        return increased
    }

    fun part2(input: List<Int>): Int {
        var increased = 0
        for (i in 3 until input.size) {
            val currentSum = input[i] + input[i-1] + input[i-2]
            val prevSum = input[i-1] + input[i-2] + input[i-3]
            if (currentSum > prevSum) increased++
        }
        return increased
    }

//    val testInput = readInputAsIntegers("Day01_test")
//    check(part1(testInput) == 7)

    val input = readInputAsIntegers("Day01")
    println(part1(input))
    println(part2(input))
}
