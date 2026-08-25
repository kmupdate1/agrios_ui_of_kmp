package org.b3.agrios.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

internal val LightColorScheme = lightColorScheme(
    primary = Color(0xFF2EAD58),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFD6F5DF),
    onPrimaryContainer = Color(0xFF0B3B1A),
    background = Color(0xFFF5F7F9),
    surface = Color.White,
    surfaceVariant = Color(0xFFEFF2F4),
    outline = Color(0xFFD8DEE3),
)

internal val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF72D98C),
    onPrimary = Color(0xFF003914),
    primaryContainer = Color(0xFF075322),
    onPrimaryContainer = Color(0xFFB8F3C6),
)
