package org.b3.agrios.ui.impl.console

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import org.b3.agrios.generated.resource.StylesKey
import org.b3.agrios.ui.capability.Renderable
import org.b3.agrios.ui.container.ContainerView
import org.b3.agrios.ui.theme.AgriOsShapes
import org.b3.agrios.ui.theme.ColorScheme

class AgriOsConsoleThemeContainerView(
    private val isDarkTheme: Boolean,
    private val renderable: Renderable,
) : ContainerView {
    override val stylesKey: StylesKey
        get() = TODO("Not yet implemented")

    @Composable
    override fun onRender() {
        MaterialTheme(
            colorScheme =
                if (isDarkTheme) ColorScheme.DarkColorScheme
                else ColorScheme.LightColorScheme,
            shapes = AgriOsShapes,
            content = renderable::onRender,
        )
    }
}
