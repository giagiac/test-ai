package it.puntoettore.myapplication

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import it.puntoettore.myapplication.presentation.tododetail.TodoDetailScreen
import it.puntoettore.myapplication.presentation.todolist.TodoListScreen

private const val ROUTE_TODO_LIST = "todo_list"
private const val ROUTE_TODO_DETAIL = "todo_detail/{todoId}"
private const val ARG_TODO_ID = "todoId"

@Composable
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = ROUTE_TODO_LIST) {
            composable(ROUTE_TODO_LIST) {
                TodoListScreen(
                    onNavigateToDetail = { todoId ->
                        navController.navigate("todo_detail/$todoId")
                    },
                )
            }
            composable(ROUTE_TODO_DETAIL) { backStackEntry ->
                val todoId = backStackEntry.arguments?.getString(ARG_TODO_ID)?.toLongOrNull() ?: -1L
                TodoDetailScreen(
                    todoId = todoId,
                    onBack = { navController.popBackStack() },
                )
            }
        }
    }
}
