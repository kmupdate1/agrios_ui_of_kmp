package org.b3.agrios.plugin.res.parser.xml

internal fun XmlNode.requireElement(name: String): XmlNode.Element {
    val element = this as? XmlNode.Element
        ?: error("Expected <$name />, but found $this")

    require(element.name == name) {
        "Expected <$name />, but found <${element.name} />"
    }

    return element
}

internal fun XmlNode.Element.requireAttribute(name: String): String =
    attributes[name] ?: error("Expected <$this /> to have attribute '$name'")

internal fun XmlNode.Element.text(): String =
    children
        .filterIsInstance<XmlNode.Value>()
        .joinToString("") { it.value }
