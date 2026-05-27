package com.example.app_quiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app_quiz.ui.theme.AppquizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                QuizApp()
            }
        }
    }
}

@Composable
fun QuizApp(viewModel: QuizViewModel = viewModel()) { // QuizViewModel 생성, 유지
    val state by viewModel.uiState.collectAsState() // stateFlow -> Compose 상태로 변환
    // StateFlow(보관함 - 예: 현재 좋아요 개수, 유저이름 값이 바뀔때마다 신호 보냄)
    // Compose(화면 기억 장치) - state 타입이 변경될때 화면 새로고침
    // collectAsState() : 중간 번역기
    // viewModel에 있는 StateFlow 값이 바뀌어도 Compose UI 바뀌지 않음
    // collectAsState() 통역사가 되어 StateFlow 값이 바뀌면 Compose 가 이해할 수 있는 언어(State)로 변환 즉시 UI 새로고침

    Scaffold { innerPadding -> // 기본화면 + 시스템 바 패딩
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            when {
                state.isFinished -> ResultScreen( // 결과 UI - 점수, 다시풀기
                    score = state.score,
                    total = state.totalQuestions,
                    onRestart = viewModel::restart,
                )
                state.currentQuestion != null -> QuestionScreen( // 퀴즈 UI: 문제, 보기, 다음
                    index = state.currentIndex + 1,
                    total = state.totalQuestions,
                    question = state.currentQuestion!!,
                    feedback = state.lastAnswerCorrect,
                    onAnswer = viewModel::submitAnswer,
                    onNext = viewModel::nextQuestion,
                    canGoNext = state.lastAnswerCorrect != null,
                )
            }
        }
    }
}

@Composable
fun QuestionScreen(
    index : Int,
    total: Int,
    question: QuizQuestion,
    feedback: Boolean?,
    onAnswer: (Int) -> Unit, // 보기 클릭시 (viewModel.submitAnswer)
    onNext: () -> Unit, // 다음 버튼 (viewModel.nextQuestion)
    canGoNext: Boolean, // 다음 버튼 활성화 여부
) {
    Text("문제 $index / $total", style= MaterialTheme.typography.labelLarge)
    Text(question.question, style = MaterialTheme.typography.titleLarge)
    // 앞 question : QuizQuestion 데이터 전체
    // 뒤 question : val question String 문제
    question.choices.forEachIndexed { i, choice -> // forEachIndexed : 보기마다 버튼 하나
        OutlinedButton(
            onClick = {onAnswer(i)}, // 0,1,2,3 -> submitAnswer(i) 실행
            modifier = Modifier.fillMaxWidth(), // 버튼이 가로 꽉 채움
            enabled = feedback == null, // 답 고르지 전에만 클릭 가능
        ) {
            Text(choice)
        }
    }

    feedback?.let { correct -> // 정오답 피드백
        Card(modifier = Modifier.fillMaxWidth()) { // null이면 카드 안보임
            Text(
                text = if (correct) "정답입니다!" else "오답입니다.",
                modifier = Modifier.padding(16.dp),
                color = if (correct) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.error,
            )
        }
    }

    Button( // 답 안고름: 비활성화, 답고름 + 마지막 아님 : 다음문제, 답 고름 + 마지막 문제: 결과보기
        onClick = onNext,
        enabled = canGoNext,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(if (index == total) "결과 보기" else "다음 문제" )
    }
}

// QuizApp state.isFinished == true 일때만 표시
@Composable
fun ResultScreen(score: Int, total: Int, onRestart: () -> Unit) {
    Text("퀴즈 완료!", style = MaterialTheme.typography.headlineMedium)
    Text(
        "점수: $score / $total", // 점수 3/5
        style = MaterialTheme.typography.displaySmall,
        modifier = Modifier.padding(vertical = 24.dp),
    )
    Text(
        when {
            score == total -> "만점! Kotlin 기초를 잘 이해하고 있어요."
            score >= total / 2 -> "좋아요. 틀린 문제를 다시 복습해 보세요."
            else -> "가이드 문서를 보며 다시 도전해 보세요."
        },
    )

    Button(onClick = onRestart, modifier = Modifier.fillMaxWidth()) {
        Text("다시 풀기")
    }
}