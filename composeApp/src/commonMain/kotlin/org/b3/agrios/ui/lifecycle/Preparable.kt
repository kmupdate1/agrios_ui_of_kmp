package org.b3.agrios.ui.lifecycle

import androidx.compose.runtime.Composable
import org.b3.agrios.ui.capability.Drawable

fun interface Preparable {
    @Composable
    fun onPrepare(drawable: Drawable)
}
