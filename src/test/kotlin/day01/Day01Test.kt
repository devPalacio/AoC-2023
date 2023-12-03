package day01

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day01Test {
    @Test
    fun `getFirstDigit should return the first digit of a string`() {
        val input = "abc123xyz"
        val result = getFirstDigit(input)
        assertEquals(1, result, "Expected the first digit to be 1")
    }

    @Test
    fun `transformToDigit converts string representation of digits to digits`() {
        val input = "one11twotest"
        val result = transformToDigits(input)
        assertEquals("1112", result)
    }

    @Test
    fun `eighthree is 83`() {
        val input = "eighthree"
        val result = transformToDigits(input)
        assertEquals("83", result)
    }

    @Test
    fun `part two sample`() {
        val testInput =
            listOf(
                "two1nine",
                "eightwothree",
                "abcone2threexyz",
                "xtwone3four",
                "4nineeightseven2",
                "zoneight234",
                "7pqrstsixteen",
            )
        val result = part2(testInput)
        assertEquals(281, result)
    }
}
