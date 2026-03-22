package com.example.volumetric.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WorkoutDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(workoutDetail: WorkoutDetailEntity)

    @Query("""
        SELECT muscleGroup, SUM(totalSets) as totalSets 
        FROM workoutDetail 
        WHERE createdAt >= :weekStartMillis AND createdAt < :weekEndMillis
        GROUP BY muscleGroup
    """)
    suspend fun getWeeklySetsPerMuscleGroup(
        weekStartMillis: Long,
        weekEndMillis: Long
    ): List<MuscleGroupWeeklyStats>

    @Query("""
        SELECT * FROM workoutDetail 
        WHERE createdAt >= :weekStartMillis AND createdAt < :weekEndMillis
        ORDER BY createdAt DESC
    """)
    suspend fun getWorkoutsForWeek(
        weekStartMillis: Long,
        weekEndMillis: Long
    ): List<WorkoutDetailEntity>

    @Query("SELECT * FROM workoutDetail ORDER BY createdAt DESC")
    suspend fun getAllWorkouts(): List<WorkoutDetailEntity>
}