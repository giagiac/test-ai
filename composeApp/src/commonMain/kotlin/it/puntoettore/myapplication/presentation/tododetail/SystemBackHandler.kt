package it.puntoettore.myapplication.presentation.tododetail

import androidx.compose.runtime.Composable

/** Intercepts the system back action (Android hardware/gesture back). No-op on other platforms. */
@Composable
expect fun SystemBackHandler(onBack: () -> Unit)
