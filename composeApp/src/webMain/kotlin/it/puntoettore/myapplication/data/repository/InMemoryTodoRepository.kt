package it.puntoettore.myapplication.data.repository

import it.puntoettore.myapplication.domain.model.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class InMemoryTodoRepository : TodoRepository {

    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    private var nextId = 1L

    override fun getAll(): Flow<List<Todo>> = _todos.asStateFlow()

    override suspend fun getById(id: Long): Todo? = _todos.value.find { it.id == id }

    override suspend fun insert(title: String, description: String) {
        val todo = Todo(id = nextId++, title = title, description = description, isDone = false)
        _todos.update { listOf(todo) + it }
    }

    override suspend fun update(id: Long, title: String, description: String) {
        _todos.update { list ->
            list.map { if (it.id == id) it.copy(title = title, description = description) else it }
        }
    }

    override suspend fun toggleDone(id: Long, isDone: Boolean) {
        _todos.update { list ->
            list.map { if (it.id == id) it.copy(isDone = isDone) else it }
        }
    }
}
