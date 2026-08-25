package org.b3.agrios.feature

import androidx.compose.runtime.Composable

interface UiContent {
    val id: String
    val minWidth: Int
    val minHeight: Int
    val preferredWidth: Int
    val preferredHeight: Int
}

interface UiContentRenderer<T : UiContent> {
    @Composable
    fun Render(
        content: T,
        containerWidth: Int,
        containerHeight: Int,
    )
}
