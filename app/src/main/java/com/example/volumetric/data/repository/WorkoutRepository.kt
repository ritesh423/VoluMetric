package com.example.volumetric.data.repository

import com.example.volumetric.data.MuscleGroupWeeklyStats
import com.example.volumetric.data.WorkoutDetailDao
import com.example.volumetric.data.WorkoutDetailEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * The data boundary used by the presentation ViewModels.
 *
 * ViewModels describe the data they need; this repository owns the Room DAO.
 */
@Singleton
class WorkoutRepository @Inject constructor(
    private val workoutDetailDao: WorkoutDetailDao
) {
    suspend fun addWorkout(
        muscleGroup: String,
        exerciseName: String,
        totalSets: Int,
        createdAt: Long
    ) {
        workoutDetailDao.insert(
            WorkoutDetailEntity(
                muscleGroup = muscleGroup,
                exerciseName = exerciseName,
                totalSets = totalSets,
                createdAt = createdAt
            )
        )
    }

    fun observeAllWorkouts(): Flow<List<WorkoutDetailEntity>> =
        workoutDetailDao.getAllWorkouts()

    fun observeWorkouts(
        startMillis: Long,
        endMillis: Long
    ): Flow<List<WorkoutDetailEntity>> =
        workoutDetailDao.getWorkoutsForWeek(startMillis, endMillis)

    fun observeSetsPerMuscleGroup(
        startMillis: Long,
        endMillis: Long
    ): Flow<List<MuscleGroupWeeklyStats>> =
        workoutDetailDao.getWeeklySetsPerMuscleGroup(startMillis, endMillis)
}
