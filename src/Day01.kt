import kotlin.math.absoluteValue

fun main() {
    fun parseDirection(instruction: String, direction: Direction) =
        when (instruction.first()) {
            'R' -> direction.turnRight()
            'L' -> direction.turnLeft()
            else -> throw IllegalArgumentException()
        }

    fun part1(input: String): Int {
        var (x, y) = 0 to 0
        var direction = Direction.NORTH
        input.split(", ")
            // .also(::println)
            .forEach { instruction ->
                direction = parseDirection(instruction, direction)

                val distance = instruction.drop(1).toInt()
                when (direction) {
                    Direction.NORTH -> y += distance
                    Direction.SOUTH -> y -= distance
                    Direction.EAST -> x += distance
                    Direction.WEST -> x -= distance
                }
                // println("Going $distance to $direction")
                // println("Now at ($x, $y)")
            }
        return x.absoluteValue + y.absoluteValue
    }

    fun part2(input: String): Int {
        val visitedLocations = mutableSetOf<Pair<Int, Int>>()
        var (x, y) = 0 to 0
        var direction = Direction.NORTH
        input.split(", ")
            .forEach { instruction ->
                direction = parseDirection(instruction, direction)

                val distance = instruction.drop(1).toInt()
                repeat(distance) {
                    when (direction) {
                        Direction.NORTH -> y += 1
                        Direction.SOUTH -> y -= 1
                        Direction.EAST -> x += 1
                        Direction.WEST -> x -= 1
                    }
                    if (visitedLocations.contains(x to y)) {
                        return x.absoluteValue + y.absoluteValue
                    }
                    visitedLocations.add(x to y)
                }
            }
        throw IllegalStateException("Did not encounter location twice")
    }

    check(part1("R2, L3") == 5)
    check(part1("R2, R2, R2") == 2)
    check(part1("R5, L5, R5, R3") == 12)
    check(part2("R8, R4, R4, R8") == 4)

    val input = readInput("Day01").first()
    part1(input).println()
    part2(input).println()
}

private enum class Direction {
    NORTH, SOUTH, EAST, WEST;

    fun turnRight() = when (this) {
        NORTH -> EAST
        EAST -> SOUTH
        SOUTH -> WEST
        WEST -> NORTH
    }

    fun turnLeft() = when (this) {
        NORTH -> WEST
        EAST -> NORTH
        SOUTH -> EAST
        WEST -> SOUTH
    }
}
