plugins {
    id("java")
    id("application")
}

group = "ru.infomaximum"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.opencsv:opencsv:5.9")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.12.4")
}

sourceSets.test {
    resources.srcDirs("src/test/resources")
}

sourceSets.main {
    resources {
        srcDirs("src/main/resources", "src/main/configs")
    }
}

tasks.test {
    useJUnitPlatform()
}