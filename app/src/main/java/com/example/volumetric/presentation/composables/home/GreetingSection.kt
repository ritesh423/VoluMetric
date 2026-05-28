package com.example.volumetric.presentation.composables.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.example.volumetric.ui.theme.BrutalistBlue
import com.example.volumetric.ui.theme.InkBlack
import com.example.volumetric.ui.theme.MuteGrey
import com.example.volumetric.ui.theme.PaperWhite
import com.example.volumetric.ui.theme.brutalistShadow
import java.time.LocalDate
import java.time.format.TextStyle as JavaTextStyle
import java.time.temporal.WeekFields
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GreetingSection(userName: String) {
    val today = LocalDate.now()
    val dayName = today.dayOfWeek.getDisplayName(JavaTextStyle.SHORT, Locale.getDefault()).uppercase()
    val weekNum = today.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear())
    val dayOfWeekIndex = ((today.dayOfWeek.value + 6) % 7) + 1

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "$dayName · WK $weekNum",
                color = MuteGrey,
                fontSize = 11.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 2.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Hey, $userName.",
                color = InkBlack,
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = (-1).sp
            )
        }

        Box(
            modifier = Modifier
                .rotate(2f)
                .brutalistShadow(dx = 3.dp, dy = 3.dp)
                .background(BrutalistBlue)
                .border(width = 2.dp, color = InkBlack)
                .padding(horizontal = 10.dp, vertical = 6.dp)
        ) {
            Text(
                text = "DAY $dayOfWeekIndex",
                color = PaperWhite,
                fontSize = 11.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 1.5.sp
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreviewGreetingSection() {
    GreetingSection("Shubham")
}
