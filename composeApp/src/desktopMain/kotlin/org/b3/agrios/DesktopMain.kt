package org.b3.agrios

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.b3.agrios.lifecycle.Lifecycle

object DesktopMain : Bootable {
    override fun bootstrap() = application.run {
        application {
            Window(
                onCloseRequest = ::exitApplication,
                title = WINDOW_TITLE,
                content = { startContent() },
            )
        }
    }

    private val application: Lifecycle = Application

    private const val WINDOW_TITLE = "AgriOS Console"
}
