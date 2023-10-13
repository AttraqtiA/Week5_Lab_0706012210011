package com.example.week5lab_0706012210011.ui.theme.viewmodel

import androidx.lifecycle.ViewModel
import com.example.week5lab_0706012210011.model.GameUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

//sealed interface GuessNumberUIState {
//    object Error: GuessNumberUIState
//    object Loading: GuessNumberUIState
//}

class GuessNumberViewModel: ViewModel() { // HARAP DIINGAT ViewModel KAPITAAL

    // START DARI 3 ATTEMPTS

    private val _uiState = MutableStateFlow(GameUIState()) //dipake di viewmodel, diupdate
    val uiState: StateFlow<GameUIState> = _uiState.asStateFlow() //buat panggil

    fun newNumber() {
        _uiState.update {currentState ->
            currentState.copy(
                answer_key = Random.nextInt(1, 11)
            )
        }
    }

    init {
        newNumber()
    }

    fun CheckInput(answer: Int): Boolean {
        _uiState.update { currentState ->
            currentState.copy(
                attempt = if (currentState.answer_key == answer) currentState.attempt else currentState.attempt - 1,
                score = if (currentState.answer_key == answer) currentState.score + 1 else currentState.score,
            )
        }

        newNumber()

        if (_uiState.value.attempt == 0 || _uiState.value.score == 3) {
            return true
        }

        return false
    }

    fun RestartGame() {
        _uiState.update { currentState ->
            currentState.copy(
                attempt = 0,
                score = 0,
            )
        }

        newNumber()
    }
}