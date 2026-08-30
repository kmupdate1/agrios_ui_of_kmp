package org.b3.agrios.plugin.res.model

data class ColorsResource(
    val type: String,
    override val name: String,
    override val value: String,
) : Resource
