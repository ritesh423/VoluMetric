package com.example.volumetric

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.core.view.WindowCompat
import com.example.volumetric.presentation.navigation.BottomNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
//            val systemUiController = rememberSystemUiController()
//            SideEffect {
//                systemUiController.setSystemBarsColor(
//                    color = Color(0xFF0D0B1E),
//                    darkIcons = false
//                )
//            }

            BottomNavigation()
        }
    }
}
