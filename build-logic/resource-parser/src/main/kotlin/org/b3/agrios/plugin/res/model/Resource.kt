package org.b3.agrios.plugin.res.model

sealed interface Resource {
    val tag: String
    val value: String
}
