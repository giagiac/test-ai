package it.puntoettore.myapplication.di

import it.puntoettore.myapplication.data.repository.InMemoryTodoRepository
import it.puntoettore.myapplication.data.repository.TodoRepository
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<TodoRepository> { InMemoryTodoRepository() }
}
