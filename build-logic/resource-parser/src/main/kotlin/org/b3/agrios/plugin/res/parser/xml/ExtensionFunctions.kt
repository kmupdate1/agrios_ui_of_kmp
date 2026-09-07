package org.b3.agrios.plugin.res.parser.xml

internal fun XmlNode.requireElement(name: String): XmlNode.Element {
    val element = this as? XmlNode.Element
        ?: error("Expected <$name />, but found $this")

    require(element.tag == name) {
        "Expected <$name />, but found <${element.tag} />"
    }

    return element
}

internal fun XmlNode.Element.requireAttribute(name: String): String =
    attributes[name] ?: error("Expected <$this /> to have attribute '$name'")

internal val XmlNode.Element.text: String get() =
    children
        .filterIsInstance<XmlNode.Value>()
        .joinToString("") { it.value }
