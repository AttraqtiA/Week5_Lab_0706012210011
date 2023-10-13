package com.example.week5lab_0706012210011.ui.theme.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week5lab_0706012210011.ui.theme.viewmodel.GuessNumberViewModel
import java.lang.Integer.parseInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuessNumberView(
    GNviewmodel: GuessNumberViewModel = GuessNumberViewModel() // ngefetch functionnya
) {
    var input by rememberSaveable { mutableStateOf("") }
    var dialogshow by rememberSaveable { mutableStateOf(false) }

    val GN_UIState by GNviewmodel.uiState.collectAsState() // buat ngefetch variabel" nya model

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(horizontal = 32.dp)
    ) {
        Text(
            text = "Guess The Number",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(Color.LightGray)
        ) {
            Column(
                modifier = Modifier
                    .background(
                        color = Color.LightGray, shape = RoundedCornerShape(16.dp)
                    )
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Life : ${GN_UIState.attempt}",
                        color = Color.White,
                        modifier = Modifier
                            .background(
                                color = Color.Blue, shape = RoundedCornerShape(8.dp)
                            )
                            .padding(4.dp)
                    )
                }
                Text(
                    text = "${GN_UIState.answer_key}",
                    fontSize = 54.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = "From 1 to 10, Guess The Number!",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "Score: ${GN_UIState.score}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )

                OutlinedTextField(
                    value = input,
                    onValueChange = { input = it },
                    label = { Text(text = "Enter your guess!") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color.White,
                        focusedBorderColor = Color.Blue,
                        unfocusedBorderColor = Color.Blue
                    ),
                    shape = RoundedCornerShape(16.dp),
                )
            }
        }

        Button(
            onClick = {
                dialogshow = GNviewmodel.CheckInput(parseInt(input))
            },
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Blue)
        ) {
            Text(
                text = "Submit",
                fontSize = 20.sp,
                modifier = Modifier.padding(8.dp),
            )
        }
    }

    if (dialogshow) {
        AlertDialog(onDismissRequest = {
            dialogshow = false
            GNviewmodel.RestartGame()
        }, title = {
            Text(
                text = "Game Over!",
            )

        }, text = {

            Text(
                text = "You scored: ${GN_UIState.score}\nThank you for playing this useless game"
            )

        }, confirmButton = {
            Button(onClick = {
                System.exit(0)
            }) {
                Text("Exit")
            }
            Button(onClick = {
                dialogshow = false
                GNviewmodel.RestartGame()
            }) {
                Text("Play Again")
            }
        })
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun GuessNumberPreview() {
    GuessNumberView()
}