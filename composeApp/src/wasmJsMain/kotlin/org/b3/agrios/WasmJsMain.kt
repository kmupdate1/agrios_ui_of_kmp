package org.b3.agrios

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import org.b3.agrios.lifecycle.Lifecycle

object WasmJsMain : Bootable {
    @OptIn(ExperimentalComposeUiApi::class)
    override fun bootstrap() = application.run {
        ComposeViewport(
            viewportContainerId = VIEWPORT_CONTAINER_ID,
            content = ::startContent,
        )
    }

    private val application: Lifecycle = Application

    private const val VIEWPORT_CONTAINER_ID = "agrios-console-root"
}
