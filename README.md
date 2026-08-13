# MobileModelLoader

A personal-first, production-grade custom evolution of [google-ai-edge/gallery](https://github.com/google-ai-edge/gallery) focused on **personal use**, **local control**, and **individual-first** on-device AI workloads.

## What It Is

This is not just a fork. **MobileModelLoader** is an independent custom evolution with its own identity, purpose, and direction:

- Personal-first design: single-user defaults, privacy-first, local storage
- On-device AI model management and inference
- Extensible MCP integration
- Skills and custom tasks tailored for individual use
- Production-grade Kotlin Android architecture with Clean Architecture

## Architecture

The project follows **Clean Architecture** with clear module boundaries:

```
:app              - Application shell and composition root
:core             - Pure Kotlin utilities and Result types
:domain           - Business logic, domain models, repository interfaces
:data             - Data sources, repository implementations, persistence
:ui-common        - Shared Compose UI components, theme, navigation
:runtime          - Model inference runtime adapters (LiteRT, TFLite)
:modelmanager     - Model download and management feature
:chat             - Chat/conversation feature
:benchmark        - Model benchmarking feature
:skills           - Skills/skills manager feature
:mcp              - Model Context Protocol integration
```

### Dependency Direction

```
:domain → :core
:data → :domain → :core
:runtime → :domain → :core
:features → :domain, :data, :runtime, :ui-common
:app → :features, :ui-common
```

## Tech Stack

- **Kotlin 2.2** + **Jetpack Compose** (Material 3)
- **Gradle 9.2.1** with version catalog
- **Hilt DI** for dependency injection
- **Proto DataStore** for persistent storage
- **KSP** for code generation
- **Detekt** + **Ktlint** for static analysis
- **JUnit** + **AndroidX Test** for testing

## Getting Started

```bash
# Clone the repository
git clone git@github.com:LoopyLuci/MobileModelLoader.git
cd MobileModelLoader

# Verify project structure
./gradlew projects

# Build the app
./gradlew assembleDebug

# Run tests
./gradlew test
```

## Building

Requires Android SDK with compileSdk 35, buildTools, and NDK for native libraries.

```bash
./gradlew assembleDebug
```

## Testing

```bash
# Run unit tests
./gradlew test

# Run instrumentation tests
./gradlew connectedAndroidTest

# Run static analysis
./gradlew detektAll ktlintCheck
```

## Security

- Network security config with trusted CAs for model downloads
- Scoped backup/restore rules
- Permission rationale strings
- Local-first privacy model: telemetry disabled by default

## Contributing

See [CONTRIBUTING.md](CONTRIBUTING.md) for development setup and workflow.

## License

Apache-2.0
