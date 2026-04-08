package it.puntoettore.myapplication

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform