package com.example.volumetric.presentation.composables.home

import androidx.compose.foundation.background
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volumetric.ui.theme.AccentBlue
import com.example.volumetric.ui.theme.AccentPurple
import com.example.volumetric.ui.theme.SurfaceCard
import com.example.volumetric.ui.theme.TextPrimary
import com.example.volumetric.ui.theme.TextSecondary

@Composable
fun RecentStatsSection() {
    Column {
        Text(text = "Recent Stats", color = TextPrimary, fontSize = 18.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(12.dp))

        // Total Sets - Large card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceCard)
        ) {
            Box(modifier = Modifier.padding(16.dp)) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .background(
                                    brush = Brush.radialGradient(listOf(AccentBlue.copy(0.3f), Color.Transparent)),
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("🎯", fontSize = 14.sp)
                        }

                        // +12% badge
                        Box(
                            modifier = Modifier
                                .background(Color(0xFF1E2A1A), RoundedCornerShape(8.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(text = "+12%", color = Color(0xFF4ADE80), fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "42", color = TextPrimary, fontSize = 32.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Total Sets", color = TextSecondary, fontSize = 13.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Two smaller cards
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Workouts card
            Card(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceCard)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("🗓", fontSize = 14.sp)
                        Box(
                            modifier = Modifier
                                .background(Color(0xFF1A2A1E), RoundedCornerShape(6.dp))
                                .padding(horizontal = 6.dp, vertical = 3.dp)
                        ) {
                            Text(text = "On Track", color = Color(0xFF4ADE80), fontSize = 10.sp, fontWeight = FontWeight.SemiBold)
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "3", color = TextPrimary, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Workouts", color = TextSecondary, fontSize = 13.sp)
                }
            }

            // Last Log card
            Card(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceCard)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("🔥", fontSize = 14.sp)
                        Box(
                            modifier = Modifier
                                .background(Color(0xFF2A1A2E), RoundedCornerShape(6.dp))
                                .padding(horizontal = 6.dp, vertical = 3.dp)
                        ) {
                            Text(text = "Legs", color = AccentPurple, fontSize = 10.sp, fontWeight = FontWeight.SemiBold)
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Yesterday", color = TextPrimary, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Last Log", color = TextSecondary, fontSize = 13.sp)
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewRecentStats(){
    RecentStatsSection()
}