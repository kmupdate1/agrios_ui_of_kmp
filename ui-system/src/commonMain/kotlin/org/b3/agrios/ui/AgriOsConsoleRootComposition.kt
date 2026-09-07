package org.b3.agrios.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.b3.agrios.data.demoDashboardRepository
import org.b3.agrios.ui.capability.Renderable
import org.b3.agrios.ui.dashboard.DashboardController

object AgriOsConsoleRootComposition {
    fun compose(): Renderable {
        val controller = DashboardController(demoDashboardRepository())

        return object : Renderable {
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
