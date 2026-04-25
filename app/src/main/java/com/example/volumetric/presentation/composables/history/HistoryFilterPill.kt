package com.example.volumetric.presentation.composables.history

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volumetric.domain.models.HistoryFilter
import com.example.volumetric.ui.theme.AccentPurple
import com.example.volumetric.ui.theme.BackgroundDark
import com.example.volumetric.ui.theme.SurfaceCard
import com.example.volumetric.ui.theme.TextPrimary
import com.example.volumetric.ui.theme.TextSecondary

@Composable
fun HistoryFilterPill(
    selected: HistoryFilter,
    onSelected: (HistoryFilter) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(50))
            .background(SurfaceCard)
            .padding(4.dp)
    ) {
        HistoryFilter.values().forEach { option ->
            FilterSegment(
                option = option,
                isSelected = option == selected,
                onClick = { onSelected(option) }
            )
        }
    }
}

@Composable
private fun RowScope.FilterSegment(
    option: HistoryFilter,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .weight(1f)
            .clip(RoundedCornerShape(50))
            .background(if (isSelected) AccentPurple else androidx.compose.ui.graphics.Color.Transparent)
            .clickable(onClick = onClick)
            .padding(PaddingValues(vertical = 10.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = option.label,
            color = if (isSelected) TextPrimary else TextSecondary,
            fontSize = 13.sp,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium
        )
    }
}

@Preview
@Composable
fun PreviewHistoryFilterPill() {
    var selected by remember { mutableStateOf(HistoryFilter.ALL) }
    Box(
        modifier = Modifier
            .background(BackgroundDark)
            .padding(16.dp)
    ) {
        HistoryFilterPill(
            selected = selected,
            onSelected = { selected = it }
        )
    }
}
