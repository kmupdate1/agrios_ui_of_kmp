package org.b3.agrios.plugin.res.parser.xml

import org.b3.agrios.plugin.res.parser.Parser
import java.io.File

internal class ByteFileXmlParser<T>(
    private val parser: ByteXmlParser<T>,
) : Parser<File, T> {
    override fun parse(input: File): T =
        parser.parse(input.readBytes())
}
