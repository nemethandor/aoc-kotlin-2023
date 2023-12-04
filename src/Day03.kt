fun main() {


    fun MutableMap<Int, String>.getNumbersByIndex(
        numbers: List<String>,
        line: String
    ): List<Unit> {
        var remainingCharactersOfLine = line
        var previousNumberIndex = 0
        return numbers.map {
            val currentNumberIndex = remainingCharactersOfLine.indexOf(it)
            put(currentNumberIndex + previousNumberIndex, it)
            remainingCharactersOfLine = remainingCharactersOfLine.substring(currentNumberIndex + 1)
            previousNumberIndex += currentNumberIndex + 1
        }
    }

    fun hasSymbol(input: List<String>, lineIndex: Int, startIndex: Int, endIndex: Int) =
        input[lineIndex].substring(startIndex, endIndex).contains("[^\\\\d.]".toRegex())

    fun hasSymbolAbove(index: Int, input: List<String>, startIndex: Int, endIndex: Int): Boolean = if (index == 0) false else {
        hasSymbol(input, index - 1, startIndex, endIndex)
    }

    fun hasSymbolBelow(index: Int, input: List<String>, startIndex: Int, endIndex: Int): Boolean = if (index == input.size - 1) false else {
        hasSymbol(input, index + 1, startIndex, endIndex)
    }

    fun isCharASymbol(line: String, charIndex: Int) = ("[^\\\\d.]".toRegex()).containsMatchIn(line.toCharArray()[charIndex].toString())

    fun isSymbolBefore(key: Int, line: String): Boolean = if (key == 0) false else {
        isCharASymbol(line, key - 1)
    }

    fun isSymbolAfter(key: Int, value: String, line: String): Boolean = if (key + value.length == line.length) false else {
        isCharASymbol(line, key + value.length)
    }

    fun part1(input: List<String>): Int {
        val parts = input.mapIndexed { index, line ->
            val numbers = line.split("\\D+".toRegex()).filter { it.isNotEmpty() }
            val numbersWithIndex = buildMap {
                getNumbersByIndex(numbers, line)
            }
            buildList {
                if (numbersWithIndex.isNotEmpty()) {
                    numbersWithIndex.map { (key, value) ->
                        val startIndex = maxOf(key - 1, 0)
                        val endIndex = minOf(key + value.length + 1, input.size)

                        if (hasSymbolAbove(index, input, startIndex, endIndex) ||
                            hasSymbolBelow(index, input, startIndex, endIndex) ||
                            isSymbolBefore(key, line) ||
                            isSymbolAfter(key, value, line)
                        ) {
                            add(value.toInt())
                        }
                    }
                } else emptyList<String>()
            }
        }
        return parts.flatten().sum()
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 4361)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}