package com.example.volumetric.domain.models

data class WorkoutDetail(
    val id: Int,
    val muscleGroup: String,
    val exerciseName: String,
    val totalSets: Int,
    val createdAt: String,
    val weekOfYear: Int? = null,
    val year: Int? = null
)
