package com.example.volumetric.ui.theme

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

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

// Spring-in on first composition: scales up + fades + a small rotation
// settle. Use sparingly on hero / above-the-fold elements.
@Composable
fun Modifier.brutalistEntry(
    delayMillis: Int = 0,
    fromRotation: Float = -3f
): Modifier {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        if (delayMillis > 0) delay(delayMillis.toLong())
        visible = true
    }
    val scale by animateFloatAsState(
        targetValue = if (visible) 1f else 0.86f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMediumLow
        ),
        label = "brutalistEntryScale"
    )
    val rotation by animateFloatAsState(
        targetValue = if (visible) 0f else fromRotation,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMediumLow
        ),
        label = "brutalistEntryRotation"
    )
    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(durationMillis = 220),
        label = "brutalistEntryAlpha"
    )
    return this.graphicsLayer {
        this.scaleX = scale
        this.scaleY = scale
        this.rotationZ = rotation
        this.alpha = alpha
    }
}

// Scales the component down slightly while it's being pressed.
@Composable
fun Modifier.pressScale(
    interactionSource: InteractionSource,
    pressedScale: Float = 0.94f
): Modifier {
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) pressedScale else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessHigh
        ),
        label = "pressScale"
    )
    return this.graphicsLayer {
        this.scaleX = scale
        this.scaleY = scale
    }
}
