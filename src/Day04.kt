import java.io.File

fun main() {
    fun part1(numbers: List<Int>, boards: List<MutableList<Int>>): Int {
        val marked = List(boards.size) { BooleanArray(25) }
        for (number in numbers) {
            for ((index, board) in boards.withIndex()) {
                if (board.contains(number))
                    marked[index][board.indexOf(number)] = true
            }
            // Check if a board has won
            for ((index, board) in marked.withIndex()) {
                if (bingoOnRow(board) || bingoOnColumn(board))
                    return boards[index].filterIndexed { idx, _ -> !board[idx] }.sum() * number
            }
        }
        return -1
    }

    fun part2(numbers: List<Int>, boards: MutableList<MutableList<Int>>): Int {
        val marked = MutableList(boards.size) { BooleanArray(25) }
        val hasWon = BooleanArray(boards.size) // Keep track of who has already won (a board can win multiple times)
        var lastWinningScore = 0
        for (number in numbers) {
            for ((index, board) in boards.withIndex()) {
                if (board.contains(number))
                    marked[index][board.indexOf(number)] = true
            }

            for ((index, board) in marked.withIndex()) {
                if (hasWon[index]) continue
                if (bingoOnRow(board) || bingoOnColumn(board)) {
                    lastWinningScore = boards[index].filterIndexed { idx, _ -> !board[idx] }.sum() * number
                    hasWon[index] = true
                }
            }
        }
        return lastWinningScore
    }

//    val (numbers, boards) = readBingoInput("Day04_test")
//    check(part1(numbers, boards) == 4512)
//    check(part2(numbers, boards) == 1924)

    val (numbers, boards) = readBingoInput("Day04")
    println(part1(numbers, boards))
    println(part2(numbers, boards))
}

private fun bingoOnRow(board: BooleanArray): Boolean {
    for (i in 0 until 25 step 5)
        if (board.copyOfRange(i, i+5).all { it })
            return true

    return false
}

private fun bingoOnColumn(board: BooleanArray): Boolean {
    val columns = mutableListOf<Boolean>()
    for (i in 0 until 5) {
        for (j in 0 until 5)
            columns.add(board[i + j * 5])
    }
    for (i in 0 until 25 step 5)
        if (columns.subList(i, i+5).all { it })
            return true

    return false
}

private fun readBingoInput(name: String): Pair<List<Int>, MutableList<MutableList<Int>>> {
    val fileReader = File("src", "$name.txt").bufferedReader()
    var line = fileReader.readLine()

    val numbers = line.split(",").map { it.toInt() }
    val boards = mutableListOf<MutableList<Int>>()

    fileReader.skip(2)
    while (line != null) {
        val rows = mutableListOf<Int>()
        repeat(5) {
            val row = fileReader.readLine().split(" ").filter { it != " " && it != ""}.map { it.toInt() }
            rows.addAll(row)
        }
        boards.add(rows)
        line = fileReader.readLine()
    }
    return Pair(numbers, boards)
}
