package org.b3.agrios.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import org.b3.agrios.generated.resource.Colors

internal object ColorScheme {
    val LightColorScheme = lightColorScheme(
        primary = Color(Colors.Light.PRIMARY),
        onPrimary = Color(Colors.Light.ON_PRIMARY),
        primaryContainer = Color(Colors.Light.PRIMARY_CONTAINER),
        onPrimaryContainer = Color(Colors.Light.ON_PRIMARY_CONTAINER),

        secondary = Color(Colors.Light.SECONDARY),
        onSecondary = Color(Colors.Light.ON_SECONDARY),
        secondaryContainer = Color(Colors.Light.SECONDARY_CONTAINER),
        onSecondaryContainer = Color(Colors.Light.ON_SECONDARY_CONTAINER),

        tertiary = Color(Colors.Light.TERTIARY),
        onTertiary = Color(Colors.Light.ON_TERTIARY),
        tertiaryContainer = Color(Colors.Light.TERTIARY_CONTAINER),
        onTertiaryContainer = Color(Colors.Light.ON_TERTIARY_CONTAINER),

        background = Color(Colors.Light.BACKGROUND),
        onBackground = Color(Colors.Light.ON_BACKGROUND),

        surface = Color(Colors.Light.SURFACE),
        onSurface = Color(Colors.Light.ON_SURFACE),
        surfaceVariant = Color(Colors.Light.SURFACE_VARIANT),
        onSurfaceVariant = Color(Colors.Light.ON_SURFACE_VARIANT),

        outline = Color(Colors.Light.OUTLINE),
        outlineVariant = Color(Colors.Light.OUTLINE_VARIANT),

        error = Color(Colors.Light.ERROR),
        onError = Color(Colors.Light.ON_ERROR),
        errorContainer = Color(Colors.Light.ERROR_CONTAINER),
        onErrorContainer = Color(Colors.Light.ON_ERROR_CONTAINER),

        inverseSurface = Color(Colors.Light.INVERSE_SURFACE),
        inverseOnSurface = Color(Colors.Light.INVERSE_ON_SURFACE),
        inversePrimary = Color(Colors.Light.INVERSE_PRIMARY),

        scrim = Color(Colors.Light.SCRIM),
        surfaceTint = Color(Colors.Light.SURFACE_TINT),
    )

    val DarkColorScheme = darkColorScheme(
        primary = Color(Colors.Dark.PRIMARY),
        onPrimary = Color(Colors.Dark.ON_PRIMARY),
        primaryContainer = Color(Colors.Dark.PRIMARY_CONTAINER),
        onPrimaryContainer = Color(Colors.Dark.ON_PRIMARY_CONTAINER),

        secondary = Color(Colors.Dark.SECONDARY),
        onSecondary = Color(Colors.Dark.ON_SECONDARY),
        secondaryContainer = Color(Colors.Dark.SECONDARY_CONTAINER),
        onSecondaryContainer = Color(Colors.Dark.ON_SECONDARY_CONTAINER),

        tertiary = Color(Colors.Dark.TERTIARY),
        onTertiary = Color(Colors.Dark.ON_TERTIARY),
        tertiaryContainer = Color(Colors.Dark.TERTIARY_CONTAINER),
        onTertiaryContainer = Color(Colors.Dark.ON_TERTIARY_CONTAINER),

        background = Color(Colors.Dark.BACKGROUND),
        onBackground = Color(Colors.Dark.ON_BACKGROUND),

        surface = Color(Colors.Dark.SURFACE),
        onSurface = Color(Colors.Dark.ON_SURFACE),
        surfaceVariant = Color(Colors.Dark.SURFACE_VARIANT),
        onSurfaceVariant = Color(Colors.Dark.ON_SURFACE_VARIANT),

        outline = Color(Colors.Dark.OUTLINE),
        outlineVariant = Color(Colors.Dark.OUTLINE_VARIANT),

        error = Color(Colors.Dark.ERROR),
        onError = Color(Colors.Dark.ON_ERROR),
        errorContainer = Color(Colors.Dark.ERROR_CONTAINER),
        onErrorContainer = Color(Colors.Dark.ON_ERROR_CONTAINER),

        inverseSurface = Color(Colors.Dark.INVERSE_SURFACE),
        inverseOnSurface = Color(Colors.Dark.INVERSE_ON_SURFACE),
        inversePrimary = Color(Colors.Dark.INVERSE_PRIMARY),

        scrim = Color(Colors.Dark.SCRIM),
        surfaceTint = Color(Colors.Dark.SURFACE_TINT),
    )
}
