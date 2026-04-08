plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
}

group = "it.puntoettore.myapplication"
version = "1.0.0"
application {
    mainClass.set("it.puntoettore.myapplication.ApplicationKt")
    
    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.ktor.serverCore)
    implementation(libs.ktor.serverNetty)
    testImplementation(libs.ktor.serverTestHost)
    testImplementation(libs.kotlin.testJunit)
}