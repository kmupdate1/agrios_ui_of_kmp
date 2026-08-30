package org.b3.agrios.plugin.res.model

sealed interface Resource {
    val name: String
    val value: String
}
