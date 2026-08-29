package org.b3.agrios.ui.impl.console

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.b3.agrios.ui.capability.Drawable
import org.b3.agrios.ui.container.ContainerView
import org.b3.agrios.ui.theme.AgriOsShapes
import org.b3.agrios.ui.theme.ColorScheme

class AgriOsConsoleThemeContainerView(
    private val isDarkTheme: Boolean,
    private val drawable: Drawable,
) : ContainerView {
    @Composable
    override fun onReCompose() {
        drawable.onReCompose()
    }

    @Composable
    override fun onRender() {
        MaterialTheme(
            colorScheme =
                if (isDarkTheme) ColorScheme.DarkColorScheme
                else ColorScheme.LightColorScheme,
            shapes = AgriOsShapes,
            content = drawable::onRender,
        )
    }
}
