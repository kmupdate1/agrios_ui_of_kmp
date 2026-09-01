package org.b3.agrios.util.locale

@OptIn(ExperimentalWasmJsInterop::class)
private val platformLanguage: String = js("navigator.language")

actual fun getLocale(): AgriOsLocale =
    when (platformLanguage) {
        "ja-JP" -> AgriOsLocale.JA_JP

        "zh-CN",
        "zh-Hans-CN",
        "zh-Hans" -> AgriOsLocale.ZH_CN

        "zh-TW",
        "zh-Hant-TW",
        "zh-Hant" -> AgriOsLocale.ZH_TW

        "ko-KR" -> AgriOsLocale.KO_KR
        "it-IT" -> AgriOsLocale.IT_IT
        "fr-FR" -> AgriOsLocale.FR_FR
        "de-DE" -> AgriOsLocale.DE_DE
        "es-ES" -> AgriOsLocale.ES_ES

        else -> AgriOsLocale.EN_US
    }
