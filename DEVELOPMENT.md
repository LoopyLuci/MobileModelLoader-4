# Development Guide

This guide covers the development environment setup, build system, and common tasks for MobileModelLoader.

## Environment Setup

### Requirements

- **JDK 17**: Download from [Adoptium](https://adoptium.net/)
- **Android Studio**: Ladybug or newer
- **Android SDK**:
  - compileSdk: 35
  - buildTools: latest
  - NDK: for native libraries
- **Git**: with SSH configured

### Local Configuration

Create `local.properties` in the project root:

```properties
sdk.dir=C:/Users/yourname/Android/Sdk
```

## Build System

### Gradle

- **Gradle version**: 9.2.1 (via wrapper)
- **AGP**: 8.10.0
- **Kotlin**: 2.2.0
- **Version catalog**: `gradle/libs.versions.toml`

### Module Structure

```
:app              - Application shell
:core             - Pure Kotlin utilities
:domain           - Business logic, models, repository interfaces
:data             - Data sources, repository implementations
:ui-common        - Shared Compose UI
:runtime          - Inference runtime adapters
:modelmanager     - Model management feature
:chat             - Chat feature
:benchmark        - Benchmarking feature
:skills           - Skills manager
:mcp              - MCP integration
```

## Common Tasks

### Build the app
```bash
./gradlew assembleDebug
```

### Run tests
```bash
./gradlew test
```

### Run static analysis
```bash
./gradlew detektAll
./gradlew ktlintCheck
```

### Clean build
```bash
./gradlew clean
```

### View project structure
```bash
./gradlew projects
```

## Architecture Patterns

### Clean Architecture

The project follows Clean Architecture with three layers:

1. **Domain Layer** (`:domain`): Pure Kotlin, no Android dependencies
   - Domain models
   - Repository interfaces
   - Use cases

2. **Data Layer** (`:data`): Implements repository interfaces
   - Data sources
   - Repository implementations
   - Persistence (DataStore)

3. **Presentation Layer** (`:ui-*`): Android-dependent UI code
   - Composables
   - ViewModels
   - UI state models

### Dependency Injection

- **Hilt** for DI in Android components
- Manual DI in pure Kotlin modules
- Repository interfaces in `:domain`, implementations in `:data`

## Troubleshooting

### Gradle sync fails
- Verify `local.properties` has correct SDK path
- Check Android SDK is installed via SDK Manager
- Try `./gradlew clean` and rebuild

### Tests fail
- Ensure emulator/device is running for instrumentation tests
- Check test dependencies are resolved
- Run with `--info` for detailed output

### Build is slow
- Enable Gradle configuration cache
- Use `--offline` flag if dependencies are cached
- Exclude unnecessary modules from build
