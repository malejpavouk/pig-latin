plugins {
    id("java")
    id("checkstyle")
    id("pmd")
    id("jacoco")
}

apply(from = "build-quality.gradle.kts")

val junitVersion = "5.6.1"
dependencies {
    implementation("commons-io:commons-io:2.6")
    implementation("ch.qos.logback:logback-classic:1.2.3")

    testImplementation("org.assertj:assertj-core:3.15.0")
    testImplementation("org.mockito:mockito-core:3.3.3")

    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
}

repositories {
    mavenCentral()
}
