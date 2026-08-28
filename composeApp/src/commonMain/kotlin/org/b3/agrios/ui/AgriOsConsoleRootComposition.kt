package org.b3.agrios.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.b3.agrios.ui.capability.Drawable

object AgriOsConsoleRootComposition {
    fun compose(): Drawable = object : Drawable {
        @Composable
        override fun onReCompose() = Unit

        @Composable
        override fun onRender() {
            DashboardScreen(modifier = Modifier)
        }
    }
}
