package com.example.volumetric.ui.theme

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// Hard offset block-shadow drawn behind the component. Apply BEFORE
// .background(...) so the shadow sits behind the surface fill.
fun Modifier.brutalistShadow(
    dx: Dp = 4.dp,
    dy: Dp = 4.dp,
    color: Color = InkBlack
): Modifier = this.drawBehind {
    drawRect(
        color = color,
        topLeft = Offset(dx.toPx(), dy.toPx()),
        size = Size(size.width, size.height)
    )
}
