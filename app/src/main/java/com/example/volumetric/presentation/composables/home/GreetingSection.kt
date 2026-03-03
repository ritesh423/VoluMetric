package com.example.volumetric.presentation.composables.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volumetric.ui.theme.AccentBlue
import com.example.volumetric.ui.theme.TextPrimary
import com.example.volumetric.ui.theme.TextSecondary

@Composable
fun GreetingSection(userName: String) {
    Column {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        color = TextPrimary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
                    )
                ) {
                    append("Good Morning, ")
                }
                withStyle(
                    SpanStyle(
                        color = AccentBlue,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        fontStyle = FontStyle.Italic
                    )
                ) {
                    append(userName)
                }
            }
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Thursday, October 24",
            color = TextSecondary,
            fontSize = 14.sp
        )
    }
}

@Preview
@Composable
fun PreviewGreetingSection(){
    GreetingSection("Ritesh")
}