package org.b3.agrios.util.locale

import androidx.compose.ui.text.intl.Locale as AndroidxLocale

actual fun getLocale(): AgriOsLocale {
    val locale = AndroidxLocale.current

    val localization = "${locale.language}-${locale.region}"

    return Locale.resolve(localization)
        ?: AgriOsLocale.EN
}
