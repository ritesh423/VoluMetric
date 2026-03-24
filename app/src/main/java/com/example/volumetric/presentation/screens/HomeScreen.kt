package com.example.volumetric.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessAlarms
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Cached
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.SportsHandball
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.volumetric.domain.models.Muscle
import com.example.volumetric.presentation.composables.home.GreetingSection
import com.example.volumetric.presentation.composables.home.MuscleGroupCard
import com.example.volumetric.presentation.composables.home.TopBar
import com.example.volumetric.presentation.composables.home.WeeklyGoalCard
import com.example.volumetric.ui.theme.AccentPurple
import com.example.volumetric.ui.theme.BackgroundDark
import com.example.volumetric.ui.theme.GradientEnd
import com.example.volumetric.ui.theme.TextPrimary

@Composable

fun HomeScreen(userName: String = "Ritesh") {

    val muscleGroups = listOf(
        Muscle("Chest", 12, 20, Icons.Default.Cached),
        Muscle("Back", 8, 18, Icons.Default.Share),
        Muscle("Legs", 15, 20, Icons.Default.AccessAlarms),
        Muscle("Shoulders", 6, 12, Icons.Default.Accessibility),
        Muscle("Arms", 18, 15, Icons.Default.SportsHandball),
        Muscle("Core", 4, 10, Icons.Default.AccountCircle)
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // or 3 depending on your design
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(top = 16.dp, bottom = 100.dp)
        ) {

            // Full width items
            item(span = { GridItemSpan(maxLineSpan) }) {
                TopBar()
            }

            item(span = { GridItemSpan(maxLineSpan) }) {
                GreetingSection("Ritesh")
            }

            item(span = { GridItemSpan(maxLineSpan) }) {
                WeeklyGoalCard()
            }

            // Grid items
            items(muscleGroups) { muscle ->
                MuscleGroupCard(muscle)
            }
            item(span = { GridItemSpan(maxLineSpan) }){
                FloatingActionButton()
            }
        }


    }

}

@Composable
fun BoxScope.FloatingActionButton() {
    FloatingActionButton(
        onClick = {},
        modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(end = 20.dp, bottom = 32.dp)
            .size(56.dp),
        shape = CircleShape,
        containerColor = Color.Transparent,
        elevation = FloatingActionButtonDefaults.elevation(0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(AccentPurple, GradientEnd)
                    ),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = "Stats",
                tint = TextPrimary,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen(){
    HomeScreen()
}