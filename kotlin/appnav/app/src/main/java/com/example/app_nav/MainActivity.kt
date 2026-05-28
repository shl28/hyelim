package com.example.app_nav

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.app_nav.ui.theme.AppnavTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                NavApp()
            }
        }
    }
}

@Composable
fun NavApp () {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home",
    ){
        composable ("home"){
            HomeScreen(
                onOpenDetail = { message ->
                    navController.navigate("detail/$message")
                },
            )
        }
        composable (
            "detail/{message}",
            arguments = listOf(navArgument("message"){type = NavType.StringType}),
        ){
            backStackEntry ->
            val message = backStackEntry.arguments?.getString("message").orEmpty()
            DetailScreen(
                message = message,
                onBack = {navController.popBackStack()},
            )
        }
    }
}

@Composable
fun HomeScreen(onOpenDetail: (String) -> Unit) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text("홈 화면", style = MaterialTheme.typography.headlineMedium)
            Text(
                "버튼을 누르면 두 번째 화면으로 이동합니다.",
                modifier = Modifier.padding(vertical = 16.dp),
            )
            Button(
                onClick = {onOpenDetail("Navigation 연습 성공!")},
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("상세 화면으로")
            }
        }
    }
}

@Composable
fun DetailScreen(message: String, onBack: () -> Unit) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text("상세 화면", style = MaterialTheme.typography.headlineMedium)
            Text(
                text = message,
                modifier = Modifier.padding(vertical = 16.dp),
                style = MaterialTheme.typography.titleLarge
            )
            Button(
                onClick = onBack,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("뒤로가기")
            }
        }
    }
}
