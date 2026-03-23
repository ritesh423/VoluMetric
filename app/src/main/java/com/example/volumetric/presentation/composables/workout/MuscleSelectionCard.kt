package com.example.volumetric.presentation.composables.workout

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
fun MuscleSelectionCard(
    muscle: Muscle,
    isSelected: Boolean = false,
    onMuscleSelected: (String) -> Unit = {}
) {
    // Different colors for selected vs unselected state
    val backgroundColor = if (isSelected) Color(0xFF3FE1B0).copy(alpha = 0.2f) else Color(0xDA2C2A2A)
    val borderColor = if (isSelected) Color(0xFF3FE1B0) else Color.Transparent
    val iconTint = if (isSelected) Color(0xFF3FE1B0) else White

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (isSelected) {
                    Modifier.border(2.dp, borderColor, RoundedCornerShape(22.dp))
                } else {
                    Modifier
                }
            ),
        shape = RoundedCornerShape(22.dp),
        onClick = {
            onMuscleSelected(muscle.name)
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(muscle.icon, contentDescription = muscle.name, tint = iconTint)
                Spacer(modifier = Modifier.height(7.dp))
                Text(muscle.name, fontSize = 12.sp, color = White)
            }
        }
    }
}

@Preview
@Composable
fun PreviewMuscleSelection() {
    MuscleSelectionCard(
        muscle = Muscle(name = "Chest", icon = Icons.Default.TrackChanges),
        isSelected = false,
        onMuscleSelected = {}
    )
}

@Preview
@Composable
fun PreviewMuscleSelectionSelected() {
    MuscleSelectionCard(
        muscle = Muscle(name = "Chest", icon = Icons.Default.TrackChanges),
        isSelected = true,
        onMuscleSelected = {}
    )
}

