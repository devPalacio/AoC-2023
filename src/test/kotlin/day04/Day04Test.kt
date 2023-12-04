package day04

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day04Test {
    @Test
    fun part1SampleInput() {
        val actual = part1(sampleInput)
        val expected = 13
        assertEquals(expected, actual)
    }

    @Test
    fun part2SampleInput() {
        val actual = part2(sampleInput)
        val expected = 30
        assertEquals(expected, actual)
    }

    @Test
    fun parseInputReturnsTriple() {
        val expected =
            Triple(
                1,
                setOf(41, 48, 83, 86, 17),
                setOf(83, 86, 6, 31, 17, 9, 48, 53),
            )
        val actual = sampleInput.first().parseInput()
        assertEquals(expected, actual)
    }

    @Test
    fun numOfMatchesReturnsExpectedAnswer() {
        val expected = 4
        val actual = sampleInput.first().parseInput().numOfMatches()
        assertEquals(expected, actual)
    }

    @Test
    fun numOfPointsReturnsExpectedAnswer() {
        val expected = 8
        val actual = sampleInput.first().parseInput().numOfMatches().numOfPoints()
        assertEquals(expected, actual)
    }

    private val sampleInput =
        """
        Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
        Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
        Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
        Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
        Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
        Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
        """.trimIndent().split("\n")
}
