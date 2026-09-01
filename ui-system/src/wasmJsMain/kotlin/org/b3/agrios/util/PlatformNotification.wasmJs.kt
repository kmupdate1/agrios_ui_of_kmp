package org.b3.agrios.util

@OptIn(ExperimentalWasmJsInterop::class)
actual fun notifyReady() {
    js("window.agrios?.onReady?.()")
}
