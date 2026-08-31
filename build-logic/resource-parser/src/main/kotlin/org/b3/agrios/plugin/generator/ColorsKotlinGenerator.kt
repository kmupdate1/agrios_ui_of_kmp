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
                        val value = color.value
                            .removePrefix("#")
                            .let {
                                if (it.length == 6) {
                                    "0xFF$it"
                                } else {
                                    "0x$it"
                                }
                            }

                        appendLine(
                            "        const val ${color.name.toKotlinConstName()}: Long = $value",
                        )
                    }

                    appendLine("    }")
                }

            appendLine("}")
        }
}
