fun main() {
    fun part1(input: List<String>): Int {
        val illegalChars = mutableListOf<Char>()
        val stack = ArrayDeque<Char>()
        outer@for (line in input) {
            for (c in line) {
                if (c.isOpeningBracket()) {
                    stack.add(c); continue
                }
                if (c.isClosingBracket()) {
                    if (stack.last().getClosing() == c) {
                        stack.removeLast(); continue
                    }
                    illegalChars.add(c)
                    continue@outer
                }
            }
        }
        return illegalChars.sumOf { it.getPoints(first = true) }
    }

    fun part2(input: List<String>): Long {
        val scores = mutableListOf<Long>()
        outer@for (line in input) {
            val stack = ArrayDeque<Char>()
            for (c in line) {
                if (c.isOpeningBracket()) {
                    stack.addFirst(c.getClosing()); continue
                }
                if (c.isClosingBracket()) {
                    if (stack.first() != c) // Not incomplete (corrupted)
                        continue@outer
                    stack.removeFirst()
                }
            }
            var score = 0L
            for (c in stack)
                score = score * 5 + c.getPoints(first = false)
            scores.add(score)
        }
        return scores.sorted()[(scores.size/2)]
    }

    val testInput = readInput("Day10_test")
    check(part1(testInput) == 26397)
    check(part2(testInput) == 288957L)

    val input = readInput("Day10")
    println(part1(input))
    println(part2(input))
}

fun Char.isOpeningBracket() = (this == '(' || this == '{' || this == '[' || this == '<')
fun Char.isClosingBracket() = (this == ')' || this == '}' || this == ']' || this == '>')

fun Char.getClosing(): Char {
    if (this == '(') return ')'
    if (this == '{') return '}'
    if (this == '[') return ']'
    if (this == '<') return '>'
    throw IllegalArgumentException("Wrong character!")
}

fun Char.getPoints(first: Boolean): Int {
    return when (this) {
        ')' -> if (first) 3 else 1
        ']' -> if (first) 57 else 2
        '}' -> if (first) 1197 else 3
        '>' -> if (first) 25137 else 4
        else -> 0
    }
}