package org.b3.agrios.plugin.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class ResourcePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val generatedResource = target.tasks.register(
            "generateResources",
            GenerateResourceTask::class.java,
        ) { task ->
            task.resDirectory.set(
                target.layout.projectDirectory.dir(GenerateResourceTask.DEFAULT_RESOURCE_DIR)
            )
            task.outputDirectory.set(
                target.layout.buildDirectory.dir(GenerateResourceTask.DEFAULT_OUTPUT_DIR)
            )
        }

        target.plugins.withId("org.jetbrains.kotlin.multiplatform") {
            target.extensions
                .getByType(KotlinMultiplatformExtension::class.java)
                .sourceSets
                .getByName("commonMain")
                .kotlin
                .srcDir(
                    generatedResource.flatMap {
                        it.outputDirectory
                    }
                )
        }
    }
}
