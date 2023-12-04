import kotlin.math.pow

fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            val splitGame = line.split(Regex("[:|]"))
            val winningNumbers = splitGame[1].trim().split(Regex("\\s+"))
            val numbers = splitGame[2].trim().split(Regex("\\s+"))
            val matchingNumbers = winningNumbers.intersect(numbers.toSet())
            if (matchingNumbers.isNotEmpty()) {
                2.0.pow(matchingNumbers.size - 1)
            } else {
                0.0
            }
        }.toInt()
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 13)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}