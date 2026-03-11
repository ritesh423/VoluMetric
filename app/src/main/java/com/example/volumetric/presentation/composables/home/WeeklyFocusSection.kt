package com.example.volumetric.presentation.composables.home

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volumetric.ui.theme.AccentBlue
import com.example.volumetric.ui.theme.AccentPurple
import com.example.volumetric.ui.theme.TextPrimary
import com.example.volumetric.ui.theme.TextSecondary

data class MuscleProgress(val name: String, val progress: Float, val color: androidx.compose.ui.graphics.Color, val trackColor: androidx.compose.ui.graphics.Color)

@Composable
fun WeeklyFocusSection() {
    val muscles = listOf(
        MuscleProgress("Chest", 0.80f, AccentBlue, AccentBlue.copy(alpha = 0.2f)),
        MuscleProgress("Back", 0.40f, AccentPurple, AccentPurple.copy(alpha = 0.2f)),
        MuscleProgress("Legs", 0.95f, TextPrimary, TextPrimary.copy(alpha = 0.2f)),
    )

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Weekly Focus", color = TextPrimary, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(text = "View All", color = AccentBlue, fontSize = 13.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            muscles.forEach { muscle ->
                CircularProgressItem(muscle)
            }
        }
    }
}

@Composable
fun CircularProgressItem(muscle: MuscleProgress) {
    var animationPlayed by remember { mutableStateOf(false) }
    val animatedProgress by animateFloatAsState(
        targetValue = if (animationPlayed) muscle.progress else 0f,
        animationSpec = tween(durationMillis = 1000),
        label = "progress"
    )

    LaunchedEffect(Unit) { animationPlayed = true }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.size(80.dp)) {
            Canvas(modifier = Modifier.size(80.dp)) {
                val strokeWidth = 7.dp.toPx()
                val radius = (size.minDimension - strokeWidth) / 2
                val topLeft = Offset(strokeWidth / 2, strokeWidth / 2)
                val arcSize = Size(radius * 2, radius * 2)

                drawArc(
                    color = muscle.trackColor,
                    startAngle = -90f,
                    sweepAngle = 360f,
                    useCenter = false,
                    topLeft = topLeft,
                    size = arcSize,
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                )

                drawArc(
                    color = muscle.color,
                    startAngle = -90f,
                    sweepAngle = 360f * animatedProgress,
                    useCenter = false,
                    topLeft = topLeft,
                    size = arcSize,
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                )
            }

            Text(
                text = "${(muscle.progress * 100).toInt()}%",
                color = TextPrimary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = muscle.name, color = TextSecondary, fontSize = 13.sp)
    }
}

@Preview
@Composable
fun PreviewWeeklyFocusSection(){
    WeeklyFocusSection()
}