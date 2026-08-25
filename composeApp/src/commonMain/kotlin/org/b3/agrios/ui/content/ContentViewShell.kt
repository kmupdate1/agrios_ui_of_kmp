package org.b3.agrios.ui.content

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

abstract class ContentViewShell : ContentView {
    @Composable
    fun create(
        modifier: Modifier = Modifier,
        content: @Composable () -> Unit,
    ) {
        Box(modifier = modifier) { content() }
    }
}
