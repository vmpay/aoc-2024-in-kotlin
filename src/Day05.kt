fun main() {
    fun part1(input: List<String>): Int {
        return parseRulesAndUpdates(input)
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val inputTest = readInput("Day05_test")
    check(parseRulesAndUpdates(inputTest) == 143)

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}

fun parseRulesAndUpdates(input: List<String>): Int {
    val (rules, updates) = parseInput(input)
    return validateUpdates(rules, updates)
}

fun validateUpdates(rules: List<Pair<String, String>>, updates: List<List<String>>): Int =
    updates.fold(0) { acc, i -> acc + validateUpdate(rules, i) }

fun parseInput(input: List<String>): Pair<List<Pair<String, String>>, List<List<String>>> {
    var isRule = true
    val rules = mutableListOf<Pair<String, String>>()
    val updates = mutableListOf<List<String>>()
    input.forEach {
        if (it.isBlank()) {
            isRule = false
        } else {
            if (isRule) {
                rules.add(it.split("|").run { first() to last() })
            } else {
                updates.add(it.split(","))
            }
        }
    }
    return rules to updates
}

fun validateUpdate(rules: List<Pair<String, String>>, update: List<String>): Int {
    val validated = mutableListOf<String>()
    update.forEach { current ->
        if (current !in validated) {
            if (rules.filter { it.first == current }.any { it.second in validated }) {
                return 0
            } else {
                validated.add(current)
            }
        }
    }
    return update[update.size / 2].toInt()
}