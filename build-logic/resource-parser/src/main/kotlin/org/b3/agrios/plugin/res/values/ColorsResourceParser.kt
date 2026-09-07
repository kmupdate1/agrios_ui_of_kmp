package org.b3.agrios.plugin.res.values

import org.b3.agrios.plugin.res.domain.ResourceParser
import org.b3.agrios.plugin.res.model.ColorsResource
import org.b3.agrios.plugin.res.parser.xml.BytesFileXmlParser
import org.b3.agrios.plugin.res.parser.xml.XmlNode
import org.b3.agrios.plugin.res.parser.xml.requireAttribute
import org.b3.agrios.plugin.res.parser.xml.requireElement
import org.b3.agrios.plugin.res.parser.xml.text
import java.io.File

class ColorsResourceParser(
    private val parser: BytesFileXmlParser,
) : ResourceParser<File, List<ColorsResource>> {
    override fun parse(input: File): List<ColorsResource> {
        val xml = parser.parse(input = input)

        val resource = xml.root
            .requireElement("colors")

        return resource.children
            .map { it.requireElement("theme") }
            .flatMap { theme ->
                theme.children
                    .filterIsInstance<XmlNode.Element>()
                    .map { element ->
                        ColorsResource(
                            theme = theme.requireAttribute("name"),
                            tag = element.tag,
                            value = element.text,
                        )
                    }
            }
    }
}
