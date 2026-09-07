package org.b3.agrios.ui.impl.console

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.b3.agrios.generated.resource.StylesKey
import org.b3.agrios.ui.container.HorizontalListContainerView
import org.b3.agrios.ui.view.View

class AgriOsConsoleRootContainerView(
    sideNav: View,
    consoleContent: View,
    modifier: Modifier = Modifier,
) : HorizontalListContainerView(
    children = listOf(sideNav, consoleContent),
    modifier = modifier.fillMaxSize(),
) {
    override val stylesKey: StylesKey = StylesKey.ConsoleRoot

    @Composable
    override fun onRender() {
        renderingModifier = modifier.background(MaterialTheme.colorScheme.background)
        super.onRender()
    }
}
