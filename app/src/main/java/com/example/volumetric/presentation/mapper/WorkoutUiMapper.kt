package com.example.volumetric.presentation.mapper

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.filled.AccessibilityNew
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.SportsGymnastics
import androidx.compose.material.icons.filled.SportsHandball
import com.example.volumetric.data.MuscleGroupWeeklyStats
import com.example.volumetric.data.WorkoutDetailEntity
import com.example.volumetric.presentation.model.Muscle
import com.example.volumetric.presentation.model.WorkoutDetail
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

val muscleVolumeTarget = mapOf(
    "Chest" to 20,
    "Back" to 20,
    "Legs" to 30,
    "Shoulders" to 14,
    "Arms" to 18,
    "Core" to 10
)

private val muscleIcons = mapOf(
    "Chest" to Icons.Default.FitnessCenter,
    "Back" to Icons.Default.AccessibilityNew,
    "Legs" to Icons.AutoMirrored.Filled.DirectionsRun,
    "Shoulders" to Icons.Default.SportsGymnastics,
    "Arms" to Icons.Default.SportsHandball,
    "Core" to Icons.Default.SelfImprovement
)

val workoutMuscles = muscleVolumeTarget.keys.map { muscleGroup ->
    Muscle(
        name = muscleGroup,
        icon = muscleIcons.getValue(muscleGroup)
    )
}

fun MuscleGroupWeeklyStats.toMuscle() = Muscle(
    name = muscleGroup,
    completed = totalSets,
    target = muscleVolumeTarget[muscleGroup] ?: 0,
    icon = muscleIcons[muscleGroup] ?: Icons.Default.FitnessCenter
)

fun WorkoutDetailEntity.toWorkoutDetail() = WorkoutDetail(
    id = id,
    muscleGroup = muscleGroup,
    exerciseName = exerciseName,
    totalSets = totalSets,
    createdAt = SimpleDateFormat("hh:mm a", Locale.getDefault())
        .format(Date(createdAt))
)
