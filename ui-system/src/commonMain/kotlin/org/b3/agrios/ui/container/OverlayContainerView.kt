package org.b3.agrios.ui.container

import androidx.compose.runtime.Composable
import org.b3.agrios.ui.view.View

abstract class OverlayContainerView(
    protected val children: List<View>,
) : InteractiveContainerView {
    @Composable
    override fun onRender() {
        children.forEach { child -> child.onRender() }
    }
}
