package org.b3.agrios.ui.lifecycle

import org.b3.agrios.ui.capability.Drawable

fun interface Creatable {
    fun onCreate(): Drawable
}
