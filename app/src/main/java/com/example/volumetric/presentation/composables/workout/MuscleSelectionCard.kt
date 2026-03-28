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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volumetric.domain.models.Muscle
import com.example.volumetric.ui.theme.AccentBlue
import com.example.volumetric.ui.theme.AccentPurple
import com.example.volumetric.ui.theme.TextSecondary
import com.example.volumetric.ui.theme.White

@Composable
fun MuscleSelectionCard(
    muscle: Muscle,
    isSelected: Boolean = false,
    onMuscleSelected: (String) -> Unit = {}
) {
    val selectedGradient = Brush.linearGradient(
        colors = listOf(AccentBlue, AccentPurple)
    )
    val unselectedBackground = Color(0xFF1C1A35)
    val iconTint = if (isSelected) White else TextSecondary
    val textColor = if (isSelected) White else TextSecondary

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (isSelected) {
                    Modifier.border(
                        width = 1.5.dp,
                        brush = Brush.linearGradient(listOf(AccentBlue, AccentPurple)),
                        shape = RoundedCornerShape(16.dp)
                    )
                } else {
                    Modifier
                }
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        onClick = { onMuscleSelected(muscle.name) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .then(
                    if (isSelected) {
                        Modifier.background(selectedGradient, RoundedCornerShape(16.dp))
                    } else {
                        Modifier.background(unselectedBackground, RoundedCornerShape(16.dp))
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = muscle.icon,
                    contentDescription = muscle.name,
                    tint = iconTint,
                    modifier = Modifier.height(26.dp)
                )
                Spacer(modifier = Modifier.height(7.dp))
                Text(
                    text = muscle.name,
                    fontSize = 12.sp,
                    fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                    color = textColor
                )
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
