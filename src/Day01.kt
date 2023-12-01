fun main() {

    val digitsInLetters = mapOf("one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5, "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9)
    val indexOfNotFound = -1
    val startingIndex = 0

    fun part1(input: List<String>): Int {
        val sum = input.sumOf { line ->
            val chars = line.toCharArray()
            val (digits, nonDigits) = chars.partition { it.isDigit() }
            digits.first().digitToInt() * 10 + digits.last().digitToInt()
        }

        return sum
    }

    fun MutableMap<Int, Int>.getIndexOfLastNumberInLetters(text: String, previousIndex: Int, digit: Int, digitInLetters: String) {
        val index = text.indexOf(digitInLetters)
        if (index != indexOfNotFound) {
            val storedIndex = previousIndex + index
            put(storedIndex, digit)
            getIndexOfLastNumberInLetters(text.substring(minOf(index + 1, text.length)), storedIndex + 1, digit, digitInLetters)
        }
    }

    fun MutableMap<Int, Int>.findDigitsInLine(line: String) {
        line.toCharArray().mapIndexed { index, char ->
            if (char.isDigit()) put(index, char.digitToInt())
        }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { line ->
            val digitsByIndex = buildMap {
                findDigitsInLine(line)
                digitsInLetters.map { (key, value) ->
                    getIndexOfLastNumberInLetters(line, startingIndex, value, key)
                }
            }
            (digitsByIndex[digitsByIndex.keys.minOrNull()]?.let { it * 10 } ?: 0) +
                    (digitsByIndex[digitsByIndex.keys.maxOrNull()] ?: 0)
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 142)
    val testInput2 = readInput("Day01_part2_test")
    check(part2(testInput2) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
