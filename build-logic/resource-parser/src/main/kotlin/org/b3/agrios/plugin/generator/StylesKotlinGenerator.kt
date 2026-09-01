package org.b3.agrios.plugin.generator

import org.b3.agrios.plugin.res.model.StylesResource

class StylesKotlinGenerator {
    fun generate(resources: List<StylesResource>): String = buildString {
        appendLine("package org.b3.agrios.generated.resource")
        appendLine()
        appendLine("sealed interface StylesKey {")

        resources
            .groupBy { it.style }
            .forEach { (style, properties) ->
                append(INDENT)
                appendLine("data object ${style.toKotlinIdentifier()} : StylesKey {")
                properties.forEach { property ->
                    append(INDENT + INDENT)
                    appendLine("const val ${property.tag.toKotlinConstIdentifier()}: String = \"${property.value}\"")
                }
                appendLine("$INDENT}")
            }

        appendLine("}")
    }

    private companion object {
        const val INDENT = "    "
    }
}
