package com.example.volumetric.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workoutDetail")
data class WorkoutDetailEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val muscleGroup: String,
    val exerciseName: String,
    val totalSets: Int,
    val createdAt: Long = System.currentTimeMillis(),
    val weekOfYear: Int? = null,
    val year: Int? = null
)
