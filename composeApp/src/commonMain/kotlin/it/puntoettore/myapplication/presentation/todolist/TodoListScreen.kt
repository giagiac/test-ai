package it.puntoettore.myapplication.presentation.todolist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import it.puntoettore.myapplication.domain.model.Todo
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(
    onNavigateToDetail: (todoId: Long) -> Unit,
    viewModel: TodoListViewModel = koinViewModel(),
) {
    val todos by viewModel.todos.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("To-Do") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onNavigateToDetail(-1L) }) {
                Text("+", style = MaterialTheme.typography.headlineSmall)
            }
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            items(todos, key = { it.id }) { todo ->
                TodoRow(
                    todo = todo,
                    onToggle = { viewModel.toggleTodo(todo.id, it) },
                    onClick = { onNavigateToDetail(todo.id) },
                )
            }
        }
    }
}

@Composable
private fun TodoRow(
    todo: Todo,
    onToggle: (Boolean) -> Unit,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = todo.isDone,
            onCheckedChange = onToggle,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = todo.title,
            style = MaterialTheme.typography.bodyLarge,
            textDecoration = if (todo.isDone) TextDecoration.LineThrough else TextDecoration.None,
        )
    }
}
