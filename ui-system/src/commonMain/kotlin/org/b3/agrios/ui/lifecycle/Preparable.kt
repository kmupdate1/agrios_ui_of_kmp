package org.b3.agrios.ui.lifecycle

import androidx.compose.runtime.Composable

fun interface Preparable {
    @Composable
    fun onPrepare()
}
