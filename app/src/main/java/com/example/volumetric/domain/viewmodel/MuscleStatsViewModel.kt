package com.example.volumetric.domain.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.volumetric.data.MuscleGroupWeeklyStats
import com.example.volumetric.data.WorkoutDetailDao
import com.example.volumetric.data.WorkoutDetailEntity
import com.example.volumetric.data.mappers.muscleVolumeTarget
import com.example.volumetric.domain.models.DateBucket
import com.example.volumetric.domain.models.HistoryFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.TemporalAdjusters
import java.time.temporal.WeekFields
import java.util.Locale
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class MuscleStatsViewModel @Inject constructor(
    private val workoutDetailDao: WorkoutDetailDao
) : ViewModel() {

    private val _weeklyStats = MutableStateFlow<List<MuscleGroupWeeklyStats>>(emptyList())
    val weeklyStats = _weeklyStats.asStateFlow()

    private val _allStats = MutableStateFlow<List<WorkoutDetailEntity>>(emptyList())
    val allStats = _allStats.asStateFlow()


    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()


    val totalWorkouts = _allStats
        .map { it.size }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 0)

    val thisWeekCount = _allStats
        .map { workouts ->
            val today = LocalDate.now()
            val currentWeek = today.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear())
            val currentYear = today.year
            workouts.count { it.weekOfYear == currentWeek && it.year == currentYear }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 0)

    // ---- Weekly metrics for the Home screen ----

    val weeklySetsCompleted = _weeklyStats
        .map { stats -> stats.sumOf { it.totalSets } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 0)

    val weeklySetsTarget = muscleVolumeTarget.values.sum() // constant total across all muscles

    val avgIntensity = _weeklyStats
        .map { stats ->
            val perMuscleCompletion = muscleVolumeTarget.map { (muscle, target) ->
                val done = stats.firstOrNull { it.muscleGroup == muscle }?.totalSets ?: 0
                if (target == 0) 0f else (done.toFloat() / target).coerceAtMost(1f)
            }
            if (perMuscleCompletion.isEmpty()) 0
            else (perMuscleCompletion.average() * 100).toInt()
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 0)

    val mostTrainedMuscle = _allStats
        .map { workouts ->
            workouts
                .groupBy { it.muscleGroup }
                .mapValues { entry -> entry.value.sumOf { it.totalSets } }
                .maxByOrNull { it.value }
                ?.key
                ?: "—"
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), "—")

    // ---- History screen filter state ----

    private val _selectedFilter = MutableStateFlow(HistoryFilter.ALL)
    val selectedFilter = _selectedFilter.asStateFlow()

    val filteredWorkouts = combine(_allStats, _selectedFilter) { workouts, filter ->
        when (filter) {
            HistoryFilter.ALL -> workouts
            HistoryFilter.THIS_WEEK -> {
                val today = LocalDate.now()
                val week = today.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear())
                val year = today.year
                workouts.filter { it.weekOfYear == week && it.year == year }
            }
            HistoryFilter.LAST_WEEK -> {
                val lastWeekDate = LocalDate.now().minusWeeks(1)
                val week = lastWeekDate.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear())
                val year = lastWeekDate.year
                workouts.filter { it.weekOfYear == week && it.year == year }
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun onFilterSelected(filter: HistoryFilter) {
        _selectedFilter.value = filter
    }

    /**
     * Filtered workouts grouped by date bucket (Today / Yesterday / Earlier).
     * Insertion order is preserved, so newest dates appear first because
     * `getAllWorkouts()` returns rows ordered by createdAt DESC.
     */
    val groupedWorkouts = filteredWorkouts
        .map { workouts ->
            workouts.groupBy { DateBucket.fromEpochMillis(it.createdAt) }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyMap())

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
            val weekStartMillis =
                weekStart.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
            val weekEndMillis =
                weekEnd.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()



            // Collect weekly stats Flow
            viewModelScope.launch {
                workoutDetailDao.getWeeklySetsPerMuscleGroup(weekStartMillis, weekEndMillis)
                    .collect { stats ->
                        _weeklyStats.value = stats
                    }
            }

            // Collect all workouts Flow
            viewModelScope.launch {
                workoutDetailDao.getAllWorkouts()
                    .collect { allWorkouts ->
                        _allStats.value = allWorkouts
                    }
            }

            _isLoading.value = false
        }
    }
}
