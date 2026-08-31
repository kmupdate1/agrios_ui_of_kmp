pluginManagement {
    includeBuild("build-logic/resource-parser")

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
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
)
