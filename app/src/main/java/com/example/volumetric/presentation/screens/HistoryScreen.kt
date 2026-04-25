package com.example.volumetric.presentation.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.volumetric.domain.viewmodel.MuscleStatsViewModel
import com.example.volumetric.presentation.composables.history.GroupedWorkoutList
import com.example.volumetric.presentation.composables.history.HistoryFilterPill
import com.example.volumetric.presentation.composables.history.HistoryTopBar
import com.example.volumetric.presentation.composables.history.StatCardRow
import com.example.volumetric.ui.theme.AccentPurple
import com.example.volumetric.ui.theme.BackgroundDark
import com.example.volumetric.ui.theme.TextSecondary

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HistoryScreen(
    viewModel: MuscleStatsViewModel = hiltViewModel()
) {
    val isLoading by viewModel.isLoading.collectAsState()

    val totalWorkouts by viewModel.totalWorkouts.collectAsState()
    val thisWeekCount by viewModel.thisWeekCount.collectAsState()
    val mostTrainedMuscle by viewModel.mostTrainedMuscle.collectAsState()

    val selectedFilter by viewModel.selectedFilter.collectAsState()
    val groupedWorkouts by viewModel.groupedWorkouts.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        HistoryTopBar(
            onBackClick = { /* hook up later */ },
            onFilterClick = { /* hook up later */ }
        )

        StatCardRow(
            totalWorkouts = totalWorkouts,
            thisWeekCount = thisWeekCount,
            mostTrainedMuscle = mostTrainedMuscle
        )

        HistoryFilterPill(
            selected = selectedFilter,
            onSelected = viewModel::onFilterSelected
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            when {
                isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = AccentPurple)
                    }
                }
                groupedWorkouts.isEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "No workouts to show",
                                fontSize = 16.sp,
                                color = TextSecondary
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Try a different filter or log your first workout.",
                                fontSize = 12.sp,
                                color = TextSecondary
                            )
                        }
                    }
                }
                else -> {
                    GroupedWorkoutList(grouped = groupedWorkouts)
                }
            }
        }
    }
}
