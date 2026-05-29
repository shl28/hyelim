package com.example.app_todo_room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_todo_room.data.AppDatabase
import com.example.app_todo_room.data.TodoEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TodoViewModel (application: Application) : AndroidViewModel(application){

    private val dao = AppDatabase.getInstance(application).todoDao()

    val todos = dao.observeAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addTodo(title: String) {
        val trimmed = title.trim()
        if (trimmed.isEmpty()) return
        viewModelScope.launch {
            dao.insert(TodoEntity(title = trimmed))
        }
    }

    fun toggleDone(todo: TodoEntity) {
        viewModelScope.launch {
            dao.update(todo.copy(done = !todo.done))
        }
    }

    fun deleteTodo(todo: TodoEntity) {
        viewModelScope.launch {
            dao.delete(todo)
        }
    }
}