package it.puntoettore.myapplication.presentation.tododetail

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable

@Composable
actual fun SystemBackHandler(onBack: () -> Unit) {
    BackHandler(onBack = onBack)
}
