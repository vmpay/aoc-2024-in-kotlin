fun main() {
    fun part1(input: List<String>): Int {
        return daySixPartOne(input)
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val testInput = readInput("Day06_test")
    check(part1(testInput) == 41)

    val input = readInput("Day06")
    val p1 = part1(input).also { it.println() }
    val p2 = part2(input).also { it.println() }
}

class Guard(
    private val input: MutableList<String>,
) {
    private var position: Pair<Int, Int> = 0 to 0
    private var direction: Direction = Direction.S

    init {
        var done = false
        for (y in 0 until input.size) {
            if (done) break
            for (x in 0 until input[y].length) {
                val c = input[y][x]
                if (c != '.' && c != '#') {
                    direction = when (c) {
                        '^' -> Direction.N
                        '>' -> Direction.E
                        '<' -> Direction.W
                        else -> Direction.S
                    }
                    position = x to y
                    done = true
                    break
                }
            }
        }
        val sb = StringBuilder(input[position.second])
        sb.setCharAt(position.first, 'X')
        input[position.second] = sb.toString()
        println("init position $position direction $direction")
    }

    fun turn() {
        direction = Direction.entries[(direction.ordinal + 1) % 4]
        println("turning to $direction pos = $position")
    }

    fun move() {
        position = when (direction) {
            Direction.N -> position.first to position.second - 1
            Direction.E -> position.first + 1 to position.second
            Direction.S -> position.first to position.second + 1
            Direction.W -> position.first - 1 to position.second
        }
        val sb = StringBuilder(input[position.second])
        sb.setCharAt(position.first, 'X')
        input[position.second] = sb.toString()
        println("moved to pos $position dir $direction")
    }

    fun canMove(): Boolean {
        return when (direction) {
            Direction.N -> input[position.second - 1][position.first] != '#'
            Direction.E -> input[position.second][position.first + 1] != '#'
            Direction.S -> input[position.second + 1][position.first] != '#'
            Direction.W -> input[position.second][position.first - 1] != '#'
        }
    }

    fun canFinish(): Boolean {
        return when (direction) {
            Direction.N -> position.second == 0
            Direction.E -> position.first + 1 == input[0].length
            Direction.S -> position.second + 1 == input.size
            Direction.W -> position.first == 0
        }
    }
}

enum class Direction {
    N, E, S, W,
}

private fun daySixPartOne(input: List<String>): Int {
    val list = input.toMutableList()
    val guard = Guard(list)

    while (!guard.canFinish()) {
        if (guard.canMove()) {
            guard.move()
        } else {
            guard.turn()
        }

    }

    return list.fold(0) { acc, element ->
        acc + element.count { it == 'X' }
    }
}