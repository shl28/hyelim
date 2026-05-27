package com.example.app_quiz

// 데이터 모델
data class QuizQuestion(
    val question: String,
    val choices: List<String>,
    val correctIndex: Int,
)
//val quiz = QuizQuestion(
//    question = "Kotlin은 어떤 회사에서 개발했나요?",
//    choices = listOf(
//        "Google",
//        "JetBrains",
//        "Apple",
//        "Microsoft"
//    ),
//    correctIndex = 1
//)

val kotlinQuizQuestions = listOf(
    QuizQuestion(
        question = "읽기 전용 변수를 선언할 때 쓰는 키워드는?",
        choices = listOf("var", "val", "const", "let"),
        correctIndex = 1,
    ),
    QuizQuestion(
        question = "null을 허용하는 타입 표기는?",
        choices = listOf("String!", "String?", "?String", "nullable String"),
        correctIndex = 1,
    ),
    QuizQuestion(
        question = "리스트를 불변으로 만드는 함수는?",
        choices = listOf("mutableListOf", "listOf", "arrayListOf", "ArrayList"),
        correctIndex = 1,
    ),
    QuizQuestion(
        question = "when은 주로 어떤 Java 문법을 대체하나요?",
        choices = listOf("for", "switch", "try", "interface"),
        correctIndex = 1,
    ),
    QuizQuestion(
        question = "data class가 자동 생성해 주지 않는 것은?",
        choices = listOf("copy()", "equals()", "main()", "componentN()"),
        correctIndex = 2,
    ),
)