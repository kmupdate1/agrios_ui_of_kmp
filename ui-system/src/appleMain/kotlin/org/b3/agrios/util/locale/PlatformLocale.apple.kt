package org.b3.agrios.util.locale

import platform.Foundation.NSLocale
import platform.Foundation.currentLocale
import platform.Foundation.languageCode

actual fun getLocale(): AgriOsLocale =
    Locale.resolve(NSLocale.currentLocale.languageCode)
