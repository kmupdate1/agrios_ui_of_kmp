package org.b3.agrios.plugin.res.parser.xml

import org.b3.agrios.plugin.res.parser.Parser
import java.io.File

class StringFileXmlParser<T>(
    private val parser: StringXmlParser,
) : Parser<File, XmlDocument> {
    override fun parse(input: File): XmlDocument =
        parser.parse(input = input.readText())
}
