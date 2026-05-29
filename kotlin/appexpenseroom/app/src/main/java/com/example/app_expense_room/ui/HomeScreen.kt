package com.example.app_expense_room.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.app_expense_room.model.Expense
import com.example.app_expense_room.model.ExpenseType
import com.example.app_expense_room.util.formatAmount
import com.example.app_expense_room.util.monthLabel
import com.example.app_expense_room.viewmodel.ExpenseViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: ExpenseViewModel,
    navController: NavController,
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("가계부 (Room)") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("add") }) {
                Icon(Icons.Default.Add, contentDescription = "등록")
            }
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                TextButton(onClick = { viewModel.previousMonth() }) { Text("◀") }
                Text(monthLabel(uiState.year, uiState.month), style = MaterialTheme.typography.titleMedium)
                TextButton(onClick = { viewModel.nextMonth() }) { Text("▶") }
            }

            OutlinedTextField(
                value = uiState.searchQuery,
                onValueChange = { viewModel.setSearchQuery(it) },
                label = { Text("검색 (제목·카테고리·메모)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            SummaryRow(
                income = uiState.totalIncome,
                expense = uiState.totalExpense,
                balance = uiState.balance,
            )

            CategoryChart(categoryExpenses = uiState.categoryExpenses)

            if (uiState.expenses.isEmpty()) {
                Text(
                    "등록된 내역이 없습니다.",
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(uiState.expenses, key = { it.id }) { expense ->
                        ExpenseListItem(
                            expense = expense,
                            onEdit = { navController.navigate("edit/${expense.id}") },
                            onDelete = { viewModel.deleteExpense(expense) },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SummaryRow(income: Long, expense: Long, balance: Long) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        SummaryCard("수입", income, MaterialTheme.colorScheme.primaryContainer, Modifier.weight(1f))
        SummaryCard("지출", expense, MaterialTheme.colorScheme.errorContainer, Modifier.weight(1f))
        SummaryCard("잔액", balance, MaterialTheme.colorScheme.secondaryContainer, Modifier.weight(1f))
    }
}

@Composable
private fun SummaryCard(label: String, amount: Long, containerColor: androidx.compose.ui.graphics.Color, modifier: Modifier) {
    Card(modifier = modifier, colors = androidx.compose.material3.CardDefaults.cardColors(containerColor = containerColor)) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(label, style = MaterialTheme.typography.labelMedium)
            Text(formatAmount(amount), fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ExpenseListItem(
    expense: Expense,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
) {
    val dateText = SimpleDateFormat("MM.dd HH:mm", Locale.KOREA).format(Date(expense.createdAt))
    val isIncome = expense.type == ExpenseType.INCOME

    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(expense.title, style = MaterialTheme.typography.titleMedium)
                Text("${ExpenseType.label(expense.type)} · ${expense.category} · $dateText")
                if (expense.memo.isNotEmpty()) {
                    Text(expense.memo, style = MaterialTheme.typography.bodySmall)
                }
            }
            Text(
                text = (if (isIncome) "+" else "-") + formatAmount(expense.amount),
                fontWeight = FontWeight.Bold,
                color = if (isIncome) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
            )
            IconButton(onClick = onEdit) {
                Icon(Icons.Default.Edit, contentDescription = "수정")
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "삭제")
            }
        }
    }
}
