plugins {
    kotlin("jvm") version "1.9.21"
    id("com.diffplug.spotless") version "6.23.2"
}
dependencies {
    implementation("org.junit.jupiter:junit-jupiter:5.8.1")
}

sourceSets {
    main {
        kotlin.srcDir("src")
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