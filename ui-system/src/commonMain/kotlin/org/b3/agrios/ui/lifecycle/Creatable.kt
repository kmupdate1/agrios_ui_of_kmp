package org.b3.agrios.ui.lifecycle

import org.b3.agrios.ui.capability.Renderable

fun interface Creatable {
    fun onCreate(): Renderable
}
