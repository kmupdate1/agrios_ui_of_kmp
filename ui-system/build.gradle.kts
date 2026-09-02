@file:OptIn(ExperimentalWasmDsl::class)
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("plugin.compose")
    id("org.jetbrains.compose")
    id("com.android.kotlin.multiplatform.library")

    id("org.b3.agrios.plugin.resource")
}

kotlin {
    wasmJs {
        browser()
        binaries.executable()
    }

    iosArm64()
    iosSimulatorArm64()

    android {
        namespace = "org.b3.agrios.ui"
        compileSdk = 37
        minSdk = 24
    }

    targets
        .withType<KotlinNativeTarget>()
        .configureEach {
            binaries.framework {
                baseName = "AgriOSKit"
                isStatic = true
            }
        }

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

        wasmJsMain.dependencies {
            implementation(libs.kotlinx.browswer)
        }
    }
}
