package com.example.volumetric.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessAlarms
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Cached
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.SportsHandball
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.volumetric.domain.models.Muscle
import com.example.volumetric.domain.viewmodel.LogWorkoutViewModel
import com.example.volumetric.presentation.composables.home.StartWorkoutButton
import com.example.volumetric.presentation.composables.workout.MuscleSelectionCard
import com.example.volumetric.ui.theme.BrutalistBlue
import com.example.volumetric.ui.theme.BrutalistOrange
import com.example.volumetric.ui.theme.BrutalistPink
import com.example.volumetric.ui.theme.BrutalistYellow
import com.example.volumetric.ui.theme.InkBlack
import com.example.volumetric.ui.theme.MuteGrey
import com.example.volumetric.ui.theme.PaperBg
import com.example.volumetric.ui.theme.PaperWhite
import com.example.volumetric.ui.theme.brutalistEntry
import com.example.volumetric.ui.theme.brutalistShadow

@Composable
fun WorkoutScreen(viewModel: LogWorkoutViewModel = hiltViewModel()) {
    val selectedMuscleGroup by viewModel.selectedMuscleGroup.collectAsState()
    val saveStatus by viewModel.saveStatus.collectAsState()

    var exerciseNameText by remember { mutableStateOf("") }
    var totalSetsText by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(exerciseNameText) {
        viewModel.onExerciseNameChanged(exerciseNameText)
    }

    LaunchedEffect(totalSetsText) {
        viewModel.onTotalSetsChanged(totalSetsText)
    }

    LaunchedEffect(saveStatus) {
        when (saveStatus) {
            true -> {
                snackbarHostState.showSnackbar("Workout saved")
                exerciseNameText = ""
                totalSetsText = ""
                viewModel.resetForm()
            }
            false -> {
                snackbarHostState.showSnackbar("Please fill all fields correctly")
                viewModel.clearSaveStatus()
            }
            null -> {}
        }
    }

    val muscleGroups = listOf(
        Muscle("Chest", icon = Icons.Default.Cached),
        Muscle("Back", icon = Icons.Default.Share),
        Muscle("Legs", icon = Icons.Default.AccessAlarms),
        Muscle("Shoulders", icon = Icons.Default.Accessibility),
        Muscle("Arms", icon = Icons.Default.SportsHandball),
        Muscle("Core", icon = Icons.Default.AccountCircle)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PaperBg)
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(top = 16.dp, bottom = 110.dp)
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                Column(modifier = Modifier.brutalistEntry()) {
                    LogTitle()
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "PICK → NAME → COUNT → SAVE",
                        color = InkBlack,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 1.6.sp
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    SectionLabel("MUSCLE")
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            items(muscleGroups) { muscle ->
                MuscleSelectionCard(
                    muscle = muscle,
                    isSelected = selectedMuscleGroup == muscle.name,
                    onMuscleSelected = { viewModel.onMuscleGroupSelected(it) }
                )
            }

            item(span = { GridItemSpan(maxLineSpan) }) {
                Spacer(modifier = Modifier.height(8.dp))
                SectionLabel("EXERCISE")
                Spacer(modifier = Modifier.height(8.dp))
                BrutalistInput(
                    value = exerciseNameText,
                    onValueChange = { exerciseNameText = it },
                    placeholder = "Barbell Row",
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )
            }

            item(span = { GridItemSpan(maxLineSpan) }) {
                Spacer(modifier = Modifier.height(12.dp))
                SectionLabel("SETS")
                Spacer(modifier = Modifier.height(8.dp))
                SetsField(
                    value = totalSetsText,
                    onValueChange = { newValue ->
                        if (newValue.isEmpty() || newValue.all { it.isDigit() }) {
                            totalSetsText = newValue
                        }
                    }
                )
            }

            item(span = { GridItemSpan(maxLineSpan) }) {
                Spacer(modifier = Modifier.height(20.dp))
                StartWorkoutButton(
                    buttonText = "Save Workout",
                    onClick = { viewModel.logWorkoutToDB() },
                    icon = Icons.Default.CheckCircle
                )
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        ) { data ->
            Box(
                modifier = Modifier
                    .padding(20.dp)
                    .brutalistShadow(dx = 4.dp, dy = 4.dp)
                    .background(if (saveStatus == true) BrutalistYellow else BrutalistPink)
                    .border(width = 2.dp, color = InkBlack)
                    .padding(horizontal = 18.dp, vertical = 14.dp)
            ) {
                Text(
                    text = data.visuals.message.uppercase(),
                    color = InkBlack,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 1.2.sp
                )
            }
        }
    }
}

