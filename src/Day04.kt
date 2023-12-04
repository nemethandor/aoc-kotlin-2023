import kotlin.math.pow

fun main() {
    fun getMatchingNumbers(line: String): Set<String> {
        val splitGame = line.split(Regex("[:|]"))
        val winningNumbers = splitGame[1].trim().split(Regex("\\s+"))
        val numbers = splitGame[2].trim().split(Regex("\\s+"))
        return winningNumbers.intersect(numbers.toSet())
    }

    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            val matchingNumbers = getMatchingNumbers(line)
            if (matchingNumbers.isNotEmpty()) {
                2.0.pow(matchingNumbers.size - 1)
            } else {
                0.0
            }
        }.toInt()
    }

    fun part2(input: List<String>): Int {
        val cardsByIndex = mutableMapOf<Int, Int>()
        cardsByIndex[0] = 1
        input.mapIndexed { lineIndex, line ->
            cardsByIndex.getOrPut(lineIndex) { 1 }
            val matchingNumbers = getMatchingNumbers(line).size
            for (i in 1..matchingNumbers) {
                val keyOfModifyingCard = lineIndex + i
                val value = cardsByIndex.getOrPut(keyOfModifyingCard) { 1 }
                cardsByIndex[keyOfModifyingCard] = value + (cardsByIndex[lineIndex] ?: 0)
            }
        }
        return cardsByIndex.values.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 13)
    check(part2(testInput) == 30)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}