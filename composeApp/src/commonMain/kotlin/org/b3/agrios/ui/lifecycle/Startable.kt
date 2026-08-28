package org.b3.agrios.ui.lifecycle

import androidx.compose.runtime.Composable

fun interface Startable {
    @Composable fun onStart()
}
