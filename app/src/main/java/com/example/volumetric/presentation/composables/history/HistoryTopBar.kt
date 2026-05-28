package com.example.volumetric.presentation.composables.history

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volumetric.ui.theme.BrutalistOrange
import com.example.volumetric.ui.theme.InkBlack
import com.example.volumetric.ui.theme.MuteGrey
import com.example.volumetric.ui.theme.PaperWhite
import com.example.volumetric.ui.theme.brutalistEntry
import com.example.volumetric.ui.theme.brutalistShadow

@Composable
fun HistoryTopBar(
    totalCount: Int = 0
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .brutalistEntry()
            .padding(top = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "THE ARCHIVE",
                color = MuteGrey,
                fontSize = 11.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 2.sp
            )
            Text(
                text = "History.",
                color = InkBlack,
                fontSize = 36.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = (-1.2).sp
            )
        }

        Box(
            modifier = Modifier
                .rotate(2.5f)
                .brutalistShadow(dx = 3.dp, dy = 3.dp)
                .background(BrutalistOrange)
                .border(width = 2.dp, color = InkBlack)
                .padding(horizontal = 10.dp, vertical = 5.dp)
        ) {
            Text(
                text = "$totalCount ENTRIES",
                color = PaperWhite,
                fontSize = 11.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 1.5.sp
            )
        }
    }
}

@Preview
@Composable
fun PreviewHistoryTopBar() {
    HistoryTopBar(totalCount = 47)
}
