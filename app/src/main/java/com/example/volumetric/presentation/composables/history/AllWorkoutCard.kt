package com.example.volumetric.presentation.composables.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volumetric.domain.models.WorkoutDetail
import com.example.volumetric.ui.theme.PurpleGrey40
import com.example.volumetric.ui.theme.White

@Composable
fun AllWorkoutCard(stat: WorkoutDetail) {
    Card(
        modifier = Modifier.fillMaxWidth().size(65.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = PurpleGrey40)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.size(40.dp).clip(shape = RoundedCornerShape(50)).background(Color(0x6C63FFFF)).padding(5.dp)){
                Icon(imageVector = Icons.Default.Work, contentDescription = "Icon")
            }
            Spacer(modifier = Modifier.width(10.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Column() {
                    Text(
                        text = stat.exerciseName,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = White
                    )
                    Text(
                        text = stat.muscleGroup,
                        fontSize = 14.sp,
                        color = White.copy(alpha = 0.7f)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Column() {
                    Text(
                        text = stat.totalSets.toString(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = White
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = stat.createdAt,
                        fontSize = 14.sp,
                        color = White.copy(alpha = 0.7f)
                    )
                }
            }
        }
    }

}
