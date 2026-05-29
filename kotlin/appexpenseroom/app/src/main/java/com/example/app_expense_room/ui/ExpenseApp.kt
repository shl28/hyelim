package com.example.app_expense_room.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.app_expense_room.viewmodel.ExpenseViewModel

@Composable
fun ExpenseApp(viewModel: ExpenseViewModel = viewModel()) {
    val navController = rememberNavController();

    NavHost(
        navController = navController,
        startDestination = "home",
    ) {
        composable("home") {
            HomeScreen(viewModel = viewModel, navController = navController)
        }
        composable("add") {
            AddEditScreen(expenseId = null, viewModel = viewModel, navController = navController)
        }
        composable(
            route = "edit/{id}",
            arguments = listOf(navArgument("id") { type = NavType.LongType }),
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getLong("id")
            AddEditScreen(expenseId = id, viewModel = viewModel, navController = navController)
        }
    }
}