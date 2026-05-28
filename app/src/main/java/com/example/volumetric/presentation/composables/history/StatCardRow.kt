package com.example.volumetric.presentation.composables.history

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volumetric.ui.theme.BrutalistBlue
import com.example.volumetric.ui.theme.BrutalistOrange
import com.example.volumetric.ui.theme.InkBlack
import com.example.volumetric.ui.theme.MuteGrey
import com.example.volumetric.ui.theme.PaperBg
import com.example.volumetric.ui.theme.PaperWhite
import com.example.volumetric.ui.theme.brutalistEntry
import com.example.volumetric.ui.theme.brutalistShadow

@Composable
fun StatCardRow(
    totalWorkouts: Int,
    thisWeekCount: Int,
    mostTrainedMuscle: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .brutalistEntry(delayMillis = 120, fromRotation = -1.5f),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        StatCard(
            value = totalWorkouts.toString(),
            label = "TOTAL",
            background = PaperWhite,
            foreground = InkBlack,
            valueIsMono = true
        )
        StatCard(
            value = thisWeekCount.toString(),
            label = "THIS WEEK",
            background = BrutalistBlue,
            foreground = PaperWhite,
            valueIsMono = true
        )
        StatCard(
            value = mostTrainedMuscle.uppercase(),
            label = "TRAINED",
            background = BrutalistOrange,
            foreground = InkBlack,
            valueIsMono = false
        )
    }
}

@Composable
private fun RowScope.StatCard(
    value: String,
    label: String,
    background: Color,
    foreground: Color,
    valueIsMono: Boolean
) {
    Column(
        modifier = Modifier
            .weight(1f)
            .height(96.dp)
            .brutalistShadow(dx = 4.dp, dy = 4.dp)
            .background(background)
            .border(width = 2.dp, color = InkBlack)
            .padding(PaddingValues(horizontal = 10.dp, vertical = 10.dp)),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = value,
            color = foreground,
            fontSize = if (valueIsMono) 30.sp else 18.sp,
            fontFamily = if (valueIsMono) FontFamily.Monospace else FontFamily.SansSerif,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = (-1).sp,
            lineHeight = 30.sp,
            maxLines = 1
        )
        Box(
            modifier = Modifier
                .background(InkBlack)
                .padding(horizontal = 6.dp, vertical = 2.dp)
        ) {
            Text(
                text = label,
                color = PaperBg,
                fontSize = 9.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 1.4.sp
            )
        }
    }
}

@Preview
@Composable
fun PreviewStatCardRow() {
    Box(modifier = Modifier.background(PaperBg).padding(16.dp)) {
        StatCardRow(
            totalWorkouts = 47,
            thisWeekCount = 9,
            mostTrainedMuscle = "Back"
        )
    }
}
