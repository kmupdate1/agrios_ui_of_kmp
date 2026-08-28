package org.b3.agrios.ui.container

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.b3.agrios.ui.view.View

abstract class ListContainerView(
    protected val children: List<View>,
    protected val modifier: Modifier = Modifier,
) : ContainerView {
    @Deprecated("", level = DeprecationLevel.WARNING)
    @Composable
    override fun onReCompose() {
        children.forEach { child -> child.onReCompose() }
    }

    @Composable
    override fun onRender() {
        children.forEach { child -> child.onRender() }
        renderingModifier = modifier
    }

    protected var renderingModifier = modifier
}
