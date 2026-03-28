package com.example.volumetric.domain.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.volumetric.data.MuscleGroupWeeklyStats
import com.example.volumetric.data.WorkoutDetailDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.TemporalAdjusters
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class MuscleStatsViewModel @Inject constructor(
    private val workoutDetailDao: WorkoutDetailDao
) : ViewModel() {

    private val _weeklyStats = MutableStateFlow<List<MuscleGroupWeeklyStats>>(emptyList())
    val weeklyStats = _weeklyStats.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        loadWeeklyStats()
    }

    fun loadWeeklyStats() {
        viewModelScope.launch {
            _isLoading.value = true

            // Calculate current week's start (Monday) and end (Sunday)
            val today = LocalDate.now()
            val weekStart = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
            val weekEnd = weekStart.plusDays(7)

            // Convert to milliseconds for the database query
            val weekStartMillis = weekStart.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
            val weekEndMillis = weekEnd.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()

            // Get stats from database
            val stats = workoutDetailDao.getWeeklySetsPerMuscleGroup(weekStartMillis, weekEndMillis)
            _weeklyStats.value = stats

            _isLoading.value = false
        }
    }
}
