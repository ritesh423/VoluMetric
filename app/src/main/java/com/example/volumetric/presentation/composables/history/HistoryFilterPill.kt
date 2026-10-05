package com.example.volumetric.presentation.composables.history

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volumetric.presentation.model.HistoryFilter
import com.example.volumetric.ui.theme.BrutalistBlue
import com.example.volumetric.ui.theme.InkBlack
import com.example.volumetric.ui.theme.PaperBg
import com.example.volumetric.ui.theme.PaperWhite
import com.example.volumetric.ui.theme.brutalistEntry
import com.example.volumetric.ui.theme.brutalistShadow
import com.example.volumetric.ui.theme.pressScale

@Composable
fun HistoryFilterPill(
    selected: HistoryFilter,
    onSelected: (HistoryFilter) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .brutalistEntry(delayMillis = 80, fromRotation = -1f)
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HistoryFilter.values().forEach { option ->
            FilterChip(
                label = option.label,
                isSelected = option == selected,
                onClick = { onSelected(option) }
            )
        }
    }
}

@Composable
private fun FilterChip(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val bg: Color = if (isSelected) BrutalistBlue else PaperWhite
    val fg: Color = if (isSelected) PaperWhite else InkBlack

    Box(
        modifier = Modifier
            .pressScale(interactionSource)
            .brutalistShadow(dx = 2.5.dp, dy = 2.5.dp)
            .background(bg)
            .border(width = 2.dp, color = InkBlack)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .padding(PaddingValues(horizontal = 12.dp, vertical = 8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label.uppercase(),
            color = fg,
            fontSize = 11.sp,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 1.2.sp
        )
    }
}

@Preview
@Composable
fun PreviewHistoryFilterPill() {
    var selected by remember { mutableStateOf(HistoryFilter.ALL) }
    Box(
        modifier = Modifier
            .background(PaperBg)
            .padding(16.dp)
    ) {
        HistoryFilterPill(
            selected = selected,
            onSelected = { selected = it }
        )
    }
}
