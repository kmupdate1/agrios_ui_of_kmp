package org.b3.agrios.plugin.res.parser.xml

import org.b3.agrios.plugin.res.parser.Parser
import java.io.File

class FileXmlParser<T>(
    private val parser: StringXmlParser<T>,
) : Parser<File, T> {
    override fun parse(input: File): T =
        parser.parse(input = input.readText())
}
