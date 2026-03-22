package com.example.volumetric.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.volumetric.data.WorkoutDetailDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
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


    fun logWorkouttoDB() {
        viewModelScope.launch {

        }
    }
}