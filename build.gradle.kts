java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

plugins {
    id("application")
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass = "AgentMain"
}


dependencies {
    implementation("io.mantisrx:mantis-server-agent:3.2.0")
    implementation("io.mantisrx:mantis-control-plane-core:3.2.0")
    implementation("io.mantisrx:mantis-runtime:3.2.0")
    implementation("io.kubernetes:client-java:18.0.1")
    implementation("org.slf4j:slf4j-api:2.0.9")
    implementation("ch.qos.logback:logback-classic:1.4.11")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks {
    named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
        archiveClassifier.set("") // So it's named projectname-version.jar
        mergeServiceFiles()       // optional, helpful with ServiceLoader
    }

    // Optional: make shadowJar the default 'jar' task
    build {
        dependsOn(shadowJar)
    }
}

tasks.register<Exec>("buildDocker") {
    dependsOn("shadowJar")
    commandLine("docker", "build", "-t", "mantis-agent:latest", ".")
}

tasks.test {
    useJUnitPlatform()
}