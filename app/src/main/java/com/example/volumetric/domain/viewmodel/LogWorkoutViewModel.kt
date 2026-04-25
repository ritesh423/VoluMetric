package com.example.volumetric.domain.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.volumetric.data.WorkoutDetailDao
import com.example.volumetric.data.WorkoutDetailEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class LogWorkoutViewModel @Inject constructor(
    private val workoutDetailDao: WorkoutDetailDao
) : ViewModel() {
    private val _selectedMuscleGroup = MutableStateFlow<String?>(null)
    val selectedMuscleGroup = _selectedMuscleGroup.asStateFlow()

    private val _selectedExerciseName = MutableStateFlow("")
    val selectedExerciseName = _selectedExerciseName.asStateFlow()

    private val _totalSets = MutableStateFlow("")
    val totalSets = _totalSets.asStateFlow()

    private val _saveStatus = MutableStateFlow<Boolean?>(null)
    val saveStatus = _saveStatus.asStateFlow()

    private var exerciseNameJob: Job? = null
    private var totalSetsJob: Job? = null



    companion object {
        private const val DEBOUNCE_DELAY_MS = 500L
    }

    fun onMuscleGroupSelected(muscleGroup: String) {
        _selectedMuscleGroup.value = muscleGroup
    }

    fun onExerciseNameChanged(exerciseName: String) {
        exerciseNameJob?.cancel()
        exerciseNameJob = viewModelScope.launch {
            delay(DEBOUNCE_DELAY_MS)
            _selectedExerciseName.value = exerciseName
        }
    }

    fun onTotalSetsChanged(totalSets: String) {
        totalSetsJob?.cancel()
        totalSetsJob = viewModelScope.launch {
            delay(DEBOUNCE_DELAY_MS)
            _totalSets.value = totalSets
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun logWorkoutToDB() {
        viewModelScope.launch {
            val currentDate = LocalDate.now()
            val currentWeekOfYear = currentDate.get(java.time.temporal.WeekFields.ISO.weekOfYear())
            val currentYear = currentDate.year
            if (_selectedMuscleGroup.value == null ||
                _selectedExerciseName.value.isBlank() ||
                _totalSets.value.isBlank()) {
                _saveStatus.value = false
                return@launch
            }

            val setsCount = _totalSets.value.toIntOrNull()
            if (setsCount == null || setsCount <= 0) {
                _saveStatus.value = false
                return@launch
            }

            workoutDetailDao.insert(
                WorkoutDetailEntity(
                    muscleGroup = _selectedMuscleGroup.value!!,
                    exerciseName = _selectedExerciseName.value,
                    totalSets = setsCount,
                    createdAt = System.currentTimeMillis(),
                    weekOfYear = currentWeekOfYear,
                    year = currentYear
                )
            )

            _saveStatus.value = true
        }
    }

    fun resetForm() {
        _selectedMuscleGroup.value = null
        _selectedExerciseName.value = ""
        _totalSets.value = ""
        _saveStatus.value = null
    }

    fun clearSaveStatus() {
        _saveStatus.value = null
    }
}