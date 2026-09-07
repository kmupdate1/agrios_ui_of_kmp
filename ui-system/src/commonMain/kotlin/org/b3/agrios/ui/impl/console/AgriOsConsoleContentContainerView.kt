package org.b3.agrios.ui.impl.console

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import org.b3.agrios.generated.resource.StylesKey
import org.b3.agrios.ui.container.VerticalListContainerView
import org.b3.agrios.ui.view.View

class AgriOsConsoleContentContainerView(
    header: View,
    content: View,
    modifier: Modifier = Modifier,
) : VerticalListContainerView(
    children = listOf(header, content),
    modifier = modifier.fillMaxSize(),
) {
    override val stylesKey: StylesKey = StylesKey.ConsoleContent
}
