package org.b3.agrios.ui.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.b3.agrios.generated.resource.StylesKey
import org.b3.agrios.ui.view.View

class ZoomWidgetView(
    private val onZoomIn: () -> Unit,
    private val onZoomOut: () -> Unit,
    private val onReset: () -> Unit,
) : View {
    override val stylesKey: StylesKey
        get() = TODO("Not yet implemented")

    @Composable
    override fun onRender() {
        Column {
            Button(onClick = onZoomIn) { Text("+") }
            Button(onClick = onZoomOut) { Text("-") }
            Button(onClick = onReset) { Text("*") }
        }
    }
}
