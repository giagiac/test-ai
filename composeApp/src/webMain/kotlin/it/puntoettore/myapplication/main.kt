package it.puntoettore.myapplication

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import it.puntoettore.myapplication.di.commonModule
import it.puntoettore.myapplication.di.platformModule
import org.koin.core.context.startKoin

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    startKoin { modules(commonModule, platformModule) }
    ComposeViewport {
        App()
    }
}
