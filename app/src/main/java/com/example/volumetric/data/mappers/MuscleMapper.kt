package com.example.volumetric.data.mappers

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessAlarms
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Cached
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.SportsHandball
import com.example.volumetric.data.MuscleGroupWeeklyStats
import com.example.volumetric.domain.models.Muscle


val muscleIcons = mapOf(
    "Chest" to Icons.Default.Cached,
    "Back" to Icons.Default.Share,
    "Legs" to Icons.Default.AccessAlarms,
    "Shoulders" to Icons.Default.Accessibility,
    "Arms" to Icons.Default.SportsHandball,
    "Core" to Icons.Default.AccountCircle
)

val muscleVolumeTarget = mapOf(
    "Chest" to 20,
    "Back" to 20,
    "Legs" to 30,
    "Shoulders" to 14,
    "Arms" to 18,
    "Core" to 10
)

fun MuscleGroupWeeklyStats.toMuscle() = Muscle(
    name = this.muscleGroup,
    completed = this.totalSets,
    target = muscleVolumeTarget[this.muscleGroup] ?: 0,
    icon = muscleIcons[this.muscleGroup] ?: Icons.Default.Cached
)