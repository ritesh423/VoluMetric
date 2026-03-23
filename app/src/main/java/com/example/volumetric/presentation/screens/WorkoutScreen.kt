package com.example.volumetric.presentation.screens

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessAlarms
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AltRoute
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Cached
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.SportsHandball
import androidx.compose.material.icons.filled.TrackChanges
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.volumetric.domain.models.Muscle
import com.example.volumetric.domain.viewmodel.LogWorkoutViewModel
import com.example.volumetric.presentation.composables.home.StartWorkoutButton
import com.example.volumetric.presentation.composables.workout.MuscleSelectionCard
import com.example.volumetric.ui.theme.BackgroundDark
import com.example.volumetric.ui.theme.White

@Composable
fun WorkoutScreen(viewModel: LogWorkoutViewModel = hiltViewModel()) {
    // Collect states from ViewModel
    val selectedMuscleGroup by viewModel.selectedMuscleGroup.collectAsState()
    val saveStatus by viewModel.saveStatus.collectAsState()

    // Local state for text fields (we'll debounce these)
    var exerciseNameText by remember { mutableStateOf("") }
    var totalSetsText by remember { mutableStateOf("") }

    // Snackbar state for showing feedback
    val snackbarHostState = remember { SnackbarHostState() }

    // Debounce effect for exercise name
    LaunchedEffect(exerciseNameText) {
        viewModel.onExerciseNameChanged(exerciseNameText)
    }

    // Debounce effect for total sets
    LaunchedEffect(totalSetsText) {
        viewModel.onTotalSetsChanged(totalSetsText)
    }

    // Handle save status changes (show snackbar and reset form)
    LaunchedEffect(saveStatus) {
        when (saveStatus) {
            true -> {
                snackbarHostState.showSnackbar("Workout saved successfully!")
                // Reset form fields
                exerciseNameText = ""
                totalSetsText = ""
                viewModel.resetForm()
            }
            false -> {
                snackbarHostState.showSnackbar("Please fill all fields correctly")
                viewModel.clearSaveStatus()
            }
            null -> { /* Do nothing */ }
        }
    }

    val muscleGroups = listOf(
        Muscle("Chest", icon = Icons.Default.Cached),
        Muscle("Back", icon = Icons.Default.Share),
        Muscle("Legs", icon = Icons.Default.AccessAlarms),
        Muscle("Shoulders", icon = Icons.Default.Accessibility),
        Muscle("Arms", icon = Icons.Default.SportsHandball),
        Muscle("Core", icon = Icons.Default.AccountCircle)
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
                // Header
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("New Entry", fontSize = 25.sp, fontWeight = FontWeight.Medium, color = White)
                        Spacer(modifier = Modifier.width(10.dp))
                        OutlinedButton(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(contentColor = Color(0xFF2196F3))
                        ) {
                            Text("Active Session")
                        }
                    }
                }

                // Subtitle
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Text("Log your sets to track weekly volume.", fontSize = 14.sp, color = White)
                    Spacer(modifier = Modifier.height(40.dp))
                }

                // Target Muscle Section Header
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.TrackChanges, contentDescription = "Target Muscle Icon")
                        Spacer(modifier = Modifier.width(5.dp))
                        Text("Target Muscle", fontSize = 17.sp, fontWeight = FontWeight.SemiBold, color = White)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }

                // Muscle Selection Cards
                items(muscleGroups) { muscle ->
                    MuscleSelectionCard(
                        muscle = muscle,
                        isSelected = selectedMuscleGroup == muscle.name,
                        onMuscleSelected = { viewModel.onMuscleGroupSelected(it) }
                    )
                }

                item(span = { GridItemSpan(maxLineSpan) }) {
                    Spacer(modifier = Modifier.height(20.dp))
                }

                // Exercise Name Section Header
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.AltRoute, contentDescription = "Exercise Name Icon")
                        Spacer(modifier = Modifier.width(5.dp))
                        Text("Exercise Name", fontSize = 17.sp, fontWeight = FontWeight.SemiBold, color = White)
                    }
                }

                // Exercise Name TextField with debouncing
                item(span = { GridItemSpan(maxLineSpan) }) {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(10.dp)),
                        value = exerciseNameText,
                        onValueChange = { exerciseNameText = it },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0xDA2C2A2A),
                            focusedContainerColor = Color(0xDA2C2A2A),
                            unfocusedTextColor = White,
                            focusedTextColor = White
                        ),
                        placeholder = { Text("eg.. Romanian Deadlift", color = White.copy(alpha = 0.6f)) }
                    )
                }

                item(span = { GridItemSpan(maxLineSpan) }) {
                    Spacer(modifier = Modifier.height(14.dp))
                }

                // Total Sets Section Header
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.AutoAwesome, contentDescription = "Total Sets Icon")
                        Spacer(modifier = Modifier.width(5.dp))
                        Text("Total Sets", fontSize = 17.sp, fontWeight = FontWeight.SemiBold, color = White)
                    }
                }

                // Total Sets TextField with debouncing
                item(span = { GridItemSpan(maxLineSpan) }) {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(10.dp)),
                        value = totalSetsText,
                        onValueChange = { newValue ->
                            // Only allow numeric input
                            if (newValue.isEmpty() || newValue.all { it.isDigit() }) {
                                totalSetsText = newValue
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Number
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0xDA2C2A2A),
                            focusedContainerColor = Color(0xDA2C2A2A),
                            unfocusedTextColor = White,
                            focusedTextColor = White
                        ),
                        placeholder = { Text("No of Sets eg.. 3", color = White.copy(alpha = 0.6f)) }
                    )
                }

                item(span = { GridItemSpan(maxLineSpan) }) {
                    Spacer(modifier = Modifier.height(14.dp))
                }

                // Save Button
                item(span = { GridItemSpan(maxLineSpan) }) {
                    StartWorkoutButton(
                        buttonText = "Save Workout",
                        onClick = { viewModel.logWorkoutToDB() }
                    )
                }
            }
        }

        // Snackbar for feedback
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        ) { data ->
            Snackbar(
                snackbarData = data,
                containerColor = if (saveStatus == true) Color(0xFF3FE1B0) else Color(0xFFE53935),
                contentColor = Color.White
            )
        }
    }
}

@Preview
@Composable
fun WorkoutScreenPreview() {
    WorkoutScreen()
}