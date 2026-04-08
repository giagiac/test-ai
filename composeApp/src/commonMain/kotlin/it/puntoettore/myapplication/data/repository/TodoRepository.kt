package it.puntoettore.myapplication.data.repository

import it.puntoettore.myapplication.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getAll(): Flow<List<Todo>>
    suspend fun getById(id: Long): Todo?
    suspend fun insert(title: String, description: String)
    suspend fun update(id: Long, title: String, description: String)
    suspend fun toggleDone(id: Long, isDone: Boolean)
}
