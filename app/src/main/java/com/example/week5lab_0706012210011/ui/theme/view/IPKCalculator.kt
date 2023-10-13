package com.example.week5lab_0706012210011.ui.theme.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week5lab_0706012210011.R
import com.example.week5lab_0706012210011.model.Course
import com.example.week5lab_0706012210011.ui.theme.viewmodel.IPKCalculatorViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IPKCalculatorView(
    IPKCalc_viewmodel: IPKCalculatorViewModel = IPKCalculatorViewModel()
) {
    val variabel_UIState by IPKCalc_viewmodel.uiState.collectAsState()

    var input_SKS by rememberSaveable { mutableStateOf("") }
    var input_Score by rememberSaveable { mutableStateOf("") }
    var input_Name by rememberSaveable { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp)
    ) {
        item(content = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Courses",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Image(
                        painter = painterResource(id = R.drawable.logo_uc),
                        contentDescription = "Logo UC",
                        modifier = Modifier.size(50.dp),
                        contentScale = ContentScale.Crop
                    )
                }
                Text(
                    text = "Total SKS : ${variabel_UIState.total_SKS}",
                    fontSize = 14.sp,
                )
                Text(
                    text = "IPK: ${variabel_UIState.total_IPK}",
                    fontSize = 14.sp,
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = input_SKS,
                        onValueChange = { input_SKS = it },
                        label = { Text(text = "Input SKS") },
                        modifier = Modifier.weight(1f),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                        ),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color.White,
                            focusedBorderColor = Color.Blue,
                            unfocusedBorderColor = Color.Black
                        ),
                        shape = RoundedCornerShape(4.dp),
                    )
                    OutlinedTextField(
                        value = input_Score,
                        onValueChange = { input_Score = it },
                        label = { Text(text = "Input score") },
                        modifier = Modifier.weight(1f),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                        ),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color.White,
                            focusedBorderColor = Color.Blue,
                            unfocusedBorderColor = Color.Black
                        ),
                        shape = RoundedCornerShape(4.dp),
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = input_Name,
                        onValueChange = { input_Name = it },
                        label = { Text(text = "Input course name") },
                        modifier = Modifier.weight(3f),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                        ),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color.White,
                            focusedBorderColor = Color.Blue,
                            unfocusedBorderColor = Color.Black
                        ),
                        shape = RoundedCornerShape(4.dp),
                    )

                    Button(
                        onClick = {
                            IPKCalc_viewmodel.addCourse(
                                input_Name,
                                input_SKS.toIntOrNull() ?: 0,
                                input_Score.toDoubleOrNull() ?: 0.0
                            )
                        },
                        modifier = Modifier
                            .weight(1f)
                            .padding(top = 8.dp),
                        colors = ButtonDefaults.buttonColors(Color.Blue),
                        enabled = input_SKS != "" && (input_SKS.toIntOrNull()
                            ?: 0) != 0 && (input_SKS.toIntOrNull()
                            ?: 0) > 0 && input_Name != "" && input_Score != "" && (input_Score.toDoubleOrNull()
                            ?: 0.0) != 0.0 && (input_Score.toDoubleOrNull()
                            ?: 0.0) > 0 && (input_Score.toDoubleOrNull() ?: 0.0) <= 4.0
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Add Course",
                            modifier = Modifier.padding(vertical = 10.dp)
                        )
                    }
                }
                if (variabel_UIState.CourseList.size == 0) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                            .padding(top = 16.dp)
                            .background(color = Color(0xFFED8639), RoundedCornerShape(16.dp))
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "No course assigned",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        })

        items(variabel_UIState.CourseList) { course: Course ->
            CourseCard(IPKCalc_viewmodel, course)
        }
    }
}

@Composable
fun CourseCard(
    IPKCalc_viewmodel: IPKCalculatorViewModel, course: Course
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(Color(0xFFF9ECE0))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(3f)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Name: ${course.name}", fontSize = 16.sp, fontWeight = FontWeight.Bold
                )
                Text(
                    text = "SKS: ${course.SKS}",
                    fontSize = 16.sp,
                )
                Text(
                    text = "Score: ${course.Score}",
                    fontSize = 16.sp,
                )
            }

            Button(
                onClick = {
                    IPKCalc_viewmodel.deleteCourse(course)
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(Color(0xFFF9ECE0))
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete Course",
                    tint = Color.Red
                )
            }
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun IPKCalculatorPreview() {
    IPKCalculatorView()
}