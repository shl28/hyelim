package com.example.app_expense_room.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.app_expense_room.model.Expense

@Entity(tableName = "expenses")
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val amount: Long,
    val type: String,
    val category: String,
    val memo: String = "",
    val createdAt: Long = System.currentTimeMillis(),
)

fun ExpenseEntity.toExpense(): Expense = Expense(
    id = id,
    title = title,
    amount = amount,
    type = type,
    category = category,
    memo = memo,
    createdAt = createdAt,
)

fun Expense.toEntity(): ExpenseEntity = ExpenseEntity(
    id = id,
    title = title,
    amount = amount,
    type = type,
    category = category,
    memo = memo,
    createdAt = createdAt,
)