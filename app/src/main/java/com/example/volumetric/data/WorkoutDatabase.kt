package com.example.volumetric.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 2, entities = [WorkoutDetailEntity::class], exportSchema = true)
abstract class WorkoutDatabase : RoomDatabase() {
    abstract fun workoutDetailDao(): WorkoutDetailDao
}
