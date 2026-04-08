package it.puntoettore.myapplication

import androidx.compose.ui.window.ComposeUIViewController
import it.puntoettore.myapplication.di.commonModule
import it.puntoettore.myapplication.di.platformModule
import org.koin.compose.KoinApplication

fun MainViewController() = ComposeUIViewController {
    KoinApplication(application = { modules(commonModule, platformModule) }) {
        App()
    }
}
