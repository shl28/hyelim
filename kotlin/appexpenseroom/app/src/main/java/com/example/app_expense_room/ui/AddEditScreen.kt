package com.example.app_expense_room.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.app_expense_room.model.ExpenseCategories
import com.example.app_expense_room.model.ExpenseType
import com.example.app_expense_room.viewmodel.ExpenseViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditScreen(
    expenseId: Long?,
    viewModel: ExpenseViewModel,
    navController: NavController,
) {
    var title by remember { mutableStateOf("") }
    var amountText by remember { mutableStateOf("") }
    var type by remember { mutableStateOf(ExpenseType.EXPENSE) }
    var category by remember { mutableStateOf(ExpenseCategories.expense.first()) }
    var memo by remember { mutableStateOf("") }

    LaunchedEffect(expenseId) {
        if (expenseId != null) {
            viewModel.getExpense(expenseId)?.let { expense ->
                title = expense.title
                amountText = expense.amount.toString()
                type = expense.type
                category = expense.category
                memo = expense.memo
            }
        }
    }

    LaunchedEffect(type) {
        val categories = ExpenseCategories.forType(type)
        if (category !in categories) {
            category = categories.first()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(if (expenseId == null) "등록" else "수정") })
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FilterChip(
                    selected = type == ExpenseType.INCOME,
                    onClick = { type = ExpenseType.INCOME },
                    label = { Text("수입") },
                )
                FilterChip(
                    selected = type == ExpenseType.EXPENSE,
                    onClick = { type = ExpenseType.EXPENSE },
                    label = { Text("지출") },
                )
            }

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("제목") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )
            OutlinedTextField(
                value = amountText,
                onValueChange = { amountText = it.filter { c -> c.isDigit() } },
                label = { Text("금액 (원)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Text("카테고리")
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                ExpenseCategories.forType(type).forEach { item ->
                    FilterChip(
                        selected = category == item,
                        onClick = { category = item },
                        label = { Text(item) },
                    )
                }
            }

            OutlinedTextField(
                value = memo,
                onValueChange = { memo = it },
                label = { Text("메모") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 2,
            )

            Button(
                onClick = {
                    val amount = amountText.toLongOrNull() ?: return@Button
                    viewModel.saveExpense(expenseId, title, amount, type, category, memo)
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(if (expenseId == null) "저장" else "수정")
            }
        }
    }
}
