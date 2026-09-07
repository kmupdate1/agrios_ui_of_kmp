pluginManagement {
    includeBuild("build-logic/resource-parser")

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "agrios_console"

include(
	":app-runtime",
	":application",
	":ui-system",
	":http-client",

	":platform:android",
)
