package day03

import fetchInput
import println

typealias Coord = Pair<Int, Int>

fun main() {
    val input = fetchInput(3)
    part1(input).println()
    part2(input).println()
}

fun part1(input: List<String>): Int {
    val numberMap = input.extractDigitsAsMap()
    numberMap.keys.removeIf { !it.neighborIsSymbol(input, numberMap) }
    return numberMap.values.sum()
}

fun part2(input: List<String>): Int {
    val gearSet = input.extractGearsAsSet()
    val numberMap = input.extractDigitsAsMap()
    return gearSet.sumOf { gear -> gear.getGearRatio(numberMap, input) }
}

fun List<String>.extractDigitsAsMap(): MutableMap<Coord, Int> {
    val map = mutableMapOf<Coord, Int>()
    // should really take the time and get comfortable with flatMap
    this.withIndex().forEach { row ->
        val y = row.index
        DIGIT_REGEX.findAll(row.value).map { matchResult ->
            Pair(matchResult.range.first, y) to matchResult.value.toInt()
        }.toMap(map)
    }
    return map
}

fun List<String>.extractGearsAsSet(): MutableSet<Coord> {
    val gearSet = mutableSetOf<Coord>()
    this.withIndex().forEach { row ->
        val y = row.index
        gearSet +=
            GEAR_REGEX.findAll(row.value).map { matchResult ->
                Pair(matchResult.range.first, y)
            }.toSet()
    }
    return gearSet
}

fun Coord.getGearRatio(
    numberMap: MutableMap<Coord, Int>,
    input: List<String>,
): Int {
    val gearValues = mutableListOf<Int>()
    for (neighbor in NEIGHBORS) {
        val x = this.first + neighbor.first
        val y = this.second + neighbor.second
        // find out if it's a number
        var curr = numberMap[Pair(x, y)]
        if (curr != null) {
            gearValues += curr
            numberMap[Pair(x, y)] = 1
            continue
        }
        if (input[y][x].isDigit()) {
            // look left in map
            for (i in 0..2) {
                curr = numberMap[Pair(x - i, y)]
                if (curr != null) gearValues += curr
                numberMap[Pair(x - i, y)] = 1
            }
        }
    }

    val gearRatio = gearValues.reduce { acc, i -> acc * i }
    // so many gross hacks I'm not proud of.
    return if (gearValues.filter { it != 1 }.size == 2) gearRatio else 0
}

fun Coord.neighborIsSymbol(
    input: List<String>,
    numberMap: MutableMap<Coord, Int>,
): Boolean {
    val digitLen = numberMap[this].toString().length - 1
    for (allDigits in this.first..this.first + digitLen) {
        for (neighbor in NEIGHBORS) {
            val x = allDigits + neighbor.first
            val y = this.second + neighbor.second

            if (y in input.indices && x in input[y].indices) {
                val curr = input[y][x]
                if (!curr.isDigit() && curr != '.') {
                    return true
                }
            }
        }
    }
    return false
}

private val NEIGHBORS =
    listOf(Pair(-1, -1), Pair(0, -1), Pair(1, -1), Pair(-1, 0), Pair(1, 0), Pair(-1, 1), Pair(0, 1), Pair(1, 1))
private val DIGIT_REGEX = Regex("\\d+")
private val GEAR_REGEX = Regex("\\*")
