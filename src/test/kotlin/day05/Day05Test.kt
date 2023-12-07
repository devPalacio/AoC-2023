package day05

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day05Test {
    @Test
    fun seedReturnsCorrectSoil() {
        val expected = listOf(82L, 43L, 86L, 35L)
        val parsed = parseDay5(sampleInput)
        val seeds = parsed.seeds
        val actual = seeds.map { seedToLocation(it, parsed, sampleInput) }
        assertEquals(expected, actual)
    }

    @Test
    fun part2ReturnsLowestLocation() {
        val expected = 46L
        val parsed = parseDay5(sampleInput)
        val newseeds =
            parsed.seeds.chunked(2).map {
                it[0] until it[0] + it[1]
            }
        val locations = mutableSetOf<Long>()

        newseeds.forEach { longRange ->
            var min: Long = Long.MAX_VALUE
            for (seed in longRange) {
                min = minOf(seedToLocation(seed, parsed, sampleInput), min)
                locations.add(min)
            }
        }
        val actual = locations.min()
        assertEquals(expected, actual)
    }

    private val sampleInput =
        """
        seeds: 79 14 55 13

        seed-to-soil map:
        50 98 2
        52 50 48

        soil-to-fertilizer map:
        0 15 37
        37 52 2
        39 0 15

        fertilizer-to-water map:
        49 53 8
        0 11 42
        42 0 7
        57 7 4

        water-to-light map:
        88 18 7
        18 25 70

        light-to-temperature map:
        45 77 23
        81 45 19
        68 64 13

        temperature-to-humidity map:
        0 69 1
        1 0 69

        humidity-to-location map:
        60 56 37
        56 93 4
        """.trimIndent().split("\n")
}
