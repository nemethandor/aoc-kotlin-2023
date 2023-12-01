fun main() {
    fun part1(input: List<String>): Int {
        val sum = input.sumOf { line ->
            val chars = line.toCharArray()
            val (digits, nonDigits) = chars.partition { it.isDigit() }
            digits.first().digitToInt() * 10 + digits.last().digitToInt()
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 142)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
