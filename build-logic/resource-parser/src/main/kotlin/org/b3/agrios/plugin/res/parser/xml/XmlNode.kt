package org.b3.agrios.plugin.res.parser.xml

sealed interface XmlNode {
    data class Element(
        val name: String,
        val attributes: Map<String, String>,
        val children: List<XmlNode>,
    ) : XmlNode

    data class Value(
        val value: String,
    ) : XmlNode
}
