package org.b3.agrios.ui.impl.console

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.b3.agrios.ui.capability.Renderable
import org.b3.agrios.ui.container.ContainerView
import org.b3.agrios.ui.theme.DarkColorScheme
import org.b3.agrios.ui.theme.LightColorScheme

class AgriOsConsoleThemeContainerView(
    private val isDarkTheme: Boolean,
    private val renderable: Renderable,
) : ContainerView {
    @Composable
    override fun onRender() {
        MaterialTheme(
            colorScheme = if (isDarkTheme) DarkColorScheme else LightColorScheme,
            content = renderable::onRender,
        )
    }
}
