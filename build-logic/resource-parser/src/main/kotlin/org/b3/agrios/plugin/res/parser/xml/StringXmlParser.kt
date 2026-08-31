package org.b3.agrios.plugin.res.parser.xml

import nl.adaptivity.xmlutil.ExperimentalXmlUtilApi
import nl.adaptivity.xmlutil.core.KtXmlReader
import org.b3.agrios.plugin.res.parser.Parser

@OptIn(ExperimentalXmlUtilApi::class)
abstract class StringXmlParser : Parser<String, XmlDocument> {
    protected abstract fun parseXml(reader: KtXmlReader): XmlDocument

    final override fun parse(input: String): XmlDocument =
        KtXmlReader(input)
            .run { parseXml(this) }
}
