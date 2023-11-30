import java.io.FileInputStream
import java.math.BigInteger
import java.net.HttpURLConnection
import java.net.URL
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.security.MessageDigest
import java.util.*
import kotlin.io.path.Path
import kotlin.io.path.readLines

private val SESSION = getSessionKey()
const val YEAR = 2023
/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readLines()

fun fetchInput(day : Int): List<String> {
    val formattedDay = String.format("%02d", day)
    val url = URL("https://adventofcode.com/$YEAR/day/$day/input")
    val connection = url.openConnection() as HttpURLConnection
    connection.setRequestProperty("Cookie", "session=$SESSION")
    connection.inputStream.use { input ->
        Files.copy(input, Paths.get("src/Day$formattedDay.txt"), StandardCopyOption.REPLACE_EXISTING)
    }
    return readInput("Day$formattedDay")
}
/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

private fun loadProperties(): Properties {
    val properties = Properties()

    FileInputStream("gradle.properties")
        .use { fileInput ->
        properties.load(fileInput)
    }
    return properties
}

private fun getSessionKey(): String? {
    val properties = loadProperties()
    return properties.getProperty("SESSION_KEY")
}