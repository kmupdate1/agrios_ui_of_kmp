package org.b3.agrios.lifecycle

import androidx.compose.runtime.Composable

interface Lifecycle :
    Creatable,
    Preparable,
    Startable,
    Stoppable,
    Destroyable {
    @Composable fun startContent()

    fun run(block: Lifecycle.() -> Unit) {
        onCreate()
        onPrepare()
        block()
        onStart()
    }
}
