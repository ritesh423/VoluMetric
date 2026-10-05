package com.example.volumetric.presentation.composables.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volumetric.presentation.model.Muscle
import com.example.volumetric.ui.theme.BrutalistBlue
import com.example.volumetric.ui.theme.BrutalistOrange
import com.example.volumetric.ui.theme.BrutalistPink
import com.example.volumetric.ui.theme.BrutalistYellow
import com.example.volumetric.ui.theme.InkBlack
import com.example.volumetric.ui.theme.MuteGrey
import com.example.volumetric.ui.theme.PaperBg
import com.example.volumetric.ui.theme.PaperWhite
import com.example.volumetric.ui.theme.brutalistEntry
import com.example.volumetric.ui.theme.brutalistShadow

@Composable
fun MuscleGroupCard(muscle: Muscle) {
    val progress = if (muscle.target == 0) 0f
                   else (muscle.completed.toFloat() / muscle.target).coerceIn(0f, 1f)
    val barColor = progressColor(progress)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .brutalistEntry(delayMillis = 180, fromRotation = -2f)
            .brutalistShadow(dx = 4.dp, dy = 4.dp)
            .background(PaperWhite)
            .border(width = 2.dp, color = InkBlack)
            .padding(12.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = muscle.name.uppercase(),
                    color = InkBlack,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 1.4.sp
                )
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = "${muscle.completed}",
                        color = InkBlack,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = (-1).sp,
                        lineHeight = 22.sp
                    )
                    Text(
                        text = "/${muscle.target}",
                        color = MuteGrey,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 1.dp, start = 1.dp)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .border(width = 1.5.dp, color = InkBlack)
                    .background(PaperBg)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(progress)
                        .height(8.dp)
                        .background(barColor)
                )
            }

            Spacer(modifier = Modifier.height(0.dp))
        }
    }
}

private fun progressColor(progress: Float): Color = when {
    progress >= 0.75f -> BrutalistBlue
    progress >= 0.45f -> BrutalistYellow
    progress >= 0.25f -> BrutalistOrange
    else              -> BrutalistPink
}
