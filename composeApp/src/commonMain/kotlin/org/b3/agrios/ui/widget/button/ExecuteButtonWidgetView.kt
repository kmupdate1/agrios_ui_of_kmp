package org.b3.agrios.ui.widget.button

import androidx.compose.ui.Modifier

abstract class ExecuteButtonWidgetView(
    modifier: Modifier = Modifier,
) : ButtonWidgetView(modifier) {
    protected abstract fun execute()

    override fun onClick() { execute() }
}
