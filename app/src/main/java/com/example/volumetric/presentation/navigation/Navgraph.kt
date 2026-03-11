package com.example.volumetric.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.volumetric.presentation.screens.HomeScreen

private val routesWithoutBottomNav = listOf("exercise_detail/{id}")


@Composable
fun VoluMetricApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomBar = currentRoute !in routesWithoutBottomNav

    Scaffold(
        containerColor = Color(0xFF0D0B1E),
        bottomBar = {
            if (showBottomBar) {
                VoluMetricBottomNav(navController = navController)
            }
        }
    ) { innerPadding ->
        VoluMetricNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}


@Composable
fun VoluMetricNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavDestination.Home.route,
        modifier = modifier
    ) {

        composable(BottomNavDestination.Home.route) {
            HomeScreen(userName = "Ritesh")
        }

        composable(
            BottomNavDestination.Workout.route,
        ) {
            PlaceholderScreen(label = "Workout 👤")
        }


        composable(BottomNavDestination.Progress.route) {
            PlaceholderScreen(label = "Progress 📊")
        }


        composable(BottomNavDestination.Profile.route) {
            PlaceholderScreen(label = "Profile 👤")
        }
    }
}


@Composable
private fun PlaceholderScreen(label: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0D0B1E)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            color = Color(0xFFAAAAAA),
            fontSize = androidx.compose.ui.unit.TextUnit(
                value = 22f,
                type = androidx.compose.ui.unit.TextUnitType.Sp
            )
        )
    }
}