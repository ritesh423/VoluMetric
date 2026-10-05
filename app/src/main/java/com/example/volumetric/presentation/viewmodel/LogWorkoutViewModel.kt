package com.example.volumetric.presentation.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.volumetric.data.repository.WorkoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.Clock
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class LogWorkoutViewModel @Inject constructor(
    private val workoutRepository: WorkoutRepository,
    private val clock: Clock
) : ViewModel() {
    private val _selectedMuscleGroup = MutableStateFlow<String?>(null)
    val selectedMuscleGroup = _selectedMuscleGroup.asStateFlow()

    private val _selectedExerciseName = MutableStateFlow("")
    val selectedExerciseName = _selectedExerciseName.asStateFlow()

    private val _totalSets = MutableStateFlow("")
    val totalSets = _totalSets.asStateFlow()

    private val _saveStatus = MutableStateFlow<Boolean?>(null)
    val saveStatus = _saveStatus.asStateFlow()

    fun onMuscleGroupSelected(muscleGroup: String) {
        _selectedMuscleGroup.value = muscleGroup
    }

    fun onExerciseNameChanged(exerciseName: String) {
        _selectedExerciseName.value = exerciseName
    }

    fun onTotalSetsChanged(totalSets: String) {
        _totalSets.value = totalSets
    }

    fun saveWorkout() {
        val muscleGroup = _selectedMuscleGroup.value
        val exerciseName = _selectedExerciseName.value.trim()
        val setsCount = _totalSets.value.toIntOrNull()

        if (muscleGroup == null || exerciseName.isBlank() || setsCount == null || setsCount <= 0) {
            _saveStatus.value = false
            return
        }

        viewModelScope.launch {
            runCatching {
                workoutRepository.addWorkout(
                    muscleGroup = muscleGroup,
                    exerciseName = exerciseName,
                    totalSets = setsCount,
                    createdAt = clock.millis()
                )
            }.onSuccess {
                _saveStatus.value = true
            }.onFailure {
                _saveStatus.value = false
            }
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