@Composable
private fun LogTitle() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "LOG A ",
            color = InkBlack,
            fontSize = 44.sp,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = (-2).sp,
            lineHeight = 44.sp
        )
        Box(
            modifier = Modifier
                .rotate(-2f)
                .brutalistShadow(dx = 4.dp, dy = 4.dp)
                .background(BrutalistPink)
                .border(width = 2.dp, color = InkBlack)
                .padding(horizontal = 8.dp, vertical = 0.dp)
        ) {
            Text(
                text = "SET",
                color = InkBlack,
                fontSize = 38.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = (-1).sp,
                lineHeight = 44.sp
            )
        }
    }
}

@Composable
private fun SectionLabel(text: String) {
    Box(
        modifier = Modifier
            .background(InkBlack)
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            color = PaperBg,
            fontSize = 11.sp,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 2.sp
        )
    }
}

@Composable
private fun BrutalistInput(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    textStyle: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = InkBlack
    ),
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .brutalistShadow(dx = 3.dp, dy = 3.dp)
            .background(PaperWhite)
            .border(width = 2.dp, color = InkBlack)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            keyboardOptions = keyboardOptions,
            shape = RectangleShape,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = PaperWhite,
                focusedContainerColor = PaperWhite,
                unfocusedTextColor = InkBlack,
                focusedTextColor = InkBlack,
                unfocusedBorderColor = androidx.compose.ui.graphics.Color.Transparent,
                focusedBorderColor = androidx.compose.ui.graphics.Color.Transparent,
                cursorColor = InkBlack
            ),
            textStyle = textStyle,
            placeholder = {
                Text(
                    text = placeholder,
                    color = MuteGrey,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            singleLine = true
        )
    }
}

@Composable
private fun SetsField(
    value: String,
    onValueChange: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .width(110.dp)
                .height(72.dp)
                .brutalistShadow(dx = 4.dp, dy = 4.dp)
                .background(BrutalistYellow)
                .border(width = 2.5.dp, color = InkBlack)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxSize(),
                value = value,
                onValueChange = onValueChange,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                shape = RectangleShape,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = BrutalistYellow,
                    focusedContainerColor = BrutalistYellow,
                    unfocusedTextColor = InkBlack,
                    focusedTextColor = InkBlack,
                    unfocusedBorderColor = androidx.compose.ui.graphics.Color.Transparent,
                    focusedBorderColor = androidx.compose.ui.graphics.Color.Transparent,
                    cursorColor = InkBlack
                ),
                placeholder = {
                    Text(
                        text = "0",
                        color = InkBlack.copy(alpha = 0.4f),
                        fontSize = 32.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                textStyle = TextStyle(
                    fontSize = 32.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center,
                    color = InkBlack,
                    letterSpacing = (-1).sp
                ),
                singleLine = true
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .brutalistShadow(dx = 3.dp, dy = 3.dp)
                .background(PaperWhite)
                .border(width = 2.dp, color = InkBlack)
                .padding(horizontal = 14.dp, vertical = 10.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Column {
                Text(
                    text = "SETS THIS WORKOUT",
                    color = MuteGrey,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 1.4.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Type the number on the left.",
                    color = InkBlack,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview
@Composable
fun WorkoutScreenPreview() {
    WorkoutScreen()
}
