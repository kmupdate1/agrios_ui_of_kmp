package org.b3.agrios.plugin.res.parser.xml

internal fun XmlNode.requireElement(name: String): XmlNode.XmlElement {
    val element = this as? XmlNode.XmlElement
        ?: error("Expected <$name />, but found $this")

    require(element.name == name) {
        "Expected <$name />, but found <${element.name} />"
    }

    return element
}

internal fun XmlNode.XmlElement.requireAttribute(name: String): String =
    attributes[name] ?: error("Expected <$this /> to have attribute '$name'")

internal fun XmlNode.XmlElement.text(): String =
    children
        .filterIsInstance<XmlNode.XmlText>()
        .joinToString("") { it.value }
