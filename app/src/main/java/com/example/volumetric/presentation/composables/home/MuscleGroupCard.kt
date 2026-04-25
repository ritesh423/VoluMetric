package com.example.volumetric.presentation.composables.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volumetric.domain.models.Muscle
import com.example.volumetric.ui.theme.AccentPurple
import com.example.volumetric.ui.theme.SurfaceCard
import com.example.volumetric.ui.theme.TextPrimary
import com.example.volumetric.ui.theme.TextSecondary

@Composable
fun MuscleGroupCard(muscle: Muscle) {

    val progress = if (muscle.target == 0) 0f
                   else (muscle.completed.toFloat() / muscle.target).coerceIn(0f, 1f)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceCard),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            color = AccentPurple.copy(alpha = 0.18f),
                            shape = RoundedCornerShape(50)
                        )
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = muscle.icon,
                        contentDescription = muscle.name,
                        tint = AccentPurple
                    )
                }

                Box(
                    modifier = Modifier
                        .background(
                            color = Color.White.copy(alpha = 0.06f),
                            shape = RoundedCornerShape(50)
                        )
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "Weekly",
                        color = TextSecondary,
                        fontSize = 10.sp
                    )
                }
            }

            Text(
                text = muscle.name.uppercase(),
                fontSize = 12.sp,
                color = TextSecondary,
                letterSpacing = 1.sp
            )

            Row {
                Text(
                    text = "${muscle.completed}",
                    fontSize = 28.sp,
                    color = TextPrimary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = " / ${muscle.target}",
                    fontSize = 18.sp,
                    color = TextSecondary,
                    modifier = Modifier.padding(top = 6.dp)
                )
            }

            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier.fillMaxWidth(),
                color = AccentPurple,
                trackColor = Color.White.copy(alpha = 0.08f)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Progress",
                    fontSize = 12.sp,
                    color = TextSecondary
                )
                Text(
                    text = "${(progress * 100).toInt()}%",
                    fontSize = 12.sp,
                    color = TextSecondary
                )
            }
        }
    }
}
