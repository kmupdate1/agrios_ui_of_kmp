package org.b3.agrios.ui.widget.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import org.b3.agrios.ui.content.ContentView

abstract class ButtonWidgetView(
    protected val modifier: Modifier = Modifier,
) : ContentView {
    protected open val shape: Shape
        @Composable
        get() = MaterialTheme.shapes.small

    protected open val variant: ButtonVariant
        get() = ButtonVariant.Primary

    protected abstract fun onClick()
    @Composable
    protected abstract fun onContent()

    @Composable
    override fun onRender() {
        when (variant) {
            ButtonVariant.Primary -> {
                Button(
                    modifier = modifier,
                    shape = shape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                    ),
                    onClick = ::onClick,
                ) { onContent() }
            }
            ButtonVariant.Outlined -> {
                OutlinedButton(
                    modifier = modifier,
                    shape = shape,
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colorScheme.primary,
                    ),
                    border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outline),
                    onClick = ::onClick,
                ) { onContent() }
            }
            ButtonVariant.OutlineVariant -> {
                OutlinedButton(
                    modifier = modifier,
                    shape = shape,
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colorScheme.outlineVariant,
                    ),
                    border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outlineVariant),
                    onClick = ::onClick,
                ) { onContent() }
            }
        }
    }
}
