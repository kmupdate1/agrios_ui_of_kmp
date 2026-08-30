package org.b3.agrios.plugin.res.parser.xml

import nl.adaptivity.xmlutil.ExperimentalXmlUtilApi
import nl.adaptivity.xmlutil.core.KtXmlReader
import org.b3.agrios.plugin.res.parser.Parser

@OptIn(ExperimentalXmlUtilApi::class)
abstract class StringXmlParser<T> : Parser<String, T> {
    protected abstract fun parseXml(reader: KtXmlReader): T

    final override fun parse(input: String): T =
        KtXmlReader(input)
            .run { parseXml(this) }
}
