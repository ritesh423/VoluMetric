package com.example.volumetric.ui.theme

import androidx.compose.ui.graphics.Color

// Brutalist palette — cream paper, black ink, flat poster blocks.
val PaperBg          = Color(0xFFFFF8E7)
val PaperWhite       = Color(0xFFFFFFFF)
val InkBlack         = Color(0xFF1B1B1B)
val BrutalistOrange  = Color(0xFFFF5C00)
val BrutalistBlue    = Color(0xFF2E5BFF)
val BrutalistYellow  = Color(0xFFFFD93D)
val BrutalistPink    = Color(0xFFFF6FB5)
val MuteGrey         = Color(0xFF6B6B6B)
val FaintGrey        = Color(0xFFE5E0D6)

// Legacy aliases — kept temporarily so screens not yet restyled (Workout,
// History) still compile. Removed in the next commit once those screens
// are ported to the brutalist tokens above.
val BackgroundDark   = PaperBg
val SurfaceDark      = PaperWhite
val SurfaceCard      = PaperWhite
val AccentBlue       = BrutalistBlue
val AccentBlueLight  = BrutalistBlue
val AccentPurple     = BrutalistOrange
val TextPrimary      = InkBlack
val TextSecondary    = MuteGrey
val TextMuted        = MuteGrey
val White            = PaperWhite
val Whiteless        = PaperBg
val GradientStart    = BrutalistBlue
val GradientEnd      = BrutalistOrange
val Purple80         = BrutalistOrange
val PurpleGrey80     = MuteGrey
val Pink80           = BrutalistPink
val Purple40         = BrutalistOrange
val PurpleGrey40     = MuteGrey
val Pink40           = BrutalistPink
