package com.example.volumetric.presentation.composables.workout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessAlarms
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Cached
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.SportsHandball
import androidx.compose.material.icons.filled.TrackChanges
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volumetric.domain.models.Muscle
import com.example.volumetric.ui.theme.BackgroundDark
import com.example.volumetric.ui.theme.White

@Composable
fun WorkoutScreen() {
    val exerciseName = rememberTextFieldState()

    val muscleGroups = listOf(
        Muscle("Chest", icon = Icons.Default.Cached),
        Muscle("Back", icon =  Icons.Default.Share),
        Muscle("Legs", icon =  Icons.Default.AccessAlarms),
        Muscle("Shoulders",icon = Icons.Default.Accessibility),
        Muscle("Arms", icon =  Icons.Default.SportsHandball),
        Muscle("Core", icon =  Icons.Default.AccountCircle)
    )

    val gradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF1F2A37),
            Color(0xFF1B3145),
            Color(0xFF2A1E3F)
        )
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .padding(20.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(20.dp))
                .background(gradient)

        ) {
            LazyVerticalGrid(
                modifier = Modifier.padding(15.dp),
                columns = GridCells.Fixed(3),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)

                ) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("New Entry", fontSize = 25.sp, fontWeight = FontWeight.Medium, color = White)
                        Spacer(modifier = Modifier.width(10.dp))
                        OutlinedButton(onClick = {}, colors = ButtonDefaults.buttonColors(contentColor = Color(0xFF2196F3))) {
                            Text("Active Session")
                        }
                    }
                }

                item(span = { GridItemSpan(maxLineSpan) }) {
                    Text("Log your sets to track weekly volume.", fontSize = 14.sp, color = White)
                    Spacer(modifier = Modifier.height(40.dp))
                }

                item(span = { GridItemSpan(maxLineSpan) }) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.TrackChanges, contentDescription = "Icon")
                        Spacer(modifier = Modifier.width(5.dp))
                        Text("Target Muscle", fontSize = 17.sp, fontWeight = FontWeight.SemiBold, color = White)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }


                items(muscleGroups){ muscle ->
                    MuscleSelectionCard(muscle)
                }

                item(span = { GridItemSpan(maxLineSpan) }) {
                    Spacer(modifier = Modifier.height(20.dp))
                }

                item(span = { GridItemSpan(maxLineSpan) }) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.TrackChanges, contentDescription = "Icon")
                        Spacer(modifier = Modifier.width(5.dp))
                        Text("Exercise Name", fontSize = 17.sp, fontWeight = FontWeight.SemiBold, color = White)
                    }

                }

                item(span = { GridItemSpan(maxLineSpan) }) {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth()
                            .clip(shape = RoundedCornerShape(10.dp)),
                        state = exerciseName,
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0xDA2C2A2A),
                            focusedContainerColor = Color(0xDA2C2A2A)
                        ),
                        placeholder = { Text("eg.. Romanian Deadlift", color = White) },

                    )
                    Spacer(modifier = Modifier.height(40.dp))
                }

            }
        }

    }
}


@Preview
@Composable
fun WorkoutScreenPreview() {
    WorkoutScreen()
}