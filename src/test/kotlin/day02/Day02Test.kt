package day02

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day02Test {
    private val expectedGame =
        listOf(
            Game(id = 1, red = 1, green = 10, blue = 5),
            Game(id = 1, red = 12, green = 5, blue = 6),
            Game(id = 1, red = 4, green = 4, blue = 10),
        )

    // Given:
    // "Game 1: 1 red, 5 blue, 10 green; 5 green, 6 blue, 12 red; 4 red, 10 blue, 4 green"
    // Return: Game(red = 1, green = 5, blue = 10)...etc
    @Test
    fun parseinputReturnsDesiredData() {
        val input = listOf("Game 1: 1 red, 5 blue, 10 green; 5 green, 6 blue, 12 red; 4 red, 10 blue, 4 green")
        val result = input.parseInput()
        val expected = listOf(expectedGame)
        assertEquals(expected, result)
    }

    @Test
    fun maxReturnsLargestOfRound() {
        val result = expectedGame.max()
        val expected = Game(id = 1, red = 12, green = 10, blue = 10)
        assertEquals(expected, result)
    }

    @Test
    fun givenGameAndMaxReturnTrueIfPossible() {
        val maxPossible = Game(0, 12, 13, 14)
        val result = isPossible(maxPossible, expectedGame)
        val expected = true
        assertEquals(expected, result)
    }

    @Test
    fun givenGameAndMaxReturnFalseIfNotPossible() {
        val maxPossible = Game(0, 11, 13, 14)
        val result = isPossible(maxPossible, expectedGame)
        val expected = false
        assertEquals(expected, result)
    }

    @Test
    fun samplePart1ReturnsCorrectAnswer() {
        val expected = 8
        val result = part1(sampleInput)
        assertEquals(expected, result)
    }

    @Test
    fun samplePart2ReturnsCorrectAnswer() {
        val expected = 2286
        val result = part2(sampleInput)
        assertEquals(expected, result)
    }

    private val sampleInput =
        """
    Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
    Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
    Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
    Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
    Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
    """.trim().split("\n")
}
