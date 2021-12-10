import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()

fun readInputAsIntegers(name: String) = readInput(name).map { it.toInt() }

fun readSingleLineInput(name: String): String = File("src", "$name.txt").bufferedReader().readLine()

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

inline fun <T> MutableList<T>.mapInPlace(mutator: (T)->T) {
    val iterate = this.listIterator()
    while (iterate.hasNext()) {
        val oldValue = iterate.next()
        val newValue = mutator(oldValue)
        if (newValue !== oldValue) {
            iterate.set(newValue)
        }
    }
}

fun String.difference(other: String): String {
    val longestString = if (this.length >= other.length) this else other
    val shortestString = if (this.length < other.length) this else other
    val sb = StringBuilder()
    for (c in longestString) {
        if (!shortestString.contains(c))
            sb.append(c)
    }
    return sb.toString()
}

fun String.intersection(other: String): String {
    val longestString = if (this.length >= other.length) this else other
    val shortestString = if (this.length < other.length) this else other
    val sb = StringBuilder()
    for (c in longestString) {
        if (shortestString.contains(c))
            sb.append(c)
    }
    return sb.toString()
}
