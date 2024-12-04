fun main() {
    fun part1(input: List<String>): Int {
        if (input.isEmpty()) return 0

        return input.fold(0) { acc, i ->
            acc + if (dayTwoPartOne(i)) 1 else 0
        }
    }

    fun part2(input: List<String>): Int {
        if (input.isEmpty()) return 0

        return input.fold(0) { acc, i ->
            acc + if (dayTwoPartTwo(i)) 1 else 0
        }
    }

    val inputTest = readInput("Day02_test")
    val testPart1 = part1(inputTest)
    check(testPart1 == 2)
    val testPart2 = part2(inputTest)
    check(testPart2 == 4)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

fun dayTwoPartOne(report: String): Boolean {
    if (report.isBlank()) return false

    val levels = report.split(" ")
        .map { it.toInt() }
    return check(levels)
}

private fun check(levels: List<Int>): Boolean {
    val isIncreasing = levels[0] < levels[1]
    return levels.windowed(size = 2, step = 1)
        .all {
            if (isIncreasing) {
                it[1] - it[0] in 1..3
            } else {
                it[0] - it[1] in 1..3
            }
        }
}

fun dayTwoPartTwo(report: String): Boolean {
    if (report.isBlank()) return false

    val levels = report.split(" ")
        .map { it.toInt() }

    for (i in levels.indices) {
        val list = levels.toMutableList()
            .apply { removeAt(i) }
        if (check(list)) {
            return true
        }
    }
    return false
}
