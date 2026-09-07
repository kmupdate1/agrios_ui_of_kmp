package org.b3.agrios.plugin.writer

import java.io.File

class KtFileWriter(
    private val outputDirectory: File,
) {
    fun write(
        fileName: String,
        source: String,
    ): File {
        val file = outputDirectory.resolve("$fileName.kt")
        file.parentFile.mkdirs()
        file.writeText(source)
        return file
    }
}
