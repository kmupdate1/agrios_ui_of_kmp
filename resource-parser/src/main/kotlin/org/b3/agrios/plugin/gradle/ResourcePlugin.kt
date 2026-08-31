package org.b3.agrios.plugin.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class ResourcePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.tasks.register(
            "generateResources",
            GenerateResourceTask::class.java,
        ) { task ->
            task.resDirectory.set(
                target.layout.projectDirectory.dir(GenerateResourceTask.DEFAULT_RESOURCE_DIR)
            )
            task.outputDirectory.set(
                target.layout.projectDirectory.dir(GenerateResourceTask.DEFAULT_OUTPUT_DIR)
            )
        }
    }
}
