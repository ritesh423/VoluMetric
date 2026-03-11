package com.example.volumetric.presentation.navigation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.TrackChanges
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.DirectionsRun
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.TrackChanges
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState


private val NavBarBg = Color(0xFF12102A)
private val NavBarBorder = Color(0xFF2A2845)
private val AccentBlue = Color(0xFF5B8DEF)
private val AccentPurple = Color(0xFFA855F7)
private val TextSecondary = Color(0xFF666680)
private val TextPrimary = Color(0xFFFFFFFF)


sealed class BottomNavDestination(
    val route: String,
    val label: String,
    val iconRes: ImageVector,
    val iconSelectedRes: ImageVector
) {
    object Home : BottomNavDestination(
        route = "home",
        label = "Home",
        iconRes = Icons.Outlined.Home,
        iconSelectedRes = Icons.Filled.Home
    )

    object Workout : BottomNavDestination(
        route = "workout",
        label = "Workout",
        iconRes = Icons.Outlined.DirectionsRun,
        iconSelectedRes = Icons.Filled.DirectionsRun
    )

    object Progress : BottomNavDestination(
        route = "progress",
        label = "Progress",
        iconRes = Icons.Outlined.TrackChanges,
        iconSelectedRes = Icons.Filled.TrackChanges
    )

    object Profile : BottomNavDestination(
        route = "profile",
        label = "Profile",
        iconRes = Icons.Outlined.AccountCircle,
        iconSelectedRes = Icons.Filled.AccountCircle
    )
}

val bottomNavItems = listOf(
    BottomNavDestination.Home,
    BottomNavDestination.Workout,
    BottomNavDestination.Progress,
    BottomNavDestination.Profile
)


@Composable
fun VoluMetricBottomNav(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(28.dp))
                .background(NavBarBg)
                .padding(horizontal = 8.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            bottomNavItems.forEach { destination ->
                val isSelected = currentDestination?.hierarchy?.any {
                    it.route == destination.route
                } == true

                NavBarItem(
                    destination = destination,
                    isSelected = isSelected,
                    onClick = {
                        navController.navigate(destination.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}



@Composable
private fun NavBarItem(
    destination: BottomNavDestination,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val scale by animateFloatAsState(
        targetValue = if (isSelected) 1.1f else 1f,
        animationSpec = tween(200),
        label = "scale"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .scale(scale)
            .clip(RoundedCornerShape(16.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .padding(horizontal = 14.dp, vertical = 6.dp)
    ) {

        Box(contentAlignment = Alignment.Center) {
            if (isSelected) {
                Box(
                    modifier = Modifier
                        .size(width = 48.dp, height = 32.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            Brush.linearGradient(
                                colors = listOf(
                                    AccentBlue.copy(alpha = 0.25f),
                                    AccentPurple.copy(alpha = 0.25f)
                                )
                            )
                        )
                )
            }

            Icon(
                imageVector = if (isSelected) destination.iconSelectedRes else destination.iconRes,
                contentDescription = destination.label,
                tint = if (isSelected) AccentBlue else TextSecondary,
                modifier = Modifier.size(22.dp)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        if (isSelected) {
            Text(
                text = destination.label,
                color = AccentBlue,
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 0.3.sp
            )
        } else {
            Spacer(modifier = Modifier.height(14.dp))
        }

        Box(
            modifier = Modifier
                .size(if (isSelected) 4.dp else 0.dp)
                .background(
                    brush = Brush.linearGradient(listOf(AccentBlue, AccentPurple)),
                    shape = CircleShape
                )
        )
    }
}