package org.b3.agrios.ui.lifecycle

import androidx.compose.runtime.Composable
import org.b3.agrios.ui.capability.Renderable

fun interface Startable {
    @Composable fun onStart(renderable: Renderable)
}
