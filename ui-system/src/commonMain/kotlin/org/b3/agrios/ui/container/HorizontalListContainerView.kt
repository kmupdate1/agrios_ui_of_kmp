package org.b3.agrios.ui.container

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.b3.agrios.ui.view.View

abstract class HorizontalListContainerView(
    children: List<View>,
    modifier: Modifier = Modifier,
) : ListContainerView(
    children = children,
    modifier = modifier,
) {
    @Composable
    override fun onRender() =
        Row(modifier = super.modifier) { super.onRender() }
}
