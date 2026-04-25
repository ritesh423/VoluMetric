package com.example.volumetric.presentation.composables.history

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.volumetric.data.mappers.toWorkoutDetail
import com.example.volumetric.domain.models.DateBucket
import com.example.volumetric.ui.theme.TextMuted

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GroupedWorkoutList(
    grouped: Map<DateBucket, List<WorkoutDetailEntity>>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
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
    Text(
        text = label,
        color = TextMuted,
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 1.5.sp,
        modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
    )
}
