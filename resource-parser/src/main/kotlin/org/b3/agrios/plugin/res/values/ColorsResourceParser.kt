package org.b3.agrios.plugin.res.values

import org.b3.agrios.plugin.res.domain.ResourceParser
import org.b3.agrios.plugin.res.model.ColorsResource
import org.b3.agrios.plugin.res.parser.xml.BytesFileXmlParser
import org.b3.agrios.plugin.res.parser.xml.requireAttribute
import org.b3.agrios.plugin.res.parser.xml.requireElement
import org.b3.agrios.plugin.res.parser.xml.text
import java.io.File

class ColorsResourceParser(
    private val parser: BytesFileXmlParser,
) : ResourceParser<File, List<ColorsResource>> {
    override fun parse(input: File): List<ColorsResource> {
        val xml = parser.parse(input = input)

        val resource = xml.root.requireElement("resources")

        return resource.children
            .map { it.requireElement("type") }
            .flatMap { type ->
                type.children
                    .map { it.requireElement("color") }
                    .map { color ->
                        ColorsResource(
                            type = type.requireAttribute("name"),
                            name = color.requireAttribute("name"),
                            value = color.text(),
                        )
                    }
            }
    }
}
