package day03

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day03Test {
    @Test
    fun samplePart1ReturnsCorrectAnswer() {
        val expected = 4361
        val result = part1(sampleInput)
        assertEquals(expected, result)
    }

    @Test
    fun redditSamplePart1ReturnsCorrectAnswer() {
        val expected = 925
        val result = part1(redditSample)
        assertEquals(expected, result)
    }

    @Test
    fun samplePart2ReturnsCorrectAnswer() {
        val expected = 467835
        val result = part2(sampleInput)
        assertEquals(expected, result)
    }

    @Test
    fun neighborIsSymbolReturnsTrue() {
        val given = Pair(0, 0)
        val givenMap = sampleInput.extractDigitsAsMap()
        val expected = true
        val result = given.neighborIsSymbol(sampleInput, givenMap)
        assertEquals(expected, result)
    }

    @Test
    fun neighborIsSymbolReturnsFalse() {
        val given = Pair(5, 0)
        val givenMap = sampleInput.extractDigitsAsMap()
        val expected = false
        println(sampleInput[1])
        val result = given.neighborIsSymbol(sampleInput, givenMap)
        assertEquals(expected, result)
    }

    private val sampleInput =
        """
        467..114..
        ...*......
        ..35..633.
        ......#...
        617*......
        .....+.58.
        ..592.....
        ......755.
        ...$.*....
        .664.598..
        """.trimIndent().split("\n")

    private val redditSample =
        """
        12.......*..
        +.........34
        .......-12..
        ..78........
        ..*....60...
        78.........9
        .5.....23..$
        8...90*12...
        ............
        2.2......12.
        .*.........*
        1.1..503+.56
        """.trimIndent().split("\n")
}
