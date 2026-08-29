package org.b3.agrios.ui.widget

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import org.b3.agrios.ui.view.View

class PullDownWidgetView<T>(
    private val values: List<T>,
    private val label: (T) -> String,
    private val onSelect: (T) -> Unit,
) : View {
    @Composable
    override fun onReCompose() { }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun onRender() {
        var expanded by remember { mutableStateOf(false) }
        var selected by remember { mutableStateOf<T?>(null) }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
        ) {
            OutlinedTextField(
                value = selected?.let(label) ?: "Select",
                onValueChange = {},
                readOnly = true,
                label = { Text("Select") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded,
                    )
                },
                modifier = Modifier,
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                values.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = label(item)) },
                        onClick = {
                            expanded = false; selected = item
                            onSelect(item)
                        },
                    )
                }
            }
        }
    }
}
