package org.b3.agrios

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import org.b3.agrios.lifecycle.Lifecycle
import org.b3.agrios.ui.AgriOsConsoleRootComposition
import org.b3.agrios.ui.capability.Drawable
import org.b3.agrios.ui.impl.console.AgriOsConsoleThemeContainerView
import org.b3.agrios.ui.lifecycle.UiLifecycle
import org.b3.agrios.ui.view.View

object Application : Lifecycle {
    override fun onCreate() {
        drawable = uiLifecycle.onCreate()
    }

    override fun onPrepare() { }

    override fun onLoad() { }

    override fun onStart() { }

    override fun onStop() {
        uiLifecycle.onStop()
    }

    override fun onDestroy() {
        uiLifecycle.onDestroy()
    }

    @Composable
    override fun Content() {
        onLoad()

        uiLifecycle.onPrepare(drawable)
        uiLifecycle.onStart()

        onStart()
    }

    private lateinit var drawable: Drawable
    private val uiLifecycle: UiLifecycle = object : UiLifecycle {
        override fun onCreate(): Drawable =
            AgriOsConsoleRootComposition.compose()

        @Composable
        override fun onPrepare(drawable: Drawable) {
            view = AgriOsConsoleThemeContainerView(
                isDarkTheme = isSystemInDarkTheme(),
                drawable = drawable,
            )

            view.onReCompose()
        }

        @Composable
        override fun onStart() {
            view.onRender()
        }

        override fun onStop() { }

        override fun onDestroy() { }

        private lateinit var view: View
    }
}
