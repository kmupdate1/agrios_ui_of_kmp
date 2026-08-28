package org.b3.agrios.ui.capability

import androidx.compose.runtime.Composable

/**
 * Provides an additional composable phase between composition
 * and rendering.
 * will be duplicated.
 */
interface ReComposable {
    @Deprecated(message = "", level = DeprecationLevel.WARNING)
    @Composable fun onReCompose()
}
