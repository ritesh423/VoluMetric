package com.example.volumetric.presentation.composables.history

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volumetric.data.WorkoutDetailEntity
import com.example.volumetric.presentation.mapper.toWorkoutDetail
import com.example.volumetric.presentation.model.DateBucket
import com.example.volumetric.ui.theme.InkBlack
import com.example.volumetric.ui.theme.PaperBg

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GroupedWorkoutList(
    grouped: Map<DateBucket, List<WorkoutDetailEntity>>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(top = 8.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        grouped.forEach { (bucket, workouts) ->
            item(key = "header-${bucket.label}") {
                SectionHeader(label = bucket.label)
            }

            items(
                items = workouts,
                key = { workout -> workout.id }
            ) { workout ->
                AllWorkoutCard(workout.toWorkoutDetail())
            }
        }
    }
}

@Composable
private fun SectionHeader(label: String) {
    Box(
        modifier = Modifier
            .padding(top = 10.dp, bottom = 4.dp)
            .background(InkBlack)
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = label,
            color = PaperBg,
            fontSize = 11.sp,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 2.sp
        )
    }
}
