fun main() {
    fun part1(input: List<String>): Long {
        return daySevenPartOne(input)
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val testInput = readInput("Day07_test")
    check(part1(testInput) == 3749L)
//    check(part2(testInput) == 6)

    val input = readInput("Day07")
    val p1 = part1(input).also { it.println() }
    val p2 = part2(input).also { it.println() }
}

private fun daySevenPartOne(input: List<String>): Long {
    val map = input.map {
        it.split(":")
            .run {
                first().toLong() to
                        last()
                            .split(" ")
                            .filter { it.isNotBlank() }
                            .map { it.toLong() }
            }
    }
    var res = 0L
    map.forEach {
        if (check(it.first, it.second.lastIndex, listOf(Operator.ADD, Operator.MULTIPLY), it.second)) {
            res += it.first
        }
    }
    println("res = $res")
    return res
}

private fun check(current: Long, i: Int, operators: List<Operator>, numbers: List<Long>): Boolean {
    if (i == 0) return current == numbers.first()

    return operators.any { operator ->
        operator.canYield(current, numbers[i]) && check(operator.reverse(current, numbers[i]), i - 1, operators, numbers)
    }
}

private enum class Operator(
    val canYield: (Long, Long) -> Boolean,
    val reverse: (Long, Long) -> Long
) {
    ADD(
        canYield = { a, b -> a - b >= 0 },
        reverse = { a, b -> a - b }
    ),
    MULTIPLY(
        canYield = { a, b -> a % b == 0L },
        reverse = { a, b -> a / b }
    ),
}