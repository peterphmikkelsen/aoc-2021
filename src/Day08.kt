fun main() {
    fun part1(input: List<String>): Int {
        var nbOfDigits = 0
        for (line in input) {
            val segments = line.split(" | ")[1].split(" ")
            for (segment in segments) {
                if (segment.length == 2 || segment.length == 4 || segment.length == 3 || segment.length == 7)
                    nbOfDigits++
            }
        }
        return nbOfDigits
    }

    // Hideous code for part 2, but it works...
    fun part2(input: List<String>): Int {
        var sum = 0
        for (line in input) {
            val (first, second) = line.split(" | ")
            val signals = first.split(" ")
            val segments = second.split(" ")

            val one = signals.findOne()
            val two = signals.findTwo()
            val three = signals.findThree()
            val four = signals.findFour()
            val five = signals.findFive()
            val six = signals.findSix()
            val seven = signals.findSeven()
            val eight = signals.findEight()
            val nine = signals.findNine()

            val sb = StringBuilder()
            for (str in segments) {
                if (str.difference(one) == "") sb.append(1)
                else if (str.difference(two) == "") sb.append(2)
                else if (str.difference(three) == "") sb.append(3)
                else if (str.difference(four) == "") sb.append(4)
                else if (str.difference(five) == "") sb.append(5)
                else if (str.difference(six) == "") sb.append(6)
                else if (str.difference(seven) == "") sb.append(7)
                else if (str.difference(eight) == "") sb.append(8)
                else if (str.difference(nine) == "") sb.append(9)
                else sb.append(0)
            }

            sum += sb.toString().toInt()
        }
        return sum
    }

//    val testInput = readInput("Day08_test")
//    check(part1(testInput) == 26)
//    check(part2(testInput) == 61229)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}

fun List<String>.findEight() = this.filter { it.length == 7 }.joinToString("")
fun List<String>.findSeven() = this.filter { it.length == 3 }.joinToString("")
fun List<String>.findFour() = this.filter { it.length == 4 }.joinToString("")
fun List<String>.findOne() = this.filter { it.length == 2 }.joinToString("")
fun List<String>.findSix(): String {
    val one = this.findOne()
    val temp = this.filter { it.length == 6 }
    for (str in temp) {
        if (str.intersection(one).length != 1) continue
        return str
    }
    return ""
}
fun List<String>.findFive(): String {
    val six = this.findSix()
    val temp = this.filter { it.length == 5 }
    for (str in temp) {
        if (str.difference(six).length != 1) continue
        return str
    }
    return ""
}
fun List<String>.findNine(): String {
    val six = this.findSix()
    val five = this.findFive()
    val temp = this.filter { it.length == 6 }
    for (str in temp) {
        if (str == six) continue
        if (str.difference(five).length != 1) continue
        return str
    }
    return ""
}
fun List<String>.findZero(): String {
    val six = this.findSix()
    val nine = this.findNine()
    val temp = this.filter { it.length == 6 }
    for (str in temp) {
        if (str == six || str == nine) continue
        return str
    }
    return ""
}
fun List<String>.findTwo(): String {
    val one = this.findOne()
    val five = this.findFive()
    val temp = this.filter { it.length == 5 }
    for (str in temp) {
        if (str == five) continue
        if (str.intersection(one).length != 1) continue
        return str
    }
    return ""
}
fun List<String>.findThree(): String {
    val five = this.findFive()
    val two = this.findTwo()
    val temp = this.filter { it.length == 5 }
    for (str in temp) {
        if (str == five || str == two) continue
        return str
    }
    return ""
}