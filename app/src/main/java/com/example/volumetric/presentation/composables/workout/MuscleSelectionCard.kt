package com.example.volumetric.presentation.composables.workout

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TrackChanges
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volumetric.presentation.model.Muscle
import com.example.volumetric.ui.theme.BrutalistBlue
import com.example.volumetric.ui.theme.InkBlack
import com.example.volumetric.ui.theme.PaperWhite
import com.example.volumetric.ui.theme.brutalistShadow
import com.example.volumetric.ui.theme.pressScale

@Composable
fun MuscleSelectionCard(
    muscle: Muscle,
    isSelected: Boolean = false,
    onMuscleSelected: (String) -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }
    val bg: Color = if (isSelected) BrutalistBlue else PaperWhite
    val tint: Color = if (isSelected) PaperWhite else InkBlack

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .pressScale(interactionSource)
            .brutalistShadow(dx = 3.dp, dy = 3.dp)
            .background(bg)
            .border(width = 2.dp, color = InkBlack)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = { onMuscleSelected(muscle.name) }
            )
            .padding(vertical = 14.dp, horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = muscle.icon,
                contentDescription = muscle.name,
                tint = tint,
                modifier = Modifier.height(24.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = muscle.name.uppercase(),
                fontSize = 11.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 1.2.sp,
                color = tint
            )
        }
    }
}

@Preview
@Composable
fun PreviewMuscleSelection() {
    MuscleSelectionCard(
        muscle = Muscle(name = "Chest", icon = Icons.Default.TrackChanges),
        isSelected = false
    )
}

@Preview
@Composable
fun PreviewMuscleSelectionSelected() {
    MuscleSelectionCard(
        muscle = Muscle(name = "Chest", icon = Icons.Default.TrackChanges),
        isSelected = true
    )
}
