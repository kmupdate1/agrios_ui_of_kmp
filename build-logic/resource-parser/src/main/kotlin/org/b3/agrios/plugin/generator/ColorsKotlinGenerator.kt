package org.b3.agrios.plugin.generator

import org.b3.agrios.plugin.res.model.ColorsResource

class ColorsKotlinGenerator {
    fun generate(resources: List<ColorsResource>): String = buildString {
        appendLine("package org.b3.agrios.generated.resource")
        appendLine()
        appendLine("object Colors {")

        resources
            .groupBy { it.theme }
            .forEach { (theme, properties) ->
                append(INDENT)
                appendLine("object ${theme.toKotlinIdentifier()} {")

                properties.forEach { property ->
                    val value = property.value
                        .removePrefix("#")
                        .let { if (it.length == 6) "0xFF$it" else "0x$it" }

                    append(INDENT + INDENT)
                    appendLine("const val ${property.tag.toKotlinConstIdentifier()}: Long = $value")
                }
                appendLine("$INDENT}")
            }
        appendLine("}")
    }

    private companion object {
        const val INDENT = "    "
    }
}
