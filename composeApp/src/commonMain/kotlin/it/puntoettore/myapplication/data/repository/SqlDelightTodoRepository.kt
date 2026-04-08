package it.puntoettore.myapplication.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import it.puntoettore.myapplication.data.db.TodoDatabase
import it.puntoettore.myapplication.domain.model.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class SqlDelightTodoRepository(private val db: TodoDatabase) : TodoRepository {

    // Mapper lambdas avoid importing the generated TodoEntity class entirely.
    override fun getAll(): Flow<List<Todo>> =
        db.todoQueries.getAllTodos { id, title, description, isDone ->
            Todo(id, title, description, isDone != 0L)
        }
        .asFlow()
        .mapToList(Dispatchers.Default)

    override suspend fun getById(id: Long): Todo? = withContext(Dispatchers.Default) {
        db.todoQueries.getTodoById(id) { id, title, description, isDone ->
            Todo(id, title, description, isDone != 0L)
        }.executeAsOneOrNull()
    }

    // Block body (not expression body) ensures these functions return Unit,
    // not QueryResult<Long> as SQLDelight 2.x mutation calls do.
    override suspend fun insert(title: String, description: String) {
        withContext(Dispatchers.Default) {
            db.todoQueries.insertTodo(title, description)
        }
    }

    override suspend fun update(id: Long, title: String, description: String) {
        withContext(Dispatchers.Default) {
            db.todoQueries.updateTodo(title, description, id)
        }
    }

    override suspend fun toggleDone(id: Long, isDone: Boolean) {
        withContext(Dispatchers.Default) {
            db.todoQueries.toggleTodo(if (isDone) 1L else 0L, id)
        }
    }
}
