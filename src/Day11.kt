fun main() {
    fun part1(input: MutableList<MutableList<Int>>): Int {
        var totalNbOfFlashes = 0
        repeat(100) {
            step(input)
            totalNbOfFlashes += input.countFlashes()
        }
        return totalNbOfFlashes
    }

    fun part2(input: MutableList<MutableList<Int>>): Int {
        val steps = 1_000_000
        repeat(steps) {
            step(input)
            if (input.countFlashes() == 100) return it+1
        }
        return steps
    }

    val testInput = readGridInput("Day11_test")
//    check(part1(testInput) == 1656)
    check(part2(testInput) == 195)

    val input = readGridInput("Day11")
//    println(part1(input))
    println(part2(input))
}

fun step(input: MutableList<MutableList<Int>>) {
    for (i in input.indices) {
        for (j in input[0].indices) {
            input[i][j]++
            if (input[i][j] == 10)
                flash(i, j, input)
        }
    }
}

fun flash(x: Int, y: Int, input: MutableList<MutableList<Int>>) {
   for (i in -1 .. 1) {
       for (j in -1 .. 1) {
           if ((x+i >= 0 && x+i < input.size) && (y+j >= 0 && y+j < input[0].size)) {
               input[x+i][y+j]++
               if (input[x+i][y+j] == 10)
                   flash(x+i, y+j, input)
           }
       }
   }
}

fun MutableList<MutableList<Int>>.countFlashes(): Int {
    var counter = 0
    for (row in this) {
        for (num in row) {
            if (num >= 10) counter++
        }
    }
    this.map { it.mapInPlace { i -> if (i >= 10) 0 else i } }
    return counter
}

fun readGridInput(name: String): MutableList<MutableList<Int>> {
    val input = readInput(name)
    val output = mutableListOf<MutableList<Int>>()
    for (line in input) {
        val num = line.toCharArray().map { it.toString().toInt() }.toMutableList()
        output.add(num)
    }
    return output
}