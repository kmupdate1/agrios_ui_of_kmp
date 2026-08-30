package org.b3.agrios.plugin.generator

internal fun String.toKotlinIdentifier(): String =
    replace(Regex("[^A-Za-z0-9_]"), "_")
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
