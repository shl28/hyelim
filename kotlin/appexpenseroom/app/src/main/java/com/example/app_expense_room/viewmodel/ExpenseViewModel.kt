package com.example.app_expense_room.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_expense_room.data.AppDatabase
import com.example.app_expense_room.data.RoomExpenseRepository
import com.example.app_expense_room.model.Expense
import com.example.app_expense_room.model.ExpenseType
import com.example.app_expense_room.util.matchesMonth
import com.example.app_expense_room.util.matchesSearch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Calendar

//화면에 필요한 모든값을 한 곳에 모은 묶음
data class ExpenseUiState(
    val expenses: List<Expense> = emptyList(), //지출목록
    val totalIncome: Long = 0, //수입합계
    val totalExpense: Long = 0, //지출합계
    val balance: Long = 0, //잔액
    val categoryExpenses: Map<String, Long> = emptyMap(), //챠트
    val year: Int = Calendar.getInstance().get(Calendar.YEAR), //년
    val month: Int = Calendar.getInstance().get(Calendar.MONTH) + 1,//월
    val searchQuery: String = "", //검색어
)

//viewModel 에서 Repository 와 Database 연걸
class ExpenseViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = RoomExpenseRepository(
        AppDatabase.getInstance(application).expenseDao(),
    )
    private val searchQuery = MutableStateFlow("") //검색어
    private val selectedYear = MutableStateFlow(Calendar.getInstance().get(Calendar.YEAR))
    private val selectedMonth = MutableStateFlow(Calendar.getInstance().get(Calendar.MONTH) + 1)
    val uiState : StateFlow<ExpenseUiState> = combine( //4개합치기
        repository.observeAll(), //db 목록 flow
        searchQuery,
        selectedYear,
        selectedMonth,
    ){ //4 개중 하나라도 바뀌면 람다 블럭이 다시 실행 -> 새 uiState생성
            all, query, year, month ->
        val filtered = all
            .filter { matchesMonth(it, year, month) && matchesSearch(it, query) }
            .sortedByDescending { it.createdAt }
        //이번 달 + 검색어 일치항목만 , 최신순
        val income = filtered.filter { it.type == ExpenseType.INCOME }.sumOf { it.amount } //합계 - 수입
        val expense = filtered.filter { it.type == ExpenseType.EXPENSE }.sumOf { it.amount }//지출합계
        //챠트용
        val categoryMap = filtered
            .filter { it.type == ExpenseType.EXPENSE } //지출만
            .groupBy { it.category } //식비 ->[...], 교통 -> [...]
            .mapValues { (_, list) -> list.sumOf { it.amount } } //합계
            .filter { it.value > 0 } // 0원 제외

        //결과 묶기
        ExpenseUiState(
            expenses = filtered,
            totalIncome = income,
            totalExpense = expense,
            balance = income - expense,
            categoryExpenses = categoryMap,
            year = year,
            month = month,
            searchQuery = query,
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), ExpenseUiState())
//stateFlow로 변환

    fun setSearchQuery(query: String) { //검색어 변경 -> 값이 바뀌면 -> combine 재실행 -> 목록 챠트 합계 갱신
        searchQuery.value = query
    }

    fun previousMonth() { //이전달
        if (selectedMonth.value == 1) {
            selectedMonth.value = 12
            selectedYear.value -= 1
        } else {
            selectedMonth.value -= 1
        }
    }

    fun nextMonth() {
        if (selectedMonth.value == 12) {
            selectedMonth.value = 1
            selectedYear.value += 1
        } else {
            selectedMonth.value += 1
        }
    }

    // 가계부의 저장(등록 / 수정) 삭제 조회 crud
    fun saveExpense(
        id: Long?,
        title: String,
        amount: Long,
        type: String,
        category: String,
        memo: String,
    ) {
        val trimmedTitle = title.trim() //입력 검증 -제목이 비어있거나 금액 0 이면 저장안함
        if (trimmedTitle.isEmpty() || amount <= 0) return

        viewModelScope.launch {
            if (id == null) {
                repository.insert(
                    Expense(
                        title = trimmedTitle,
                        amount = amount,
                        type = type,
                        category = category,
                        memo = memo.trim(),
                    ),
                )
            } else {
                val existing = repository.getById(id) ?: return@launch
                repository.update(
                    existing.copy(
                        title = trimmedTitle,
                        amount = amount,
                        type = type,
                        category = category,
                        memo = memo.trim(),
                    ),
                )
            }
        }
    }

    fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
            repository.delete(expense)
        }
    }

    suspend fun getExpense(id: Long): Expense? = repository.getById(id)


}