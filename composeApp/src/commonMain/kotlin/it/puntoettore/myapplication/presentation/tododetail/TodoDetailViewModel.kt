package it.puntoettore.myapplication.presentation.tododetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.puntoettore.myapplication.data.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TodoDetailViewModel(
    private val repository: TodoRepository,
    private val todoId: Long,
) : ViewModel() {

    data class UiState(
        val title: String = "",
        val description: String = "",
        val isLoading: Boolean = true,
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        if (todoId == NEW_TODO_ID) {
            _uiState.value = UiState(isLoading = false)
        } else {
            loadTodo()
        }
    }

    private fun loadTodo() {
        viewModelScope.launch {
            val todo = repository.getById(todoId)
            _uiState.value = UiState(
                title = todo?.title.orEmpty(),
                description = todo?.description.orEmpty(),
                isLoading = false,
            )
        }
    }

    fun onTitleChange(value: String) {
        _uiState.value = _uiState.value.copy(title = value)
    }

    fun onDescriptionChange(value: String) {
        _uiState.value = _uiState.value.copy(description = value)
    }

    fun save(onDone: () -> Unit) {
        val title = _uiState.value.title.trim()
        if (title.isBlank()) {
            onDone()
            return
        }
        viewModelScope.launch {
            if (todoId == NEW_TODO_ID) {
                repository.insert(title, _uiState.value.description.trim())
            } else {
                repository.update(todoId, title, _uiState.value.description.trim())
            }
            onDone()
        }
    }

    companion object {
        const val NEW_TODO_ID = -1L
    }
}
