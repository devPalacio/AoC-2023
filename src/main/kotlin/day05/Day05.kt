package day05

import fetchInput
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import println

fun main() {
    val input = fetchInput(5)
    part1(input).println()
    part2(input).println()
}

fun part1(input: List<String>): Long {
    val parsed = parseDay5(input)
    return parsed.seeds.minOf {
        seedToLocation(it, parsed, input)
    }
}

fun part2(input: List<String>): Long =
    runBlocking {
        val parsed = parseDay5(input)
        val newSeeds =
            parsed.seeds
                .chunked(2)
                .map { (seed, range) ->
                    seed until seed + range
                }

        val locations = mutableSetOf<Long>()

        newSeeds.map { longRange ->
            async {
                var min: Long = Long.MAX_VALUE
                for (seed in longRange) {
                    min = minOf(seedToLocation(seed, parsed, input), min)
                }
                min
            }
        }.awaitAll().forEach {
            locations.add(it)
        }
        locations.min()
    }

data class Day05Game(
    val seeds: List<Long>,
    val sToSMap: String,
    val sToFMap: String,
    val fToWMap: String,
    val wToLMap: String,
    val lToTMap: String,
    val tToHMap: String,
    val hToLMap: String,
)

fun parseDay5(input: List<String>): Day05Game {
    // a regex is probably way simpler.
    val seeds =
        input
            .first()
            .split(": ")
            .drop(1)
            .first()
            .split(" ")
            .map(String::toLong)

    return Day05Game(
        seeds = seeds,
        sToSMap = "seed-to-soil map:",
        sToFMap = "soil-to-fertilizer map:",
        fToWMap = "fertilizer-to-water map:",
        wToLMap = "water-to-light map:",
        lToTMap = "light-to-temperature map:",
        tToHMap = "temperature-to-humidity map:",
        hToLMap = "humidity-to-location map:",
    )
}

private fun convertToDestination(
    seed: Long,
    mapName: String,
    input: List<String>,
): Long {
    val startIndex = input.indexOf(mapName) + 1

    for (i in startIndex until input.size) {
        val line = input[i]
        if (line.isBlank()) break

        val parsed = line.split(" ").map(String::toLong)

        val (destination, source, range) = parsed
        if (seed in source until source + range) {
            val offset = destination - source
            return seed + offset
        }
    }
    return seed
}

fun seedToLocation(
    seed: Long,
    game: Day05Game,
    input: List<String>,
): Long {
    var key = seed
    val transformations =
        listOf(game.sToSMap, game.sToFMap, game.fToWMap, game.wToLMap, game.lToTMap, game.tToHMap, game.hToLMap)
    for (transform in transformations) {
        val value = convertToDestination(key, transform, input)
        key = value
    }
    return key
}
