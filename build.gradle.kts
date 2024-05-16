plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "org.example"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    val jenaVersion = "5.0.0"
    implementation("org.apache.jena:jena-core:${jenaVersion}")
    implementation("org.apache.jena:apache-jena-libs:${jenaVersion}")

    // OWL API
    implementation("net.sourceforge.owlapi:owlapi-distribution:5.5.0")

    // Hermit Reasoner
    implementation("net.sourceforge.owlapi:org.semanticweb.hermit:1.4.5.519")

    val log4jVersion = "2.23.1"
    implementation("org.apache.logging.log4j:log4j-core:${log4jVersion}")
    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:${log4jVersion}")
    implementation("org.apache.logging.log4j:log4j-api:${log4jVersion}")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc> {
    options.encoding = "UTF-8"
}

tasks.withType<ProcessResources> {
    filteringCharset = "UTF-8"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))

    }
}