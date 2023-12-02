package day02

import fetchInput
import println

fun main() {
    val input = fetchInput(2)
    part1(input).println()
    part2(input).println()
}

fun part1(input: List<String>): Int {
    val maxPossible = Game(0, 12, 13, 14)
    return input.parseInput().map { round ->
        if (isPossible(maxPossible, round)) {
            round[0].id
        } else {
            0
        }
    }.sumOf { it }
}

fun part2(input: List<String>): Int {
    return input.parseInput().map { round ->
        val minimumColors = round.max()
        minimumColors.red * minimumColors.blue * minimumColors.green
    }.sumOf { it }
}

fun List<String>.parseInput(): List<List<Game>> {
    return this.map { round ->
        val id = Regex("Game (\\d+):").find(round)!!.groupValues[1].toInt()
        round.split(GAME_SEPARATOR)
            .map { game ->
                val colormap = getColormap(game)
                Game(
                    id = id,
                    red = colormap["red"] ?: 0,
                    green = colormap["green"] ?: 0,
                    blue = colormap["blue"] ?: 0,
                )
            }
    }
}

private fun getColormap(game: String) =
    DIGIT_WORD_REGEX.findAll(game)
        .map { matchResult ->
            val (value, color) = matchResult.destructured
            color to value.toInt()
        }.toMap()

fun List<Game>.max(): Game {
    return fold(first()) { acc, game ->
        Game(
            game.id,
            red = maxOf(acc.red, game.red),
            green = maxOf(acc.green, game.green),
            blue = maxOf(acc.blue, game.blue),
        )
    }
}

fun isPossible(
    max: Game,
    round: List<Game>,
): Boolean {
    val biggestGame = round.max()
    return (biggestGame.red <= max.red && biggestGame.green <= max.green && biggestGame.blue <= max.blue)
}

data class Game(val id: Int, val red: Int, val green: Int, val blue: Int)

val DIGIT_WORD_REGEX = Regex("(\\d+)\\s*(\\w+)")
const val GAME_SEPARATOR = ';'
