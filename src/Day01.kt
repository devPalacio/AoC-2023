import java.lang.Character.isDigit

fun main() {
    val input = fetchInput(1)
    part1(input).println()
    part2(input).println()
}

fun part1(input: List<String>): Int {
    return input.sumOf {
        "${getFirstDigit(it)}${getLastDigit(it)}".toInt()
    }
}

fun part2(input: List<String>): Int {
    return input.map(::transformToDigits).sumOf {
        "${getFirstDigit(it)}${getLastDigit(it)}".toInt()
    }
}

fun getFirstDigit(input: String): Int {
    return input.first(::isDigit).digitToInt()
}

fun getLastDigit(input: String): Int {
    return input.last(::isDigit).digitToInt()
}

fun transformToDigits(input: String): String {
    var temp = input
    for ((key, _) in digitMap) {
        val regex = key.toRegex()
        temp =
            regex.replace(temp) { matchResult ->
                // This is hideous, but it works to preserve shared chars ¯\_(ツ)_/¯
                "${matchResult.value}${digitMap[matchResult.value]}${matchResult.value}"
            }
    }
    temp = temp.filter(::isDigit)
    return temp
}

val digitMap =
    mapOf(
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9",
    )
