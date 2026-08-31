package org.b3.agrios.plugin.generator

import org.b3.agrios.plugin.res.model.ColorsResource

class ColorsKotlinGenerator {
    fun generate(resources: List<ColorsResource>): String =
        buildString {
            appendLine("package org.b3.agrios.generated.resource\n")
            appendLine("object Colors {")

            resources
                .groupBy { it.type }
                .forEach { (type, colors) ->
                    appendLine("    object ${type.toKotlinIdentifier()} {")

                    colors.forEach { color ->
                        appendLine(
                            "        const val ${color.name.toKotlinConstName()} = " +
                                    color.value.toKotlinString()
                        )
                    }

                    appendLine("    }")
                }

            appendLine("}")
        }
}
