package org.b3.agrios.ui.content

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.b3.agrios.ui.composable.FieldMapComposable

class FieldMapContentView : ContentViewShell() {
    @Composable
    override fun onRender() = super.create(modifier = Modifier, content = {
        FieldMapComposable()
    })

    override val context = ContentViewContext(
        id = "field_map",
        name = "Field Map",
    )
}
