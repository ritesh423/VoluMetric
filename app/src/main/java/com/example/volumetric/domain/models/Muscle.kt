package com.example.volumetric.domain.models

import androidx.compose.ui.graphics.vector.ImageVector

data class Muscle(
    val name : String,
    val completed : Int,
    val target : Int,
    val icon : ImageVector
)
