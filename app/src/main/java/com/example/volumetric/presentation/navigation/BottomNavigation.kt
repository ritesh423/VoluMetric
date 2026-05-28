package com.example.volumetric.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volumetric.presentation.screens.HistoryScreen
import com.example.volumetric.presentation.screens.HomeScreen
import com.example.volumetric.presentation.screens.WorkoutScreen
import com.example.volumetric.ui.theme.BrutalistOrange
import com.example.volumetric.ui.theme.BrutalistYellow
import com.example.volumetric.ui.theme.InkBlack
import com.example.volumetric.ui.theme.MuteGrey
import com.example.volumetric.ui.theme.PaperBg
import com.example.volumetric.ui.theme.PaperWhite
import com.example.volumetric.ui.theme.brutalistShadow

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BottomNavigation() {
    val navItemList = listOf(
        NavItem("Home", Icons.Default.Home),
        NavItem("Log", Icons.Default.Add),
        NavItem("History", Icons.Default.History)
    )

    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = PaperBg,
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(78.dp)
                    .background(PaperWhite)
                    .border(width = 2.dp, color = InkBlack),
                verticalAlignment = Alignment.CenterVertically
            ) {
                navItemList.forEachIndexed { index, item ->
                    val isSelected = selectedIndex == index
                    val isMiddle = index == 1

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .noRippleClickable { selectedIndex = index },
                        contentAlignment = Alignment.Center
                    ) {
                        if (isMiddle) {
                            Box(
                                modifier = Modifier
                                    .size(56.dp)
                                    .brutalistShadow(dx = 4.dp, dy = 4.dp)
                                    .background(BrutalistOrange)
                                    .border(width = 2.dp, color = InkBlack),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = item.bottomBarIcon,
                                    contentDescription = item.label,
                                    tint = InkBlack,
                                    modifier = Modifier.size(28.dp)
                                )
                            }
                        } else {
                            NavTabContent(
                                icon = item.bottomBarIcon,
                                label = item.label,
                                isSelected = isSelected
                            )
                        }
                    }
                }
            }
        },
    ) { innerPadding ->
        ContentScreen(
            modifier = Modifier.padding(innerPadding),
            selectedIndex = selectedIndex,
            onSelectTab = { selectedIndex = it }
        )
    }
}

@Composable
private fun NavTabContent(
    icon: ImageVector,
    label: String,
    isSelected: Boolean
) {
    if (isSelected) {
        Box(
            modifier = Modifier
                .brutalistShadow(dx = 3.dp, dy = 3.dp)
                .background(BrutalistYellow)
                .border(width = 2.dp, color = InkBlack)
                .padding(horizontal = 14.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            TabIconLabel(icon = icon, label = label, tint = InkBlack, selected = true)
        }
    } else {
        TabIconLabel(icon = icon, label = label, tint = MuteGrey, selected = false)
    }
}

@Composable
private fun TabIconLabel(
    icon: ImageVector,
    label: String,
    tint: androidx.compose.ui.graphics.Color,
    selected: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = tint,
            modifier = Modifier.size(22.dp)
        )
        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = label.uppercase(),
            color = tint,
            style = androidx.compose.material3.MaterialTheme.typography.labelMedium,
            fontSize = if (selected) 10.sp else 9.sp
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ContentScreen(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    onSelectTab: (Int) -> Unit
) {
    when (selectedIndex) {
        0 -> HomeScreen(onNavigateToLog = { onSelectTab(1) })
        1 -> WorkoutScreen()
        2 -> HistoryScreen()
    }
}

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}

data class NavItem(
    val label: String,
    val bottomBarIcon: ImageVector
)
