import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    kotlin("jvm") version "1.9.21"
    id("com.diffplug.spotless") version "6.23.2"
}
dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        showStandardStreams = true
        events = setOf(
            TestLogEvent.PASSED,
            TestLogEvent.FAILED,
            TestLogEvent.STANDARD_OUT
        )
    }
}

tasks {
    wrapper {
        gradleVersion = "8.5"
    }
}

configure<com.diffplug.gradle.spotless.SpotlessExtension> {
    kotlin {
        ktfmt()    // has its own section below
        ktlint()   // has its own section below
    }
}