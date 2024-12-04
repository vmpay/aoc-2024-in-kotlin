fun main() {
    fun part1(input: List<String>): Int {
        return day04Part1(input)
    }

    fun part2(input: List<String>): Int {
        return day04Part2(input)
    }

    val inputTest = readInput("Day04_test")
    check(countThereAndBack(inputTest) == 5)
    check(countThereAndBack(transformVertical(inputTest)) == 3)
    check(countThereAndBack(diagonalSlices(inputTest)) == 10)

    check(part1(inputTest) == 18)
    check(part2(inputTest) == 9)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}

fun day04Part1(input: List<String>): Int {
    val horizontal = countThereAndBack(input)
    val vertical = countThereAndBack(transformVertical(input))
    val diagonal = countThereAndBack(diagonalSlices(input))
    return horizontal + vertical + diagonal
}

fun diagonalSlices(input: List<String>): List<String> {
    val rows = input.size
    val cols = input[0].length
    val diagonals = mutableListOf<String>()

    for (d in 0 until (rows + cols - 1)) {
        val diagonal = StringBuilder()
        for (i in maxOf(0, d - cols + 1)..minOf(d, rows - 1)) {
            val j = d - i
            if (j in 0 until cols) {
                diagonal.append(input[i][j])
            }
        }
        diagonals.add(diagonal.toString())
    }
    for (d in 0 until (rows + cols - 1)) {
        val diagonal = StringBuilder()
        for (i in maxOf(0, d - cols + 1)..minOf(d, rows - 1)) {
            val j = cols - 1 - (d - i)
            if (j in 0 until cols) {
                diagonal.append(input[i][j])
            }
        }
        diagonals.add(diagonal.toString())
    }
    return diagonals
}

fun transformVertical(input: List<String>): List<String> {
    val result = mutableListOf<String>()
    repeat(input.first().length) {
        result.add("")
    }
    input.forEachIndexed { _, line ->
        line.forEachIndexed { j, character ->
            result[j] = result[j] + character
        }
    }
    return result
}

fun countThereAndBack(input: List<String>): Int {
    val there = Regex("XMAS").findAll(
        input.fold("") { acc, i -> "$acc|$i" }
    ).count()
    val back = Regex("SAMX").findAll(
        input.fold("") { acc, i -> "$acc|$i" }
    ).count()
    return there + back
}

fun day04Part2(input: List<String>): Int {
    var count = 0
    for (i in 1 until input[0].length - 1) {
        for (j in 1 until input.size - 1) {
            if (input[i][j] == 'A') {
                if (checkA(input, i, j)) {
                    count++
                }
            }
        }
    }
    return count
}

fun checkA(input: List<String>, i: Int, j: Int): Boolean {
    val diagonal1 = "${input[i - 1][j - 1]}${input[i][j]}${input[i + 1][j + 1]}"
    val diagonal2 = "${input[i - 1][j + 1]}${input[i][j]}${input[i + 1][j - 1]}"
    return (diagonal1 == "MAS" || diagonal1 == "SAM") && (diagonal2 == "MAS" || diagonal2 == "SAM")
}
