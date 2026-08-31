package org.b3.agrios.plugin.res.parser.xml

import nl.adaptivity.xmlutil.ExperimentalXmlUtilApi
import nl.adaptivity.xmlutil.core.KtXmlReader
import org.b3.agrios.plugin.res.parser.Parser

@OptIn(ExperimentalXmlUtilApi::class)
abstract class BytesXmlParser : Parser<ByteArray, XmlDocument> {
    protected abstract fun parseXml(reader: KtXmlReader): XmlDocument

    final override fun parse(input: ByteArray): XmlDocument =
        KtXmlReader(input.inputStream())
            .run { parseXml(this) }
}
