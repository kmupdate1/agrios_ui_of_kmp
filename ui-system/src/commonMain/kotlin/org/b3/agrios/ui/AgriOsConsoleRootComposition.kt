package org.b3.agrios.ui

import androidx.compose.runtime.getValue
import org.b3.agrios.data.demoDashboardRepository
import org.b3.agrios.ui.capability.Renderable
import org.b3.agrios.ui.dashboard.DashboardController
import org.b3.agrios.ui.dashboard.view.DashboardRootContainerView
import org.b3.agrios.ui.impl.console.AgriOsConsoleThemeContainerView

object AgriOsConsoleRootComposition {
    fun compose(): Renderable {
        val controller = DashboardController(demoDashboardRepository())
        val root = DashboardRootContainerView(controller)

        return AgriOsConsoleThemeContainerView(
            isDarkTheme = {
                val state by controller.state
                state.isDarkMode
            },
            renderable = root,
        )
    }
}
