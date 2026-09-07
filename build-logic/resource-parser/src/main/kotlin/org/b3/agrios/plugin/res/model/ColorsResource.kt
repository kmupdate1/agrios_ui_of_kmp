package org.b3.agrios.plugin.res.model

data class ColorsResource(
    val theme: String,
    override val tag: String,
    override val value: String,
) : Resource
