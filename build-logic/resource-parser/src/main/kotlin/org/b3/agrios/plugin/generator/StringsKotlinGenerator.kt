package org.b3.agrios.plugin.generator

import org.b3.agrios.plugin.res.model.StringsResource

class StringsKotlinGenerator {
    fun generate(resources: List<StringsResource>): String = buildString {
        appendLine("package org.b3.agrios.generated.resource")
        appendLine()
        appendLine("import org.b3.agrios.util.locale.Locale")
        appendLine()
        appendLine("object Strings {")

        generateTypes(
            resources = resources,
            path = emptyList(),
            indent = 1,
        )

        appendLine("}")
    }

    private fun StringBuilder.generateTypes(
        resources: List<StringsResource>,
        path: List<String>,
        indent: Int,
    ) {
        val currentTypes = resources
            .map { it.path }
            .filter { it.size > path.size && it.take(path.size) == path }
            .map { it[path.size] }
            .distinct()

        currentTypes.forEach { type ->
            val currentPath = path + type
            val currentIndent = INDENT.repeat(indent)

            append(currentIndent)
            appendLine("object ${type.toKotlinIdentifier()} {")

            val values = resources
                .filter { it.path == currentPath }

            values
                .groupBy { it.tag }
                .forEach { (name, localized) ->
                    generateString(
                        name = name,
                        resources = localized,
                        indent = indent + 1,
                    )
                }

            generateTypes(
                resources = resources,
                path = currentPath,
                indent = indent + 1,
            )

            appendLine("$currentIndent}")
        }
    }

    private fun StringBuilder.generateString(
        name: String,
        resources: List<StringsResource>,
        indent: Int,
    ) {
        val currentIndent = INDENT.repeat(indent)
        val valueIndent = INDENT.repeat(indent + 1)

        append(currentIndent)
        if (resources.size > 1) {
            append("val ${name.toKotlinIdentifier()}: String get() = ")
            appendLine("when (Locale.current.language) {")

            resources
                .filter { it.locale != null }
                .forEach { resource ->
                    append(valueIndent)
                    appendLine("\"${resource.locale}\" -> ${resource.value.toKotlinString()}")
                }
            resources
                .firstOrNull { it.locale == null }
                ?.let { resource ->
                    append(valueIndent)
                    appendLine("else -> ${resource.value.toKotlinString()}")
                }

            appendLine("${currentIndent}}")
        } else {
            append("const val ${name.toKotlinConstIdentifier()}: String = ")
            appendLine(resources[0].value.toKotlinString())
        }
    }

    private companion object {
        const val INDENT = "    "
    }
}
