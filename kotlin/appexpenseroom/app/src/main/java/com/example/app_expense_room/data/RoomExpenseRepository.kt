package com.example.app_expense_room.data

import com.example.app_expense_room.model.Expense
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomExpenseRepository (private val dao: ExpenseDao){
    fun observeAll() : Flow<List<Expense>> =
        dao.observeAll().map { entities -> entities.map { it.toExpense() } }

    suspend fun getById(id: Long): Expense? =
        dao.getById(id)?.toExpense()

    suspend fun insert(expense: Expense): Long =
        dao.insert(expense.toEntity())

    suspend fun update(expense: Expense) {
        dao.update(expense.toEntity())
    }

    suspend fun delete(expense: Expense) {
        dao.delete(expense.toEntity())
    }
}