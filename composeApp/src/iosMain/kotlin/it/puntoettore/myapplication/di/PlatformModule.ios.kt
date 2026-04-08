package it.puntoettore.myapplication.di

import app.cash.sqldelight.driver.native.NativeSqliteDriver
import it.puntoettore.myapplication.data.db.TodoDatabase
import it.puntoettore.myapplication.data.repository.SqlDelightTodoRepository
import it.puntoettore.myapplication.data.repository.TodoRepository
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<TodoRepository> {
        val driver = NativeSqliteDriver(TodoDatabase.Schema, "todo.db")
        SqlDelightTodoRepository(TodoDatabase(driver))
    }
}
