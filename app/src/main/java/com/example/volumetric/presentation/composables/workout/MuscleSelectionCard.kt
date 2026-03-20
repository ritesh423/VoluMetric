package com.example.volumetric.presentation.composables.workout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TrackChanges
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volumetric.domain.models.Muscle
import com.example.volumetric.ui.theme.White

@Composable
fun MuscleSelectionCard(muscle: Muscle) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xDA2C2A2A)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(muscle.icon, contentDescription = "Icon", tint = White)
                Spacer(modifier = Modifier.height(7.dp))
                Text(muscle.name, fontSize = 12.sp, color = White)
            }

        }

    }
}

@Preview
@Composable
fun PreviewMuscleSelection(){
    MuscleSelectionCard(Muscle(name = "Chest", icon = Icons.Default.TrackChanges))
}

