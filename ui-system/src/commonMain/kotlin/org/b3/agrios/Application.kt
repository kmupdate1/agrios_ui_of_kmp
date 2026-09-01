package org.b3.agrios

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import org.b3.agrios.lifecycle.Lifecycle
import org.b3.agrios.ui.AgriOsConsoleRootComposition
import org.b3.agrios.ui.capability.Renderable
import org.b3.agrios.ui.impl.console.AgriOsConsoleThemeContainerView
import org.b3.agrios.ui.lifecycle.UiLifecycle
import org.b3.agrios.ui.view.View
import org.b3.agrios.util.notifyReady

object Application : Lifecycle {
    override fun onCreate() {
        renderable = uiLifecycle.onCreate()
    }

    override fun onPrepare() { }

    override fun onLoad() { }

    override fun onStart() {
        uiLifecycle.onStart()
    }

    override fun onStop() {
        uiLifecycle.onStop()
    }

    override fun onDestroy() {
        uiLifecycle.onDestroy()
    }

    @Composable
    override fun Content() {
        onLoad()
        uiLifecycle.onPrepare()
        onStart()
    }

    private lateinit var renderable: Renderable
    private val uiLifecycle: UiLifecycle = object : UiLifecycle {
        override fun onCreate(): Renderable =
            AgriOsConsoleRootComposition.compose()

        @Composable
        override fun onPrepare() {
            val view: View = AgriOsConsoleThemeContainerView(
                isDarkTheme = isSystemInDarkTheme(),
                renderable = renderable,
            )

            view.onRender()
        }

        override fun onStart() {
            notifyReady()
        }

        override fun onStop() { }

        override fun onDestroy() { }
    }
}
