plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    implementation(libs.kotlinx.serialization.xml)
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
