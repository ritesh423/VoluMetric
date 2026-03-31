package com.example.volumetric.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

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
    fun getWeeklySetsPerMuscleGroup(
        weekStartMillis: Long,
        weekEndMillis: Long
    ): Flow<List<MuscleGroupWeeklyStats>>

    @Query("""
        SELECT * FROM workoutDetail 
        WHERE createdAt >= :weekStartMillis AND createdAt < :weekEndMillis
        ORDER BY createdAt DESC
    """)
    fun getWorkoutsForWeek(
        weekStartMillis: Long,
        weekEndMillis: Long
    ): Flow<List<WorkoutDetailEntity>>

    @Query("SELECT * FROM workoutDetail ORDER BY createdAt DESC")
    fun getAllWorkouts(): Flow<List<WorkoutDetailEntity>>
}