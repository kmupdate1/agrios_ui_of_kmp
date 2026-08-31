package org.b3.agrios.plugin.generator

import org.b3.agrios.plugin.res.model.StringsResource

class StringsKotlinGenerator {
    fun generate(resources: List<StringsResource>): String =
        buildString {
            appendLine("object Strings {")

            resources
                .groupBy { it.type }
                .forEach { (type, values) ->
                    appendLine("    object ${type.toKotlinIdentifier()} {")

                    values
                        .groupBy { it.name }
                        .forEach { (name, localizedValues) ->
                            appendLine(
                                "        val ${name.toKotlinIdentifier()}: String"
                            )
                            appendLine(
                                "            get() = when (Locale.current.language) {"
                            )

                            localizedValues
                                .filter { it.locale != null }
                                .forEach { resource ->
                                    appendLine(
                                        """                "${resource.locale}" -> ${resource.value.toKotlinString()}"""
                                    )
                                }

                            localizedValues
                                .firstOrNull { it.locale == null }
                                ?.let { resource ->
                                    appendLine(
                                        """                else -> ${resource.value.toKotlinString()}"""
                                    )
                                }

                            appendLine("            }")
                        }

                    appendLine("    }")
                }

            appendLine("}")
        }
}
