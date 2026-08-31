package org.b3.agrios.plugin.generator

import org.b3.agrios.plugin.res.model.ColorsResource

class ColorsKotlinGenerator {
    fun generate(resources: List<ColorsResource>): String =
        buildString {
            appendLine("object Colors {")

            resources
                .groupBy { it.type }
                .forEach { (type, colors) ->
                    appendLine("    object ${type.toKotlinIdentifier()} {")

                    colors.forEach { color ->
                        appendLine(
                            "        val ${color.name.toKotlinIdentifier()} = " +
                                    color.value.toKotlinString()
                        )
                    }

                    appendLine("    }")
                }

            appendLine("}")
        }
}
