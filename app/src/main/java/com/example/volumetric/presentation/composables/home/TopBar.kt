package com.example.volumetric.presentation.composables.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volumetric.ui.theme.BrutalistYellow
import com.example.volumetric.ui.theme.InkBlack
import com.example.volumetric.ui.theme.PaperWhite
import com.example.volumetric.ui.theme.brutalistEntry
import com.example.volumetric.ui.theme.brutalistShadow

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .brutalistEntry()
            .padding(top = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .rotate(-1.5f)
                .brutalistShadow(dx = 4.dp, dy = 4.dp)
                .background(BrutalistYellow)
                .border(width = 2.dp, color = InkBlack)
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Text(
                text = "VOLUMETRIC",
                color = InkBlack,
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = (-0.5).sp
            )
        }

        Box(
            modifier = Modifier
                .size(40.dp)
                .brutalistShadow(dx = 3.dp, dy = 3.dp)
                .background(PaperWhite)
                .border(width = 2.dp, color = InkBlack),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Tune,
                contentDescription = "Filter",
                tint = InkBlack,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewTopBar() {
    TopBar()
}
