package org.b3.agrios.util.locale

object Locale {
    val current: AgriOsLocale get() = getLocale()

    internal fun resolve(locale: String): AgriOsLocale =
        when {
            locale.startsWith("zh-CN") or locale.startsWith("zh-Hans")
                -> AgriOsLocale.ZH_CN

            locale.startsWith("zh-TW") or locale.startsWith("zh-Hant")
                -> AgriOsLocale.ZH_TW

            else -> {
                val language = locale
                    .substringBefore('-')
                    .lowercase()

                AgriOsLocale.entries
                    .firstOrNull { it.language == language }
                    ?: AgriOsLocale.EN
            }
        }
}
