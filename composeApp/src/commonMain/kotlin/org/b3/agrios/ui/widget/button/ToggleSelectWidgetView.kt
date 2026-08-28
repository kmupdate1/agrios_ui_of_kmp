package org.b3.agrios.ui.widget.button

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.b3.agrios.ui.view.View

class ToggleSelectWidgetView<T>(
    private val values: List<T>,
    private val selected: T?,
    private val label: (T) -> String = { it.toString() },
    private val onSelected: (T) -> Unit
) : View {
    @Composable
    override fun onReCompose() { }

    @Composable
    override fun onRender() {
        Row {
            values.forEach { item ->
                FilterChip(
                    selected = item == selected,
                    onClick = { onSelected(item) },
                    label = { Text(label(item)) },
                )
            }
        }
    }
}
