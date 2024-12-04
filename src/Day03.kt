fun main() {
    fun part1(input: List<String>): Int {
        return day3Part1(input)
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val inputTest = readInput("Day03_test")
    check(part1(inputTest) == 161)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}

fun day3Part1(input: List<String>): Int = input.fold(0) { acc, i -> acc + parseLine(i) }

fun parseLine(input: String): Int =
    input.split("mul(")
        .fold(0) { acc, i -> acc + parseCommand(i) }

fun parseCommand(input: String): Int =
    input.split(")")
        .let {
            if (it.size > 1) it.firstOrNull()
            else null
        }
        ?.split(",")
        ?.let {
            val a = it.getOrNull(0)?.toIntOrNull()
            val b = it.getOrNull(1)?.toIntOrNull()
            if (a != null && b != null
                && a in 0 until 1000 && b in 0 until 1000
            ) {
                a * b
            } else {
                0
            }
        } ?: 0