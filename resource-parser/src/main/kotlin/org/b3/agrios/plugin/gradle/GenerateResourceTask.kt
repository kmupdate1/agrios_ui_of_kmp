package org.b3.agrios.plugin.gradle

import org.b3.agrios.plugin.generator.ColorsKotlinGenerator
import org.b3.agrios.plugin.generator.StringsKotlinGenerator
import org.b3.agrios.plugin.res.model.ColorsResource
import org.b3.agrios.plugin.res.model.Resource
import org.b3.agrios.plugin.res.model.StringsResource
import org.b3.agrios.plugin.res.parser.xml.BytesFileXmlParser
import org.b3.agrios.plugin.res.parser.xml.DefaultBytesXmlParser
import org.b3.agrios.plugin.res.values.ColorsResourceParser
import org.b3.agrios.plugin.res.values.StringsResourceParser
import org.b3.agrios.plugin.writer.KtFileWriter
import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction

abstract class GenerateResourceTask : DefaultTask() {
    @get:InputDirectory
    abstract val resDirectory: DirectoryProperty

    @get:OutputDirectory
    abstract val outputDirectory: DirectoryProperty

    @TaskAction
    fun generate() {
        val res = loadRes()

        val writer = KtFileWriter(outputDirectory.get().asFile)

        val strings = res.filterIsInstance<StringsResource>()
        if (strings.isNotEmpty()) {
            writer.write(
                fileName = "Strings",
                source = StringsKotlinGenerator().generate(strings),
            )
        }

        val colors = res.filterIsInstance<ColorsResource>()
        if (colors.isNotEmpty()) {
            writer.write(
                fileName = "Colors",
                source = ColorsKotlinGenerator().generate(colors),
            )
        }
    }

    private fun loadRes(): List<Resource> =
        resDirectory.get()
            .asFileTree
            .matching { it.include("**/*.xml") }
            .files
            .flatMap { file ->
                when (file.nameWithoutExtension) {
                    "strings" -> stringsParser.parse(file)
                    "colors" -> colorsParser.parse(file)
                    else -> emptyList()
                }
            }

    private val fileParser = BytesFileXmlParser(
        parser = DefaultBytesXmlParser(),
    )
    private val stringsParser = StringsResourceParser(
        parser = fileParser,
    )
    private val colorsParser = ColorsResourceParser(
        parser = fileParser,
    )

    companion object {
        const val DEFAULT_RESOURCE_DIR = "src/main/resources/res"
        const val DEFAULT_OUTPUT_DIR = "generated/sources/agrios/kotlin"
    }
}
