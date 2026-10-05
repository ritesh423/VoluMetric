package com.example.volumetric.presentation.composables.history

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volumetric.presentation.model.WorkoutDetail
import com.example.volumetric.ui.theme.BrutalistYellow
import com.example.volumetric.ui.theme.InkBlack
import com.example.volumetric.ui.theme.MuteGrey
import com.example.volumetric.ui.theme.PaperWhite
import com.example.volumetric.ui.theme.brutalistShadow

@Composable
fun AllWorkoutCard(stat: WorkoutDetail) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .brutalistShadow(dx = 3.dp, dy = 3.dp)
            .background(PaperWhite)
            .border(width = 2.dp, color = InkBlack)
            .padding(PaddingValues(horizontal = 12.dp, vertical = 10.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = stat.exerciseName,
                color = InkBlack,
                fontSize = 15.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = (-0.3).sp,
                maxLines = 1
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stat.muscleGroup.uppercase(),
                    color = MuteGrey,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 1.4.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "·",
                    color = MuteGrey,
                    fontSize = 10.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stat.createdAt,
                    color = MuteGrey,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
            }
        }

        Spacer(modifier = Modifier.width(10.dp))

        Box(
            modifier = Modifier
                .background(BrutalistYellow)
                .border(width = 2.dp, color = InkBlack)
                .padding(horizontal = 8.dp, vertical = 2.dp)
        ) {
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "${stat.totalSets}",
                    color = InkBlack,
                    fontSize = 22.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = (-1).sp,
                    lineHeight = 24.sp
                )
                Text(
                    text = "×",
                    color = MuteGrey,
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 2.dp, bottom = 2.dp)
                )
            }
        }
    }
}
