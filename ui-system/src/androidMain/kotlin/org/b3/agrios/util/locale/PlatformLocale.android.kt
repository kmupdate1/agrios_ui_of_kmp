package org.b3.agrios.util.locale

import androidx.compose.ui.text.intl.Locale as AndroidxLocale

actual fun getLocale(): AgriOsLocale =
    Locale.resolve(AndroidxLocale.current.language)
        ?: AgriOsLocale.EN
