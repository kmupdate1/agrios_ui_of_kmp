package org.b3.agrios.plugin.res

import org.b3.agrios.plugin.res.model.Resources

interface ResourceParser<T : Resources> {
    fun parse(document: XmlDocument): T
}
