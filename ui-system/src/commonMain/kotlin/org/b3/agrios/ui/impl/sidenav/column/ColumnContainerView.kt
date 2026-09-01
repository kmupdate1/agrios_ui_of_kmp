package org.b3.agrios.ui.impl.sidenav.column

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.b3.agrios.generated.resource.StylesKey
import org.b3.agrios.ui.container.VerticalListContainerView
import org.b3.agrios.ui.content.ContentView
import org.b3.agrios.ui.widget.button.ExecuteButtonWidgetView

class ColumnContainerView(
    private val title: ContentView,
    private val contentGroup: ColumnContentGroupContainerView,
    private val button: ExecuteButtonWidgetView,
    modifier: Modifier = Modifier,
) : VerticalListContainerView(
    children = listOf(title, contentGroup, button),
    modifier = modifier,
) {
    override val stylesKey: StylesKey
        get() = TODO("Not yet implemented")

    @Composable
    override fun onRender() {
        renderingModifier = modifier
            .padding(horizontal = 3.dp, vertical = 0.dp)
            .border(0.5.dp, MaterialTheme.colorScheme.primary)
        super.onRender()
    }
}
