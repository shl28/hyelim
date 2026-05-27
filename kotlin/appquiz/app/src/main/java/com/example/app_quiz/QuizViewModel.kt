package com.example.app_quiz

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// 화면 상태 저장 클래스
data class QuizUiState(
    val currentIndex: Int = 0,
    val score: Int = 0,
    val isFinished: Boolean = false,
    val lastAnswerCorrect: Boolean? = null,
){
    val currentQuestion: QuizQuestion?
        get() = kotlinQuizQuestions.getOrNull(currentIndex)

    val totalQuestions: Int = kotlinQuizQuestions.size
}

class QuizViewModel : ViewModel(){
    private val _uiState = MutableStateFlow(QuizUiState())
    val uiState : StateFlow<QuizUiState> = _uiState.asStateFlow() // UI 읽기 전용 상태, 수정불가
    // MutableStateFlow : 상태(state) 저장, 변경되면 UI에 자동으로 알려줌 -> 화면 갱신

    fun submitAnswer(choiceIndex: Int) {
        val state = _uiState.value
        if (state.isFinished || state.lastAnswerCorrect != null) return

        val question = state.currentQuestion ?: return
        // 현재의 문제를 가져옴 ?: return -> null 이면 함수 종료

        val correct = choiceIndex == question.correctIndex
        // 선택한 답, 정답 비교 -> 같으면 true

        _uiState.update { // 현재 상태 변경 -> UI 자동 갱신
            it.copy( // 기존 상태 복사 - 일부만 변경
                lastAnswerCorrect = correct, // 정답|오답 UI 표시용
                score = if (correct) it.score + 1 else it.score, // 정답이면 점수 +1, 틀리면 그대로
            )
        }
    }

    fun nextQuestion() {
        _uiState.update { state ->
            if (state.currentIndex < kotlinQuizQuestions.lastIndex) {
                state.copy( // 다음 문제로 이동
                    currentIndex = state.currentIndex + 1,
                    lastAnswerCorrect = null,
                )
            } else {
                // 현재 인덱스 번호가 마지막 인덱스 번호와 같으면
                state.copy(isFinished = true, lastAnswerCorrect = null)
            }
        }
    }

    fun restart(){ // 새로 시작하면 UI를 초기 상태로 , QuizUiState() 초기 상태 객체 생성
        _uiState.value = QuizUiState()
    }
}