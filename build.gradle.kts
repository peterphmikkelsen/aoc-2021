plugins {
    kotlin("jvm") version "1.6.0"
}

repositories {
    mavenCentral()
}

tasks {
    sourceSets {
        main {
            java.srcDirs("src")
        }
    }

    wrapper {
        gradleVersion = "7.3"
    }
}

dependencies {
    implementation("org.jetbrains.lets-plot:lets-plot-common:2.2.1")
    implementation("org.jetbrains.lets-plot:lets-plot-kotlin-jvm:3.1.0")
}
