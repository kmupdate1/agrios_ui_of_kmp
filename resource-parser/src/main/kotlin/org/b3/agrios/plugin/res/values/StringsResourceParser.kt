package org.b3.agrios.plugin.res.values

import org.b3.agrios.plugin.res.domain.ResourceParser
import org.b3.agrios.plugin.res.model.StringsResource
import org.b3.agrios.plugin.res.model.xml.XmlDocument
import org.b3.agrios.plugin.res.parser.xml.FileXmlParser
import java.io.File

class StringsResourceParser(
    private val parser: FileXmlParser<XmlDocument>,
) : ResourceParser<File, StringsResource> {
    override fun parse(input: File): StringsResource {
        val document = parser.parse(input)
        /* なんらかの方法で、XmlDocumentをStringResourceに変換する感じ？ */
        TODO()
    }
}
