fun main() {
    fun part1(input: List<String>): Int {
        return dayTwoPartOne(input)
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val inputTest = readInput("Day02_test")
    val testPart1 = part1(inputTest)
    check(testPart1 == 2)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

fun dayTwoPartOne(input: List<String>): Int {
    if (input.isEmpty()) return 0

    return input.fold(0) { acc, i ->
        acc + if (checkReport(i)) 1 else 0
    }
}

fun checkReport(report: String): Boolean {
    if (report.isBlank()) return false

    val levels = report.split(" ")
        .map { it.toInt() }
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