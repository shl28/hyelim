package com.example.app_expense_room.model

data class Expense(
    val id: Long = 0,
    val title: String,
    val amount: Long,
    val type: String,
    val category: String,
    val memo: String = "",
    val createdAt: Long = System.currentTimeMillis(),
)

//Expense(
//id = 1,
//title = "점심",
//amount = 12000,
//type = ExpenseType.EXPENSE,
//category = "식비",
//memo = "김치찌개",
//)

object ExpenseType {

    const val INCOME = "income"
    const val EXPENSE = "expense"

    fun label(type: String): String = when (type) {
        INCOME -> "수입"
        else -> "지출"
    }
}

object ExpenseCategories {

    val income = listOf("급여", "부수입", "기타")
    val expense = listOf("식비", "교통", "쇼핑", "주거", "기타")

    fun forType(type: String): List<String> =
        if (type == ExpenseType.INCOME) income else expense
}