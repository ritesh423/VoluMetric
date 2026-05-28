package com.example.volumetric.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val BrutalistColorScheme = lightColorScheme(
    primary          = BrutalistBlue,
    onPrimary        = PaperWhite,
    secondary        = BrutalistOrange,
    onSecondary      = InkBlack,
    tertiary         = BrutalistYellow,
    onTertiary       = InkBlack,
    background       = PaperBg,
    onBackground     = InkBlack,
    surface          = PaperWhite,
    onSurface        = InkBlack,
    surfaceVariant   = PaperBg,
    onSurfaceVariant = InkBlack,
    outline          = InkBlack,
    error            = BrutalistOrange,
    onError          = PaperWhite
)

@Composable
fun VoluMetricTheme(content: @Composable () -> Unit) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                WindowCompat.getInsetsController(window, view).apply {
                    isAppearanceLightStatusBars = true
                    isAppearanceLightNavigationBars = true
                }
            }
        }
    }
    MaterialTheme(
        colorScheme = BrutalistColorScheme,
        typography  = Typography,
        content     = content
    )
}
