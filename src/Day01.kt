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
        var currentSum = input[0] + input[1] + input[2]
        for (i in 5 until input.size) {
            val tempSum = input[i] + input[i-1] + input[i-2]
            if (tempSum > currentSum) increased++
            currentSum = tempSum
        }
        return increased
    }

//    val testInput = readInputAsIntegers("Day01_test")
//    check(part1(testInput) == 7)

    val input = readInputAsIntegers("Day01")
    println(part1(input))
    println(part2(input))
}
