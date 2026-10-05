package com.example.volumetric.presentation.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.volumetric.data.MuscleGroupWeeklyStats
import com.example.volumetric.data.WorkoutDetailEntity
import com.example.volumetric.data.repository.WorkoutRepository
import com.example.volumetric.data.time.WeekRange
import com.example.volumetric.presentation.mapper.muscleVolumeTarget
import com.example.volumetric.presentation.model.DateBucket
import com.example.volumetric.presentation.model.HistoryFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.Clock
import java.time.ZoneId
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@OptIn(ExperimentalCoroutinesApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class MuscleStatsViewModel @Inject constructor(
    private val workoutRepository: WorkoutRepository,
    private val clock: Clock,
    private val zoneId: ZoneId
) : ViewModel() {
    private val currentWeek = WeekRange.containing(clock.instant(), zoneId)
    private val lastWeek = currentWeek.previous(zoneId)

    val weeklyStats: StateFlow<List<MuscleGroupWeeklyStats>> =
        workoutRepository.observeSetsPerMuscleGroup(
            currentWeek.startMillis,
            currentWeek.endMillis
        ).stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            emptyList()
        )

    val allStats: StateFlow<List<WorkoutDetailEntity>> =
        workoutRepository.observeAllWorkouts().stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            emptyList()
        )

    val isLoading: StateFlow<Boolean> = combine(weeklyStats, allStats) { _, _ -> false }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), true)

    val totalWorkouts = allStats
        .map { it.size }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 0)

    val thisWeekCount = workoutRepository.observeWorkouts(
        currentWeek.startMillis,
        currentWeek.endMillis
    ).map { it.size }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 0)

    val weeklySetsCompleted = weeklyStats
        .map { stats -> stats.sumOf { it.totalSets } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 0)

    val weeklySetsTarget = muscleVolumeTarget.values.sum()

    val avgIntensity = weeklyStats
        .map { stats ->
            val perMuscleCompletion = muscleVolumeTarget.map { (muscle, target) ->
                val done = stats.firstOrNull { it.muscleGroup == muscle }?.totalSets ?: 0
                if (target == 0) 0f else (done.toFloat() / target).coerceAtMost(1f)
            }
            if (perMuscleCompletion.isEmpty()) 0
            else (perMuscleCompletion.average() * 100).toInt()
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 0)

    val mostTrainedMuscle = allStats
        .map { workouts ->
            workouts
                .groupBy { it.muscleGroup }
                .mapValues { entry -> entry.value.sumOf { it.totalSets } }
                .maxByOrNull { it.value }
                ?.key
                ?: "-"
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), "-")

    private val _selectedFilter = MutableStateFlow(HistoryFilter.ALL)
    val selectedFilter = _selectedFilter.asStateFlow()

    val filteredWorkouts: StateFlow<List<WorkoutDetailEntity>> = selectedFilter
        .flatMapLatest { filter ->
            when (filter) {
                HistoryFilter.ALL -> workoutRepository.observeAllWorkouts()
                HistoryFilter.THIS_WEEK -> workoutRepository.observeWorkouts(
                    currentWeek.startMillis,
                    currentWeek.endMillis
                )
                HistoryFilter.LAST_WEEK -> workoutRepository.observeWorkouts(
                    lastWeek.startMillis,
                    lastWeek.endMillis
                )
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    val groupedWorkouts = filteredWorkouts
        .map { workouts ->
            val today = clock.instant().atZone(zoneId).toLocalDate()
            workouts.groupBy {
                DateBucket.fromEpochMillis(
                    millis = it.createdAt,
                    today = today,
                    zoneId = zoneId
                )
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyMap())

    fun onFilterSelected(filter: HistoryFilter) {
        _selectedFilter.value = filter
    }
}
