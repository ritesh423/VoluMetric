package com.example.volumetric.presentation.composables.history

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volumetric.ui.theme.AccentPurple
import com.example.volumetric.ui.theme.BackgroundDark
import com.example.volumetric.ui.theme.SurfaceCard
import com.example.volumetric.ui.theme.TextMuted
import com.example.volumetric.ui.theme.TextPrimary

@Composable
fun StatCardRow(
    totalWorkouts: Int,
    thisWeekCount: Int,
    mostTrainedMuscle: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        StatCard(
            icon = Icons.Default.FitnessCenter,
            value = totalWorkouts.toString(),
            label = "TOTAL"
        )
        StatCard(
            icon = Icons.Default.CalendarMonth,
            value = thisWeekCount.toString(),
            label = "THIS WEEK"
        )
        StatCard(
            icon = Icons.Default.FitnessCenter,
            value = mostTrainedMuscle,
            label = "TRAINED"
        )
    }
}

@Composable
private fun RowScope.StatCard(
    icon: ImageVector,
    value: String,
    label: String
) {
    Column(
        modifier = Modifier
            .weight(1f)
            .height(110.dp)
            .background(
                color = SurfaceCard,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(PaddingValues(vertical = 14.dp, horizontal = 8.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .background(
                    color = AccentPurple.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(8.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = AccentPurple,
                modifier = Modifier.size(16.dp)
            )
        }

        Text(
            text = value,
            color = TextPrimary,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )

        Text(
            text = label,
            color = TextMuted,
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 1.sp
        )
    }
}

@Preview
@Composable
fun PreviewStatCardRow() {
    Box(modifier = Modifier.background(BackgroundDark).padding(16.dp)) {
        StatCardRow(
            totalWorkouts = 42,
            thisWeekCount = 4,
            mostTrainedMuscle = "Chest"
        )
    }
}
