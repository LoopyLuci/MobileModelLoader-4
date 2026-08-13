# Contributing to MobileModelLoader

Thank you for your interest in contributing! This guide covers the development setup, workflow, and standards for the project.

## Prerequisites

- **JDK 17** (Temurin recommended)
- **Android Studio Ladybug** or newer
- **Android SDK**: compileSdk 35, buildTools, NDK
- **Gradle 9.2.1** (wrapper included)
- **Git** with SSH keys configured for GitHub

## Setup

1. Clone the repository:
   ```bash
   git clone git@github.com:LoopyLuci/MobileModelLoader.git
   cd MobileModelLoader
   ```

2. Open in Android Studio:
   - File → Open → Select project root
   - Wait for Gradle sync to complete
   - Create an `local.properties` file with your SDK path if not auto-detected:
     ```
     sdk.dir=C:/Users/yourname/Android/Sdk
     ```

3. Verify the build:
   ```bash
   ./gradlew projects
   ```

## Project Structure

See the [Architecture section in README.md](README.md#architecture) for module overview.

## Development Workflow

1. **Create a branch** for your feature or fix:
   ```bash
   git checkout -b feature/my-feature
   ```

2. **Make changes** following the code style and architecture guidelines.

3. **Run checks** before committing:
   ```bash
   # Run unit tests
   ./gradlew test

   # Run static analysis
   ./gradlew detektAll ktlintCheck

   # Verify project loads
   ./gradlew projects
   ```

4. **Commit with a clear message**:
   ```bash
   git add -A
   git commit -m "feat: add feature description"
   ```

5. **Push and create a PR**:
   ```bash
   git push origin feature/my-feature
   ```

## Code Standards

- **Kotlin style**: Follow official Kotlin coding conventions
- **Compose**: Use Material 3 components, avoid legacy Material 2
- **Architecture**: Respect module boundaries—domain has no Android deps, data implements domain interfaces
- **Naming**: Use descriptive names, avoid abbreviations
- **Imports**: Sort imports, remove unused imports
- **Comments**: Write self-documenting code; comments for "why", not "what"

## Testing

- Write unit tests for domain logic and repository implementations
- Write UI tests for critical user flows
- All tests must pass before merging
- Test files go in the corresponding `-test` module

## Static Analysis

- **Detekt**: Static analysis for complexity, bugs, style
- **Ktlint**: Code style enforcement
- All checks must pass before merging

## Architecture Decision Records

Major architectural changes should be documented as ADRs in `docs/adr/`. See existing ADRs for the format.

## Questions?

Open an issue on GitHub for discussion before starting large changes.
