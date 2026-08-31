package org.b3.agrios.ui.impl.sidenav.sidenav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.b3.agrios.ui.capability.Reorderable
import org.b3.agrios.ui.container.VerticalListContainerView
import org.b3.agrios.ui.content.ContentView
import org.b3.agrios.ui.impl.sidenav.column.ColumnContainerView
import org.b3.agrios.ui.impl.sidenav.item.ItemGroupContainerView

class SideNavContainerView(
    private val title: ContentView,
    private val itemGroup: ItemGroupContainerView,
    private val column: ColumnContainerView,
    modifier: Modifier = Modifier,
) : Reorderable, VerticalListContainerView(
    children = listOf(title, itemGroup, column),
    modifier = modifier,
) {
    override fun onReorder() {  }

    @Composable
    override fun onRender() {
        renderingModifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 8.dp, vertical = 16.dp)
        super.onRender()
    }
}
