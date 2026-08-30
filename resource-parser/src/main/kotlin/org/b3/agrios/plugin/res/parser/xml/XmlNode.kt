package org.b3.agrios.plugin.res.parser.xml

sealed interface XmlNode {
    data class XmlElement(
        val name: String,
        val attributes: Map<String, String>,
        val children: List<XmlNode>,
    ) : XmlNode

    data class XmlText(
        val value: String,
    ) : XmlNode
}
