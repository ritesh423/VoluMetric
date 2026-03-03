package com.example.volumetric

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.volumetric.presentation.screens.HomeScreen
import com.example.volumetric.ui.theme.VoluMetricTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VoluMetricTheme {
                HomeScreen()
            }
        }
    }
}
