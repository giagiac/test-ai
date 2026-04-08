package it.puntoettore.myapplication.presentation.tododetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoDetailScreen(
    todoId: Long,
    onBack: () -> Unit,
    viewModel: TodoDetailViewModel = koinViewModel(parameters = { parametersOf(todoId) }),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val saveAndGoBack = { viewModel.save(onBack) }

    SystemBackHandler(onBack = saveAndGoBack)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(if (todoId == TodoDetailViewModel.NEW_TODO_ID) "New To-Do" else "Edit To-Do")
                },
                navigationIcon = {
                    IconButton(onClick = saveAndGoBack) {
                        Text("←")
                    }
                },
            )
        },
    ) { paddingValues ->
        if (!uiState.isLoading) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
            ) {
                OutlinedTextField(
                    value = uiState.title,
                    onValueChange = viewModel::onTitleChange,
                    label = { Text("Title") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    value = uiState.description,
                    onValueChange = viewModel::onDescriptionChange,
                    label = { Text("Description") },
                    minLines = 4,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}
