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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.volumetric.ui.theme.BrutalistOrange
import com.example.volumetric.ui.theme.BrutalistYellow
import com.example.volumetric.ui.theme.InkBlack
import com.example.volumetric.ui.theme.PaperWhite
import com.example.volumetric.ui.theme.brutalistEntry
import com.example.volumetric.ui.theme.brutalistShadow
import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.Locale

@Composable
fun WeeklyGoalCard(
    setsCompleted: Int = 0,
    setsTarget: Int = 0,
    avgIntensity: Int = 0,
    weekNumber: Int = currentWeekNumber(),
    onLogWorkoutClick: () -> Unit = {}
) {
    val progress = if (setsTarget == 0) 0f
                   else (setsCompleted.toFloat() / setsTarget).coerceIn(0f, 1f)
    val onTrack = progress >= 0.6f

    Box(modifier = Modifier
        .fillMaxWidth()
        .brutalistEntry(delayMillis = 120)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .brutalistShadow(dx = 6.dp, dy = 6.dp)
                .background(BrutalistOrange)
                .border(width = 3.dp, color = InkBlack)
                .padding(18.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "WEEKLY GOAL",
                        color = InkBlack,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 2.sp
                    )
                    Box(
                        modifier = Modifier
                            .background(InkBlack)
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = "WK $weekNumber",
                            color = BrutalistYellow,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = 1.5.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    StatBlock(
                        value = "$setsCompleted",
                        denom = "/ $setsTarget",
                        label = "SETS"
                    )
                    StatBlock(
                        value = "$avgIntensity",
                        denom = "%",
                        label = "INTENSITY"
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(14.dp)
                        .border(width = 2.dp, color = InkBlack)
                        .background(PaperWhite)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(progress)
                            .height(14.dp)
                            .background(InkBlack)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                StartWorkoutButton(
                    buttonText = "LOG WORKOUT",
                    onClick = onLogWorkoutClick
                )
            }
        }

        if (onTrack) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = (-10).dp, end = (-6).dp)
                    .zIndex(1f)
                    .rotate(6f)
                    .brutalistShadow(dx = 3.dp, dy = 3.dp)
                    .background(BrutalistYellow)
                    .border(width = 2.dp, color = InkBlack)
                    .padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
                Text(
                    text = "★ ON TRACK",
                    color = InkBlack,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 1.5.sp
                )
            }
        }
    }
}

@Composable
private fun StatBlock(value: String, denom: String, label: String) {
    Column {
        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                text = value,
                color = InkBlack,
                fontSize = 48.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = (-2).sp,
                lineHeight = 48.sp
            )
            Text(
                text = denom,
                color = InkBlack,
                fontSize = 18.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp, start = 2.dp)
            )
        }
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            color = InkBlack,
            fontSize = 10.sp,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 1.5.sp
        )
    }
}

private fun currentWeekNumber(): Int = try {
    LocalDate.now().get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear())
} catch (e: Exception) {
    0
}

@Preview
@Composable
fun PreviewWeeklyGoal() {
    WeeklyGoalCard(setsCompleted = 68, setsTarget = 100, avgIntensity = 72)
}
