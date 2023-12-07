package day06

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day06KtTest {
    @Test
    fun parseRacesReturnsCorrectResult() {
        val expected = listOf(Pair(7, 9), Pair(15, 40), Pair(30, 200))
        val actual = parseDay6(sampleInput)
        assertEquals(expected, actual)
    }

    @Test
    fun parseRacesReturnsCorrectResultP2() {
        val expected = Pair(71530L, 940200L)
        val actual = parseDay6P2(sampleInput)
        assertEquals(expected, actual)
    }

    @Test
    fun findBestSpeedReturnsCorrectResult() {
        val expected = listOf<Long>(2, 3, 4, 5)
        val actual = findBestSpeeds(parseDay6(sampleInput).first())
        assertEquals(expected, actual)
    }

    @Test
    fun part2ReturnsCorrectResult()  {
        val expected = 71503
        val actual = part2(sampleInput)
        assertEquals(expected, actual)
    }

    private val sampleInput =
        """
        Time:      7  15   30
        Distance:  9  40  200
        """.trimIndent().split("\n")
}
