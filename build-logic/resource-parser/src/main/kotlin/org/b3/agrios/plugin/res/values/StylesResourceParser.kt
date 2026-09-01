package org.b3.agrios.plugin.res.values

import org.b3.agrios.plugin.res.domain.ResourceParser
import org.b3.agrios.plugin.res.model.StylesResource
import org.b3.agrios.plugin.res.parser.xml.BytesFileXmlParser
import org.b3.agrios.plugin.res.parser.xml.XmlNode
import org.b3.agrios.plugin.res.parser.xml.requireAttribute
import org.b3.agrios.plugin.res.parser.xml.requireElement
import org.b3.agrios.plugin.res.parser.xml.text
import java.io.File

class StylesResourceParser(
    private val parser: BytesFileXmlParser,
) : ResourceParser<File, List<StylesResource>> {
    override fun parse(input: File): List<StylesResource> {
        val xml = parser.parse(input = input)

        val resource = xml.root
            .requireElement("styles")

        return resource.children
            .map { it.requireElement("style") }
            .flatMap { style ->
                style.children
                    .filterIsInstance<XmlNode.Element>()
                    .map { element ->
                        StylesResource(
                            style = style.requireAttribute("name"),
                            tag = element.tag,
                            value = element.text,
                        )
                    }
            }
    }
}
