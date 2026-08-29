package org.b3.agrios.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

internal val LightColorScheme = lightColorScheme(
    primary = Color(0xFF2EAD58),
    onPrimary = Color(0xFFFFFFFF),

    primaryContainer = Color(0xFFD6F5DF),
    onPrimaryContainer = Color(0xFF0B3B1A),

    background = Color(0xFFF5F7F9),
    onBackground = Color(0xFF121212),

    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF121212),

    surfaceVariant = Color(0xFFEFF2F4),
    onSurfaceVariant = Color(0xFF3F474D),

    outline = Color(0xFF2EAD58),
    outlineVariant = Color(0xFFE3E7EA),
)

internal val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF72D98C),
    onPrimary = Color(0xFF003914),

    primaryContainer = Color(0xFF075322),
    onPrimaryContainer = Color(0xFFB8F3C6),

    background = Color(0xFF121212),
    onBackground = Color(0xFFF5F5F5),

    surface = Color(0xFF0F0F0F),
    onSurface = Color(0xFFF5F5F5),

    surfaceVariant = Color(0xFF2A2D2F),
    onSurfaceVariant = Color(0xFFD0D5D8),

    outline = Color(0xFF72D98C),
    outlineVariant = Color(0xFF454A4F),
)
