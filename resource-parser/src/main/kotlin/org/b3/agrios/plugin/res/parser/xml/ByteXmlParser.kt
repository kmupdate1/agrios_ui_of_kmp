package org.b3.agrios.plugin.res.parser.xml

import nl.adaptivity.xmlutil.ExperimentalXmlUtilApi
import nl.adaptivity.xmlutil.XmlUtilInternal
import nl.adaptivity.xmlutil.core.KtXmlReader
import org.b3.agrios.plugin.res.parser.Parser

@OptIn(ExperimentalXmlUtilApi::class)
internal abstract class ByteXmlParser<T> : Parser<ByteArray, T> {
    protected abstract fun parseXml(reader: KtXmlReader): T

    @OptIn(XmlUtilInternal::class)
    final override fun parse(input: ByteArray): T {
        /*
        val reader = KtXmlReader()
        return parseXml(reader)
        */
        TODO("Not yet implemented")
    }
}
