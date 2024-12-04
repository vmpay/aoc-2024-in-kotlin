fun main() {
    fun part1(input: List<String>): Int {
        return day3Part1(input)
    }

    fun part2(input: List<String>): Int {
        return day3Part2(input)
    }

    val inputTest = readInput("Day03_test")
    check(part1(inputTest) == 161)
    check(part2(inputTest) == 48)

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

fun day3Part2(input: List<String>): Int {
    val inputString = input.fold("") { acc, i -> "$acc$i" }
    val splitDoNot = inputString.split("don't")
    var result = parseLine(splitDoNot[0])
    if (splitDoNot.size > 1) {
        for (i in 1 until splitDoNot.size) {
            result += parseLine(
                splitDoNot[i].split("do()")
                    .drop(1).fold("") { acc, i -> "$acc$i" }
            )
        }
    }
    return result
}