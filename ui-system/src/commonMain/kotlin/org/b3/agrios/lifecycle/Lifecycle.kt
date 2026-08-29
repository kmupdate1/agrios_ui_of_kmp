package org.b3.agrios.lifecycle

import androidx.compose.runtime.Composable

interface Lifecycle :
    Creatable,
    Preparable,
    Loadable,
    Startable,
    Stoppable,
    Destroyable {
    @Composable fun startContent()
}
