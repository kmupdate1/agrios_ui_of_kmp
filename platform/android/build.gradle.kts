plugins {
    id("com.android.application")
}

android {
    namespace = "org.b3.agrios.android"
    compileSdk = 37

    defaultConfig {
        applicationId = "org.b3.agrios.android"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "0.1.0"
    }
}

dependencies {
    implementation(libs.androidx.activity.compose)

    implementation(project(":ui-system"))
    implementation(project(":app-runtime"))

    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(24)
}
