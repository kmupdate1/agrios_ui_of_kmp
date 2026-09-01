package org.b3.agrios.ui.container

import androidx.compose.foundation.layout.ExperimentalGridApi
import androidx.compose.foundation.layout.Grid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.b3.agrios.ui.view.View

abstract class GridContainerView(
    children: List<View>,
    gridSize: Int,
    modifier: Modifier = Modifier,
) : ListContainerView(
    children = children,
    modifier = modifier,
) {
    @OptIn(ExperimentalGridApi::class)
    @Composable
    override fun onRender() {
        Grid(
            modifier = modifier,
            config = {  },
        ) { super.onRender() }
    }
}
