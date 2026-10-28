fun main() {
    fun triangleIsPossible(input: List<Int>) =
        input.sorted()[0] + input.sorted()[1] > input.sorted()[2]

    fun part1(input: String): Boolean {
        val values = input.split("\\s+".toRegex())
            .filter { it.isNotBlank() }
            .map { it.toInt() }
        return triangleIsPossible(values)
    }

    fun part1(input: List<String>): Int {
        return input.count { part1(it) }
    }

    fun part2(input: List<String>): Int {
        val map = input.map { row -> row.split("\\s+".toRegex()).filter { it.isNotBlank() }.map { it.toInt() } }
        val transposed = map.map { it[0] } + map.map { it[1] } + map.map { it[2] }
        val triangles = transposed.chunked(3)

        return triangles.count { triangleIsPossible(it) }
    }

    check(part1("5 10 25") == false)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
