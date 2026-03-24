package com.example.volumetric.presentation.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.volumetric.data.MuscleGroupWeeklyStats
import com.example.volumetric.domain.viewmodel.MuscleStatsViewModel
import com.example.volumetric.ui.theme.BackgroundDark
import com.example.volumetric.ui.theme.PurpleGrey40
import com.example.volumetric.ui.theme.White

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ThirdScreen(
    viewModel: MuscleStatsViewModel = hiltViewModel()
) {

    val weeklyStats by viewModel.weeklyStats.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .padding(16.dp)
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
            weeklyStats.isEmpty() -> {
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
                    items(weeklyStats) { stat ->
                        MuscleGroupCard(stat)
                    }
                }
            }
        }
    }
}

@Composable
fun MuscleGroupCard(stat: MuscleGroupWeeklyStats) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = PurpleGrey40)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Muscle Group Name
            Text(
                text = stat.muscleGroup,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = White
            )
            // Total Sets
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "${stat.totalSets}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = White
                )
                Text(
                    text = "sets",
                    fontSize = 12.sp,
                    color = White.copy(alpha = 0.7f)
                )
            }
        }
    }
}