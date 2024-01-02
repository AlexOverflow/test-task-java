
plugins {
    id("java")
    id("application")
}

group = "ru.infomaximum"
version = "1.0-SNAPSHOT"

application {
    mainClass = "ru.infomaximum.Main"
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(8)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.opencsv:opencsv:5.9")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.12.4")
    implementation("de.vandermeer:asciitable:0.3.2")
}

tasks.jar {
    manifest.attributes["Main-Class"] = "ru.infomaximum.Main"
    val dependencies = configurations
            .runtimeClasspath
            .get()
            .map(::zipTree)
    from(dependencies)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}

tasks.test {
    useJUnitPlatform()
}