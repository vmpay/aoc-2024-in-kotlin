fun main() {
    fun part1(input: List<String>): Int {
        return dayEightPartOne(input)
    }

    fun part2(input: List<String>): Int {
        return 34
    }

    val testInput = readInput("Day08_test")
    check(part1(testInput) == 14)
    check(part2(testInput) == 34)

    val input = readInput("Day08")
    val p1 = part1(input).also { it.println() }
    val p2 = part2(input).also { it.println() }
}

private fun dayEightPartOne(input: List<String>): Int =
    countAntiNodes(
        input.joinToString("")
            .toCharArray()
            .distinct()
            .filter { it != '.' },
        input,
    )

private fun countAntiNodes(frequencies: List<Char>, input: List<String>): Int {
    val antennaMap = mutableMapOf<Char, MutableList<Pair<Int, Int>>>()
    for (y in input.indices) {
        for (x in 0 until input[y].length) {
            if (frequencies.any { it == input[y][x] }) {
                antennaMap[input[y][x]] = mutableListOf(x to y)
                    .also { it.addAll(antennaMap[input[y][x]] ?: listOf()) }
            }
        }
    }
    val antiNodes = mutableSetOf<Pair<Int, Int>>()
    antennaMap.values.forEach {
        val pairList = mutableListOf<Pair<Pair<Int, Int>, Pair<Int, Int>>>()
        for (i in 0 until it.size - 1) {
            for (j in i + 1 until it.size) {
                pairList.add(it[i] to it[j])
            }
        }
        pairList.forEach {
            antiNodes.addAll(
                buildAntiNodeForPair(it.first, it.second, input[0].length, input.size)
            )
        }
    }
    return antiNodes.size
}

private fun buildAntiNodeForPair(
    first: Pair<Int, Int>,
    second: Pair<Int, Int>,
    width: Int,
    height: Int,
): Set<Pair<Int, Int>> {
    val xDistance = first.first - second.first
    val yDistance = first.second - second.second
    return setOf(
        first.first + xDistance to first.second + yDistance,
        second.first - xDistance to second.second - yDistance,
    ).filter {
        it.first in 0 until width
                && it.second in 0 until height
    }.toSet()
}