package com.example.week5lab_0706012210011.ui.theme.viewmodel

import androidx.compose.ui.input.key.Key.Companion.I
import androidx.lifecycle.ViewModel
import com.example.week5lab_0706012210011.model.Course
import com.example.week5lab_0706012210011.model.GameUIState
import com.example.week5lab_0706012210011.model.IPKUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class IPKCalculatorViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(IPKUIState())
    val uiState: StateFlow<IPKUIState> = _uiState.asStateFlow()


    fun CalculateSKS_and_IPK() {
        var SKS = 0
        var IPK = 0.0
        for (course in _uiState.value.CourseList) {
            IPK = ((IPK * SKS) + (course.Score * course.SKS)) / (SKS + course.SKS)
            SKS += course.SKS
        }
        _uiState.update{currentState ->
            currentState.copy(
                total_SKS = SKS,
                total_IPK = IPK
            )
        }
    }

    fun addCourse(input_name: String, input_sks: Int, input_score: Double) {
        _uiState.value.CourseList.add(Course(input_name, input_sks, input_score))
        CalculateSKS_and_IPK()
    }

    fun deleteCourse(course_todelete: Course) {
        _uiState.value.CourseList.remove(course_todelete)
        CalculateSKS_and_IPK()
    }
}