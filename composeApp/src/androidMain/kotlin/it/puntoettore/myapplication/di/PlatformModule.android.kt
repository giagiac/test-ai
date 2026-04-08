package it.puntoettore.myapplication.di

import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import it.puntoettore.myapplication.data.db.TodoDatabase
import it.puntoettore.myapplication.data.repository.SqlDelightTodoRepository
import it.puntoettore.myapplication.data.repository.TodoRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<TodoRepository> {
        val driver = AndroidSqliteDriver(TodoDatabase.Schema, androidContext(), "todo.db")
        SqlDelightTodoRepository(TodoDatabase(driver))
    }
}
