fun main() {

    val numberOfCubes = mapOf("red" to 12, "green" to 13, "blue" to 14)

    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            val game = line.split(":")
            val sets = game[1].split(";")
            var possibleSet = true
            var index = 0
            while (possibleSet && index < sets.size) {
                val cubes = buildMap {
                    sets[index].split(",").map {
                        val cube = it.trim().split(" ")
                        put(cube[1], cube[0].toInt())
                    }
                }
                cubes.map { (key, value) ->
                    if (value > (numberOfCubes[key] ?: 0)) {
                        possibleSet = false
                    }
                }
                index++
            }
            if (possibleSet) {
                game[0].trim().split(" ")[1].toInt()
            } else {
                0
            }
        }
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 8)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}