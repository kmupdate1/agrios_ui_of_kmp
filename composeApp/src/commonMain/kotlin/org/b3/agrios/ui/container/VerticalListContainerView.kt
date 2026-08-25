package org.b3.agrios.ui.container

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.b3.agrios.ui.view.View

abstract class VerticalListContainerView(
    children: List<View>,
    modifier: Modifier = Modifier,
) : ListContainerView(
    children = children,
    modifier = modifier,
) {
    @Composable
    override fun onRender() =
        Column(modifier = super.modifier) { super.onRender() }
}
