package org.b3.agrios.plugin.generator

internal fun String.toKotlinIdentifier(): String =
    replace(Regex("[^A-Za-z0-9_]"), "_")
        .split('_')
        .filter { it.isNotEmpty() }
        .joinToString("") { part ->
            part.replaceFirstChar { it.uppercase() }
        }
        .let { identifier ->
            if (identifier.firstOrNull()?.isDigit() == true) {
                "_$identifier"
            } else {
                identifier
            }
        }

internal fun String.toKotlinConstName(): String =
    replace(Regex("([a-z0-9])([A-Z])"), "$1_$2")
        .replace(Regex("[^A-Za-z0-9_]"), "_")
        .replace(Regex("_+"), "_")
        .trim('_')
        .uppercase()
        .let { identifier ->
            if (identifier.firstOrNull()?.isDigit() == true) {
                "_$identifier"
            } else {
                identifier
            }
        }

internal fun String.toKotlinString(): String =
    buildString {
        append('"')

        for (char in this@toKotlinString) {
            when (char) {
                '\\' -> append("\\\\")
                '"' -> append("\\\"")
                '\n' -> append("\\n")
                '\r' -> append("\\r")
                '\t' -> append("\\t")
                else -> append(char)
            }
        }

        append('"')
    }
