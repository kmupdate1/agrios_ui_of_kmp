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

    return when (locale) {
        "zh-CN", "zh-Hans", "zh-Hans-CN",
            -> AgriOsLocale.ZH_CN

        "zh-TW", "zh-Hant", "zh-Hant-TW",
            -> AgriOsLocale.ZH_TW

        else -> AgriOsLocale.entries
            .firstOrNull { it.language == locale }
            ?: AgriOsLocale.EN_US
    }
}
