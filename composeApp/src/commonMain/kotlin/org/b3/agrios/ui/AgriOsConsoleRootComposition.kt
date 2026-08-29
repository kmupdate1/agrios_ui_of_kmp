package org.b3.agrios.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.b3.agrios.data.demoDashboardRepository
import org.b3.agrios.ui.capability.Drawable
import org.b3.agrios.ui.dashboard.DashboardController

object AgriOsConsoleRootComposition {
    fun compose(): Drawable {
        val controller = DashboardController(demoDashboardRepository())

        return object : Drawable {
            @Composable
            override fun onReCompose() = Unit

            @Composable
            override fun onRender() {
                DashboardScreen(
                    controller = controller,
                    modifier = Modifier,
                )
            }
        }
    }
}
