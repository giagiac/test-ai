package it.puntoettore.myapplication.domain.model

data class Todo(
    val id: Long,
    val title: String,
    val description: String,
    val isDone: Boolean,
)
