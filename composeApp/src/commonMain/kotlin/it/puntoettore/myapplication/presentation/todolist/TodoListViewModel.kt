package it.puntoettore.myapplication.presentation.todolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.puntoettore.myapplication.data.repository.TodoRepository
import it.puntoettore.myapplication.domain.model.Todo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TodoListViewModel(private val repository: TodoRepository) : ViewModel() {

    val todos: StateFlow<List<Todo>> = repository.getAll()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun toggleTodo(id: Long, isDone: Boolean) {
        viewModelScope.launch { repository.toggleDone(id, isDone) }
    }
}
