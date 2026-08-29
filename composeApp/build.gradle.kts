@file:OptIn(ExperimentalWasmDsl::class)
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    kotlin("multiplatform")
    kotlin("plugin.compose")
    id("org.jetbrains.compose")
    // id("com.android.kotlin.multiplatform.library")
}

kotlin {
    jvm("desktop")

    wasmJs {
        browser()
        binaries.executable()
    }

    iosArm64()
    iosSimulatorArm64()

    /*
    androidLibrary {
        namespace = "org.b3.agrios"
        compileSdk = 36
        minSdk = 21
    }
    */

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.ui)
            implementation(compose.components.resources)

            implementation(libs.kotlinx.datetime)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }

        val desktopMain by getting
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
        }

        wasmJsMain.dependencies {
            implementation(libs.kotlinx.browswer)
        }
    }
}

compose.desktop {
    application {
        mainClass = "org.b3.agrios.ui.MainKt"

        nativeDistributions {
            packageName = "agrios_ui"
            packageVersion = "0.1.0"
        }
    }
}
