package com.example.volumetric.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [WorkoutDetailEntity::class], exportSchema = false)
abstract class WorkoutDatabase() : RoomDatabase() {
    abstract fun workoutDetailDao() : WorkoutDetailDao
}