fun main() {
    fun part1(input: List<String>): Int {
        val gamma = StringBuilder()
        val epsilon = StringBuilder()
        for (i in 0 until input.first().length) {
            val currentBitList = input.map { it[i] }
            val numberOfZeros = currentBitList.count { it == '0' }
            val numberOfOnes = currentBitList.count { it == '1' }

            if (numberOfOnes < numberOfZeros) {
                gamma.append("0")
                epsilon.append("1")
            } else {
                gamma.append("1")
                epsilon.append("0")
            }
        }
        return gamma.toString().toInt(2) * epsilon.toString().toInt(2)
    }

    fun part2(input: List<String>): Int {
        var oxygen = ""
        var oxygenBitStringsToConsider = input
        outer@ for (bits in oxygenBitStringsToConsider) {
            for (i in 0 until input.first().length) {
                if (oxygenBitStringsToConsider.size == 1) {
                    oxygen = oxygenBitStringsToConsider.first(); break@outer
                }

                val currentBitList = oxygenBitStringsToConsider.map { it[i] }
                val numberOfZeros = currentBitList.count { it == '0' }
                val numberOfOnes = currentBitList.count { it == '1' }
                val bitToKeep = if (numberOfOnes >= numberOfZeros) '1' else '0'

                oxygenBitStringsToConsider = oxygenBitStringsToConsider.filter { it[i] == bitToKeep }
            }
        }

        var co2 = ""
        var co2BitStringsToConsider = input
        outer@ for (bits in co2BitStringsToConsider) {
            for (i in 0 until input.first().length) {
                if (co2BitStringsToConsider.size == 1) {
                    co2 = co2BitStringsToConsider.first(); break@outer
                }

                val currentBitList = co2BitStringsToConsider.map { it[i] }
                val numberOfZeros = currentBitList.count { it == '0' }
                val numberOfOnes = currentBitList.count { it == '1' }
                val bitToKeep = if (numberOfOnes >= numberOfZeros) '0' else '1'

                co2BitStringsToConsider = co2BitStringsToConsider.filter { it[i] == bitToKeep }
            }
        }
        return oxygen.toInt(2) * co2.toInt(2)
    }

//    val testInput = readInput("Day03_test")
//    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}