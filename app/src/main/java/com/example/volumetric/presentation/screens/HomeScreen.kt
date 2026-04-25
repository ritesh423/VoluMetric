package com.example.volumetric.presentation.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.volumetric.data.mappers.toMuscle
import com.example.volumetric.domain.viewmodel.MuscleStatsViewModel
import com.example.volumetric.presentation.composables.home.GreetingSection
import com.example.volumetric.presentation.composables.home.MuscleGroupCard
import com.example.volumetric.presentation.composables.home.TopBar
import com.example.volumetric.presentation.composables.home.WeeklyGoalCard
import com.example.volumetric.ui.theme.BackgroundDark

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    userName: String = "Ritesh",
    onNavigateToLog: () -> Unit = {},
    viewModel: MuscleStatsViewModel = hiltViewModel()
) {
    val weeklyStats by viewModel.weeklyStats.collectAsState()
    val setsCompleted by viewModel.weeklySetsCompleted.collectAsState()
    val avgIntensity by viewModel.avgIntensity.collectAsState()
    val setsTarget = viewModel.weeklySetsTarget

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(top = 16.dp, bottom = 100.dp)
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                TopBar()
            }

            item(span = { GridItemSpan(maxLineSpan) }) {
                GreetingSection(userName)
            }

            item(span = { GridItemSpan(maxLineSpan) }) {
                WeeklyGoalCard(
                    setsCompleted = setsCompleted,
                    setsTarget = setsTarget,
                    avgIntensity = avgIntensity,
                    onLogWorkoutClick = onNavigateToLog
                )
            }

            items(weeklyStats) { stat ->
                MuscleGroupCard(stat.toMuscle())
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}
