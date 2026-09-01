package org.b3.agrios.plugin.res.values

import org.b3.agrios.plugin.res.domain.ResourceParser
import org.b3.agrios.plugin.res.model.StringsResource
import org.b3.agrios.plugin.res.parser.xml.BytesFileXmlParser
import org.b3.agrios.plugin.res.parser.xml.XmlNode
import org.b3.agrios.plugin.res.parser.xml.requireAttribute
import org.b3.agrios.plugin.res.parser.xml.requireElement
import org.b3.agrios.plugin.res.parser.xml.text
import java.io.File

class StringsResourceParser(
    private val parser: BytesFileXmlParser,
) : ResourceParser<File, List<StringsResource>> {
    override fun parse(input: File): List<StringsResource> {
        val xml = parser.parse(input = input)

        val resources = xml.root
            .requireElement("strings")

        return resources.children
            .map { it.requireElement("type") }
            .flatMap {
                parseType(
                    type = it,
                    path = emptyList(),
                    locale = input.parentFile?.locale,
                )
            }
    }

    private fun parseType(
        type: XmlNode.Element,
        path: List<String>,
        locale: String?,
    ): List<StringsResource> {
        val currentPath = path + type.requireAttribute("name")

        return type.children.flatMap { child ->
            when (child) {
                is XmlNode.Element -> when (child.tag) {
                    "string" -> listOf(
                        StringsResource(
                            locale = locale,
                            path = currentPath,
                            tag = child.requireAttribute("name"),
                            value = child.text,
                        )
                    )

                    "type" -> parseType(
                        type = child,
                        path = currentPath,
                        locale = locale,
                    )

                    else -> error("Unsupported element '${child.tag}' in <type>")
                }

                is XmlNode.Value -> emptyList()
            }
        }
    }

    private val File.locale: String?
        get() = name
            .takeIf { it != "values" }
            ?.removePrefix("values_")
}
