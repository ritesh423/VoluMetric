package com.example.volumetric.presentation.composables.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volumetric.ui.theme.BrutalistBlue
import com.example.volumetric.ui.theme.BrutalistYellow
import com.example.volumetric.ui.theme.InkBlack
import com.example.volumetric.ui.theme.PaperWhite
import com.example.volumetric.ui.theme.brutalistShadow

@Composable
fun StartWorkoutButton(
    buttonText: String,
    onClick: () -> Unit = {},
    icon: ImageVector = Icons.Default.PlayArrow,
    backgroundColor: Color = BrutalistBlue,
    contentColor: Color = PaperWhite
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .brutalistShadow(dx = 5.dp, dy = 5.dp)
            .border(width = 2.5.dp, color = InkBlack),
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = buttonText.uppercase(),
                    color = contentColor,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 1.5.sp
                )
                Spacer(modifier = Modifier.width(10.dp))
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .background(BrutalistYellow)
                        .border(width = 2.dp, color = InkBlack),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = InkBlack,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewWorkoutButton() {
    StartWorkoutButton(buttonText = "LOG WORKOUT", onClick = {})
}

@Preview
@Composable
fun PreviewSaveButton() {
    StartWorkoutButton(
        buttonText = "SAVE WORKOUT",
        onClick = {},
        icon = Icons.Default.CheckCircle
    )
}
