plugins {
    `java-gradle-plugin`
    kotlin("jvm")
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    implementation(libs.pdvrieze.xmlutl.core)
    implementation(libs.pdvrieze.xmlutl.serialization)

    implementation(kotlin("gradle-plugin"))

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
