package com.example.volumetric.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.SportsHandball
import androidx.compose.material.icons.filled.TrackChanges
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.volumetric.domain.models.Muscle
import com.example.volumetric.domain.viewmodel.LogWorkoutViewModel
import com.example.volumetric.presentation.composables.home.StartWorkoutButton
import com.example.volumetric.presentation.composables.workout.MuscleSelectionCard
import com.example.volumetric.ui.theme.AccentBlue
import com.example.volumetric.ui.theme.AccentPurple
import com.example.volumetric.ui.theme.BackgroundDark
import com.example.volumetric.ui.theme.SurfaceCard
import com.example.volumetric.ui.theme.TextMuted
import com.example.volumetric.ui.theme.TextSecondary
import com.example.volumetric.ui.theme.White

@Composable
fun WorkoutScreen(viewModel: LogWorkoutViewModel = hiltViewModel()) {
    val selectedMuscleGroup by viewModel.selectedMuscleGroup.collectAsState()
    val saveStatus by viewModel.saveStatus.collectAsState()

    var exerciseNameText by remember { mutableStateOf("") }
    var totalSetsText by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(exerciseNameText) {
        viewModel.onExerciseNameChanged(exerciseNameText)
    }

    LaunchedEffect(totalSetsText) {
        viewModel.onTotalSetsChanged(totalSetsText)
    }

    LaunchedEffect(saveStatus) {
        when (saveStatus) {
            true -> {
                snackbarHostState.showSnackbar("Workout saved successfully!")
                exerciseNameText = ""
                totalSetsText = ""
                viewModel.resetForm()
            }
            false -> {
                snackbarHostState.showSnackbar("Please fill all fields correctly")
                viewModel.clearSaveStatus()
            }
            null -> {}
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

    val cardGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF161430),
            Color(0xFF1A1535),
            Color(0xFF1C1240)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(24.dp))
                .background(cardGradient)
        ) {
            LazyVerticalGrid(
                modifier = Modifier.padding(horizontal = 18.dp, vertical = 20.dp),
                columns = GridCells.Fixed(3),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "New Entry",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = White
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Box(
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    brush = Brush.linearGradient(listOf(AccentBlue, AccentPurple)),
                                    shape = RoundedCornerShape(50)
                                )
                                .padding(horizontal = 12.dp, vertical = 4.dp)
                        ) {
                            Text(
                                "Active Session",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium,
                                color = AccentBlue
                            )
                        }
                    }
                }

                item(span = { GridItemSpan(maxLineSpan) }) {
                    Text(
                        "Log your sets to track weekly volume.",
                        fontSize = 13.sp,
                        color = TextSecondary
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item(span = { GridItemSpan(maxLineSpan) }) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.TrackChanges,
                            contentDescription = "Target Muscle Icon",
                            tint = AccentBlue,
                            modifier = Modifier.height(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            "TARGET MUSCLE",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = TextSecondary,
                            letterSpacing = 1.2.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }

                items(muscleGroups) { muscle ->
                    MuscleSelectionCard(
                        muscle = muscle,
                        isSelected = selectedMuscleGroup == muscle.name,
                        onMuscleSelected = { viewModel.onMuscleGroupSelected(it) }
                    )
                }

                item(span = { GridItemSpan(maxLineSpan) }) {
                    Spacer(modifier = Modifier.height(8.dp))
                }

                item(span = { GridItemSpan(maxLineSpan) }) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.AltRoute,
                            contentDescription = "Exercise Name Icon",
                            tint = AccentBlue,
                            modifier = Modifier.height(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            "EXERCISE NAME",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = TextSecondary,
                            letterSpacing = 1.2.sp
                        )
                    }
                }

                item(span = { GridItemSpan(maxLineSpan) }) {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(12.dp)),
                        value = exerciseNameText,
                        onValueChange = { exerciseNameText = it },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = SurfaceCard,
                            focusedContainerColor = SurfaceCard,
                            unfocusedTextColor = White,
                            focusedTextColor = White,
                            unfocusedBorderColor = Color(0xFF2A2850),
                            focusedBorderColor = AccentBlue
                        ),
                        placeholder = {
                            Text(
                                "e.g. Bench Press",
                                color = TextMuted,
                                fontSize = 14.sp
                            )
                        },
                        singleLine = true
                    )
                }

                item(span = { GridItemSpan(maxLineSpan) }) {
                    Spacer(modifier = Modifier.height(8.dp))
                }

                item(span = { GridItemSpan(maxLineSpan) }) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.AutoAwesome,
                            contentDescription = "Total Sets Icon",
                            tint = AccentBlue,
                            modifier = Modifier.height(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            "TOTAL SETS",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = TextSecondary,
                            letterSpacing = 1.2.sp
                        )
                    }
                }

                item(span = { GridItemSpan(maxLineSpan) }) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            modifier = Modifier
                                .width(80.dp)
                                .clip(shape = RoundedCornerShape(12.dp)),
                            value = totalSetsText,
                            onValueChange = { newValue ->
                                if (newValue.isEmpty() || newValue.all { it.isDigit() }) {
                                    totalSetsText = newValue
                                }
                            },
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done,
                                keyboardType = KeyboardType.Number
                            ),
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedContainerColor = SurfaceCard,
                                focusedContainerColor = SurfaceCard,
                                unfocusedTextColor = White,
                                focusedTextColor = White,
                                unfocusedBorderColor = Color(0xFF2A2850),
                                focusedBorderColor = AccentBlue
                            ),
                            placeholder = {
                                Text(
                                    "0",
                                    color = TextMuted,
                                    fontSize = 22.sp,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )
                            },
                            textStyle = TextStyle(
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                color = White
                            ),
                            singleLine = true
                        )
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(12.dp))
                                .background(SurfaceCard)
                                .padding(horizontal = 14.dp, vertical = 12.dp)
                        ) {
                            Text(
                                "Weekly Target: 20",
                                color = White,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            LinearProgressIndicator(
                                progress = { 0.6f },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(4.dp)
                                    .clip(RoundedCornerShape(50)),
                                color = AccentPurple,
                                trackColor = Color(0xFF2A2850)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                "12 of 20 sets completed",
                                color = TextMuted,
                                fontSize = 11.sp
                            )
                        }
                    }
                }

                item(span = { GridItemSpan(maxLineSpan) }) {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item(span = { GridItemSpan(maxLineSpan) }) {
                    StartWorkoutButton(
                        buttonText = "Save Workout",
                        onClick = { viewModel.logWorkoutToDB() },
                        icon = Icons.Default.CheckCircle,
                        useGradient = true
                    )
                }
            }
        }

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
