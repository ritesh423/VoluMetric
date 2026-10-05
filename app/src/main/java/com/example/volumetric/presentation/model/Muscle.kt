package com.example.volumetric.presentation.model

import androidx.compose.ui.graphics.vector.ImageVector

data class Muscle(
    val name: String,
    val completed: Int = 0,
    val target: Int = 0,
    val icon: ImageVector
)
