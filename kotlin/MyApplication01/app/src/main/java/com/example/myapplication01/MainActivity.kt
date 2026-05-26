package com.example.myapplication01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication01.ui.theme.MyApplication01Theme


// MainActivity 앱 시작 실행 화면 onCreate()안에 UI 설정
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplication01Theme { // 색상 글꼴 설정
                // Scaffold 기본화면구조 - 상단바 TopAppBar BottomBar
                // Floating 버튼
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting( // 텍스트 출력 함수
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable // Compose UI 함수
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
    Button(onClick = {}) {
        Text("버튼 클릭")
    }
    // 코틀린에서 compose 방식
    // Text() Button() Column() Row()
    // xml 방식 <TextView /> <Button/>
}

@Preview(showBackground = true) // 앱 실행없이 미리보기 창에서 화면 확인
@Composable
fun GreetingPreview() {
    MyApplication01Theme {
        Greeting("Android")
    }
}