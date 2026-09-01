package org.b3.agrios.plugin.res.model

data class StylesResource(
    val style: String,
    override val tag: String,
    override val value: String,
) : Resource
