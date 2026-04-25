package com.example.volumetric.presentation.composables.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volumetric.data.mappers.muscleIcons
import com.example.volumetric.domain.models.WorkoutDetail
import com.example.volumetric.ui.theme.AccentPurple
import com.example.volumetric.ui.theme.SurfaceCard
import com.example.volumetric.ui.theme.TextPrimary
import com.example.volumetric.ui.theme.TextSecondary

@Composable
fun AllWorkoutCard(stat: WorkoutDetail) {
    val icon = muscleIcons[stat.muscleGroup] ?: Icons.Default.FitnessCenter

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = SurfaceCard,
                shape = RoundedCornerShape(14.dp)
            )
            .padding(PaddingValues(horizontal = 14.dp, vertical = 12.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left: round icon badge
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = AccentPurple.copy(alpha = 0.18f),
                    shape = RoundedCornerShape(50)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = AccentPurple,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Middle: exercise name + muscle group
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = stat.exerciseName,
                color = TextPrimary,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
            )
            Text(
                text = stat.muscleGroup,
                color = TextSecondary,
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Right: sets + time stacked
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "${stat.totalSets} sets",
                color = AccentPurple,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.AccessTime,
                    contentDescription = null,
                    tint = TextSecondary,
                    modifier = Modifier.size(12.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stat.createdAt,
                    color = TextSecondary,
                    fontSize = 11.sp
                )
            }
        }
    }
}
