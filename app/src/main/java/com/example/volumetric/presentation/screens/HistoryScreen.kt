package com.example.volumetric.presentation.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.volumetric.data.mappers.toWorkoutDetail
import com.example.volumetric.domain.viewmodel.MuscleStatsViewModel
import com.example.volumetric.presentation.composables.history.AllWorkoutCard
import com.example.volumetric.ui.theme.BackgroundDark
import com.example.volumetric.ui.theme.PurpleGrey40
import com.example.volumetric.ui.theme.White

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HistoryScreen(
    viewModel: MuscleStatsViewModel = hiltViewModel()
) {

    val weeklyStats by viewModel.weeklyStats.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val allStats by viewModel.allStats.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .padding(22.dp)
    ) {
        // Title
        Text(
            text = "Weekly Muscle Stats",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = White,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        when {
            isLoading -> {
                // Show loading indicator
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = PurpleGrey40)
                }
            }
            allStats.isEmpty() -> {
                // Show empty state
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No workouts logged this week",
                        fontSize = 16.sp,
                        color = White
                    )
                }
            }
            else -> {
                // Show list of cards
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(allStats) { stat ->
                        AllWorkoutCard(stat.toWorkoutDetail())
                    }
                }
            }
        }
    }
}
