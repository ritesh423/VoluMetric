package com.example.volumetric.domain.repository

import com.example.volumetric.data.WorkoutDetailDao
import javax.inject.Inject


class WorkoutRepository @Inject constructor(
    private val workoutDetailDao: WorkoutDetailDao
) {

}