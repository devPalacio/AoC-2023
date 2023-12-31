package day04

import fetchInput
import println
import kotlin.math.pow

fun main() {
    val input = fetchInput(4)
    part1(input).println()
    part2(input).println()
}

fun part1(input: List<String>): Int {
    return input
        .map(String::parseInput)
        .sumOf { it.numOfMatches().numOfPoints() }
}

fun part2(input: List<String>): Int {
    val cardTracker = IntArray(input.size) { 1 }
    input
        .map(String::parseInput)
        .forEach { card ->
            // iterate for number of cards we have
            val cardIndex = card.first - 1
            val matches = card.numOfMatches()
            for (i in 1..cardTracker[cardIndex]) {
                for (j in 1..matches) {
                    cardTracker[cardIndex + j] += 1
                }
            }
        }
    return cardTracker.sum()
}

fun Triple<Int, Set<Int>, Set<Int>>.numOfMatches(): Int = (second intersect third).size

fun Int.numOfPoints(): Int = 2f.pow(this - 1).toInt()

fun String.parseInput(): Triple<Int, Set<Int>, Set<Int>> {
    val parts = split(":", " | ")
    require(parts.size == 3) { "String failed to parse: $this" }

    val cardNum =
        parts[0]
            .substringAfter("Card ")
            .trim()
            .toInt()

    val winningNums =
        parts[1]
            .split(" ")
            .filter { it.isNotBlank() }
            .map(String::toInt)
            .toSet()

    val myNums =
        parts[2]
            .split(" ")
            .filter { it.isNotBlank() }
            .map(String::toInt)
            .toSet()
    return Triple(cardNum, winningNums, myNums)
}
