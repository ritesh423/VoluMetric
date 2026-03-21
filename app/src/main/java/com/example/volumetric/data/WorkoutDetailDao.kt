package com.example.volumetric.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface WorkoutDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(workoutDetail : WorkoutDetailEntity)
}