package org.b3.agrios.plugin.res.model

data class StringsResource(
    val locale: String?,
    val type: String,
    override val name: String,
    override val value: String,
) : Resource
