package it.puntoettore.myapplication.di

import it.puntoettore.myapplication.presentation.tododetail.TodoDetailViewModel
import it.puntoettore.myapplication.presentation.todolist.TodoListViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val commonModule = module {
    viewModel { TodoListViewModel(get()) }
    viewModel { (todoId: Long) -> TodoDetailViewModel(get(), todoId) }
}

expect val platformModule: Module
