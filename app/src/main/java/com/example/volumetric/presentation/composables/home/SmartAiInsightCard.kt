package com.example.volumetric.presentation.composables.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volumetric.ui.theme.AccentPurple
import com.example.volumetric.ui.theme.SurfaceCard
import com.example.volumetric.ui.theme.SurfaceDark
import com.example.volumetric.ui.theme.TextPrimary
import com.example.volumetric.ui.theme.TextSecondary

@Composable
fun SmartAiInsightCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceCard),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "⚡", fontSize = 14.sp)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "SMART AI INSIGHT",
                        color = TextSecondary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                }
                Box(
                    modifier = Modifier
                        .size(22.dp)
                        .background(SurfaceDark, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "i", color = TextSecondary, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Recommended Today:",
                color = TextSecondary,
                fontSize = 13.sp
            )

            Text(
                text = "Back & Biceps",
                color = TextPrimary,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))
            Divider(color = Color(0xFF2A2845), thickness = 1.dp)
            Spacer(modifier = Modifier.height(12.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .background(
                            brush = Brush.linearGradient(listOf(Color(0xFFE8A87C), Color(0xFFE8916B))),
                            shape = CircleShape
                        )
                )
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .offset(x = (-8).dp)
                        .background(
                            brush = Brush.linearGradient(listOf(AccentPurple, Color(0xFFEC4899))),
                            shape = CircleShape
                        )
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = buildAnnotatedString {
                        append("Only ")
                        withStyle(SpanStyle(color = TextPrimary, fontWeight = FontWeight.Bold)) { append("6 of 15") }
                        withStyle(SpanStyle(color = TextSecondary)) { append(" weekly sets\ncompleted for Back.") }
                    },
                    color = TextSecondary,
                    fontSize = 13.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Divider(color = Color(0xFF2A2845), thickness = 1.dp)

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "View Muscle Analysis",
                    color = TextSecondary,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(text = "›", color = TextSecondary, fontSize = 18.sp)
            }
        }
    }
}

@Preview
@Composable
fun PreviewInsightCard(){
    SmartAiInsightCard()
}