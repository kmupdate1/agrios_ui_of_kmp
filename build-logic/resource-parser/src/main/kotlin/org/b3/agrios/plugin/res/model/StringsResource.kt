package org.b3.agrios.plugin.res.model

data class StringsResource(
    val locale: String?,
    val path: List<String>,
    override val tag: String,
    override val value: String,
) : Resource
