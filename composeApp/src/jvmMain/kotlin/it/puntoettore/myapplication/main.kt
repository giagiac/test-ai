package it.puntoettore.myapplication

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import it.puntoettore.myapplication.di.commonModule
import it.puntoettore.myapplication.di.platformModule
import org.koin.core.context.startKoin

fun main() {
    startKoin { modules(commonModule, platformModule) }
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "To-Do",
        ) {
            App()
        }
    }
}
