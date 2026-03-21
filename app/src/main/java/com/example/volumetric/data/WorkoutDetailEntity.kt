package com.example.volumetric.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workoutDetail")
data class WorkoutDetailEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val muscleGroup : String,
    val exerciseName : String,
    val totalSets : Int
)
