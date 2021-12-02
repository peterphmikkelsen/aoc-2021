fun main() {
    fun part1(input: List<String>): Int {
        var x = 0; var y = 0
        for (command in input) {
            val (direction, num) = command.split(" ")
            val amount = num.toInt()
            when (direction) {
                "forward" -> x += amount
                "down" -> y += amount
                else -> y -= amount
            }
        }
        return x*y //Gives incorrect warning
    }

    fun part2(input: List<String>): Int {
        var x = 0; var y = 0; var aim = 0
        for (command in input) {
            val (direction, num) = command.split(" ")
            val amount = num.toInt()
            when (direction) {
                "forward" -> { x += amount; y += aim * amount } //Gives incorrect warning
                "down" -> aim += amount
                else -> aim -= amount
            }
        }
        return x*y
    }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}