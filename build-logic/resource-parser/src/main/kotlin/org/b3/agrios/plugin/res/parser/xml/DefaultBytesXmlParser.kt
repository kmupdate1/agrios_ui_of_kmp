package org.b3.agrios.plugin.res.parser.xml

import nl.adaptivity.xmlutil.EventType
import nl.adaptivity.xmlutil.ExperimentalXmlUtilApi
import nl.adaptivity.xmlutil.core.KtXmlReader

@OptIn(ExperimentalXmlUtilApi::class)
class DefaultBytesXmlParser : BytesXmlParser() {
    override fun parseXml(reader: KtXmlReader): XmlDocument {
        var root: XmlNode.Element? = null
        val stack = ArrayDeque<XmlNode.Element>()

        while (reader.hasNext()) {
            when (reader.next()) {
                EventType.START_ELEMENT -> {
                    val element = XmlNode.Element(
                        tag = reader.name.localPart,
                        attributes = buildMap {
                            repeat(reader.attributeCount) { index ->
                                put(
                                    reader.getAttributeName(index).localPart,
                                    reader.getAttributeValue(index),
                                )
                            }
                        },
                        children = mutableListOf(),
                    )

                    if (stack.isEmpty()) {
                        root = element
                    } else {
                        (stack.last().children as MutableList).add(element)
                    }

                    stack.addLast(element)
                }

                EventType.TEXT -> {
                    val text = XmlNode.Value(reader.text)

                    stack.lastOrNull()
                        ?.let { (it.children as MutableList).add(text) }
                }

                EventType.END_ELEMENT -> {
                    stack.removeLast()
                }

                else -> Unit
            }
        }

        return XmlDocument(
            root = root ?: error("XML document has no root element"),
        )
    }
}
