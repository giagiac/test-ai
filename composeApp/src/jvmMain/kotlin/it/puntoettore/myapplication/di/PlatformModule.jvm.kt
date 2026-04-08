package it.puntoettore.myapplication.di

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import it.puntoettore.myapplication.data.db.TodoDatabase
import it.puntoettore.myapplication.data.repository.SqlDelightTodoRepository
import it.puntoettore.myapplication.data.repository.TodoRepository
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<TodoRepository> {
        val driver = JdbcSqliteDriver("jdbc:sqlite:todo.db")
        TodoDatabase.Schema.create(driver)
        SqlDelightTodoRepository(TodoDatabase(driver))
    }
}
