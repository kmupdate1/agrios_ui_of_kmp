package org.b3.agrios.util.locale

import platform.Foundation.NSBundle

actual fun getLocale(): AgriOsLocale {
    val localization = NSBundle.mainBundle
        .preferredLocalizations
        .firstOrNull() as? String

    return localization
        ?.let { Locale.resolve(it) }
        ?: AgriOsLocale.EN
}
