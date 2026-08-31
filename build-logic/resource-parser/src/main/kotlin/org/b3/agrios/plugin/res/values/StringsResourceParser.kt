package org.b3.agrios.plugin.res.values

import org.b3.agrios.plugin.res.domain.ResourceParser
import org.b3.agrios.plugin.res.model.StringsResource
import org.b3.agrios.plugin.res.parser.xml.BytesFileXmlParser
import org.b3.agrios.plugin.res.parser.xml.requireAttribute
import org.b3.agrios.plugin.res.parser.xml.requireElement
import org.b3.agrios.plugin.res.parser.xml.text
import java.io.File

class StringsResourceParser(
    private val parser: BytesFileXmlParser,
) : ResourceParser<File, List<StringsResource>> {
    override fun parse(input: File): List<StringsResource> {
        val xml = parser.parse(input = input)

        val resources = xml.root.requireElement("resources")

        return resources.children
            .map { it.requireElement("type") }
            .flatMap { type ->
                type.children
                    .map { it.requireElement("string") }
                    .map { string ->
                        StringsResource(
                            locale = input.parentFile?.locale,
                            type = type.requireAttribute("name"),
                            name = string.requireAttribute("name"),
                            value = string.text(),
                        )
                    }
            }
    }

    private val File.locale: String? get() =
        name.takeIf { it != "values" }
            ?.removePrefix("values-")
}
