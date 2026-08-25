package org.b3.agrios.container

import androidx.compose.runtime.Composable
import org.b3.agrios.ui.capability.Attachable
import org.b3.agrios.ui.capability.Detachable
import org.b3.agrios.ui.capability.Renderable
import org.b3.agrios.ui.capability.Resizable

class WeatherContainer :
    Attachable,
    Detachable,
    Resizable,
    Renderable {

    override fun onAttach() {
        // ...
    }

    override fun onDetach() {
        // ...
    }

    override fun onResize(width: Int, height: Int) {
        // ...
    }

    @Composable
    override fun onRender() {
        // WeatherContent()
    }
}
