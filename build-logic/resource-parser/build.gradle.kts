plugins {
    `java-gradle-plugin`
    kotlin("jvm") version "2.4.10"
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    implementation("io.github.pdvrieze.xmlutil:core:1.0.1")
    implementation("io.github.pdvrieze.xmlutil:serialization:1.0.1")

    implementation(kotlin("gradle-plugin"))

    testImplementation(kotlin("test"))
}

gradlePlugin {
    plugins {
        create("agriOsResource") {
            id = "org.b3.agrios.plugin.resource"
            implementationClass =
                "org.b3.agrios.plugin.gradle.ResourcePlugin"
        }
    }
}

tasks.test {
    useJUnitPlatform()
}
