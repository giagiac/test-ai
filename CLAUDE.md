# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a **Kotlin Multiplatform (KMP)** project named "MyApplication" targeting Android, iOS, Desktop (JVM), Web (JS/Wasm), and a Ktor server backend. The app is a Todo manager.

## Modules

- **`:composeApp`** — Shared Compose Multiplatform UI + Android/iOS/Desktop/Web entry points
- **`:shared`** — Shared utility code, platform abstractions (`expect`/`actual`), constants
- **`:server`** — Ktor REST API backend (runs on port 8080)

## Build Commands

```bash
# Build Android app
./gradlew :composeApp:assembleDebug

# Run Desktop (JVM) app
./gradlew :composeApp:run

# Run Ktor server
./gradlew :server:run

# Run all tests
./gradlew test

# Run common tests only
./gradlew :composeApp:commonTest

# Run server tests
./gradlew :server:test

# Run a single test class
./gradlew :server:test --tests "it.puntoettore.myapplication.ApplicationTest"
```

## Architecture

**MVVM + Clean Architecture** across all platforms:

- `domain/model/` — Plain data classes (e.g., `Todo`)
- `data/repository/` — `TodoRepository` interface + `SqlDelightTodoRepository` implementation
- `presentation/<feature>/` — `ViewModel` (StateFlow-based) + Compose `Screen` composable
- `di/` — Koin modules: `commonModule` (ViewModels), `platformModule` (platform-specific DB driver)

**Navigation** uses Jetpack Compose Navigation with string routes (`todo_list`, `todo_detail/{todoId}`), defined in `App.kt`.

**Database** uses SQLDelight with `.sq` files in `composeApp/src/commonMain/sqldelight/`. Schema and queries are in `Todo.sq`. Platform drivers are injected via Koin's `platformModule` (each platform has its own `actual` implementation under `androidMain`, `iosMain`, `jvmMain`, etc.).

**Dependency Injection**: Koin 4.0. `MyApplication.kt` (Android) starts Koin via `startKoin`. Platform modules are registered per-platform via `expect/actual`.

**Multiplatform pattern**: Use `expect` in `commonMain`, provide `actual` in each platform source set (`androidMain`, `iosMain`, `jvmMain`, `jsMain`, `wasmJsMain`).

## Key Libraries

| Library | Version | Purpose |
|---|---|---|
| Kotlin | 2.3.20 | Language |
| Compose Multiplatform | 1.10.3 | Shared UI |
| Koin | 4.0.0 | DI |
| SQLDelight | 2.3.2 | Multiplatform DB |
| Ktor | 3.4.1 | Server + HTTP client |
| AndroidX Navigation | 2.8.0-alpha10 | Compose navigation |
| Kotlinx Coroutines | 1.10.2 | Async |

All versions are managed via the version catalog at `gradle/libs.versions.toml`.

## SDK / Java Targets

- `compileSdk`/`targetSdk`: 36, `minSdk`: 24
- Kotlin JVM target: 11
- Gradle daemon max heap: 4GB (`org.gradle.jvmargs=-Xmx4g`)
- Configuration caching and build caching are enabled