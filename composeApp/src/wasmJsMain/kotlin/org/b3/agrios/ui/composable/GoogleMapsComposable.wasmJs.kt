package org.b3.agrios.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.browser.document
import org.b3.agrios.vendor.createGoogleMaps
import org.w3c.dom.HTMLDivElement

@OptIn(ExperimentalWasmJsInterop::class)
@Composable
actual fun GoogleMapsComposable() {
    LaunchedEffect(Unit) {
        val mapElement = (document.createElement("div") as HTMLDivElement)
            .apply {
                style.width = "800px"
                style.height = "560px"
            }

        document.body?.appendChild(mapElement)

        createGoogleMaps(
            element = mapElement,
            apiKey = "AIzaSyDpnCYOlPMYymMeAXU-vYRNb6tnRhkQCVE",
        )
    }
}
