package org.b3.agrios.util.locale

@OptIn(ExperimentalWasmJsInterop::class)
private val requestedLocale: String =
    js("""new URLSearchParams(location.search).get("lang") || """"")

@OptIn(ExperimentalWasmJsInterop::class)
private val platformLanguage: String =
    js("navigator.language")

actual fun getLocale(): AgriOsLocale {
    val locale = requestedLocale
        .ifBlank { platformLanguage }

    return Locale.resolve(locale)
        ?: AgriOsLocale.EN
}
