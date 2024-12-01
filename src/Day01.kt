import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        return dayOnePartOne(input)
    }

    fun part2(input: List<String>): Int {
        return dayOnePartTwo(input)
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(listOf("3   14")) == 11)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    val p1 = part1(input).also { it.println() }
    val p2 = part2(input).also { it.println() }

}

private fun dayOnePartOne(lines: List<String>): Int {
    val (left, right) = lines.map { line ->
        val (first, second) = line.split("   ")
        first.toInt() to second.toInt()
    }.unzip()

    val result = left.sorted()
        .zip(right.sorted())
        .sumOf { (first, second) ->
            abs(first - second)
        }
    return result
}

private fun dayOnePartTwo(lines: List<String>): Int {
    val (left, right) = lines.map { line ->
        val (first, second) = line.split("   ")
        first.toInt() to second.toInt()
    }.unzip()

    val rightMap = right.groupingBy { it }.eachCount()
    val result = left.fold(initial = 0, operation = { acc, item ->
        acc + item * rightMap.getOrDefault(item, 0)
    })
    return result
}