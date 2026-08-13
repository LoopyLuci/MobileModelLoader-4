# ADR-002: Clean Architecture with Domain Layer

Status: Accepted

## Context

Business logic in Android apps often leaks into Activities, Fragments, and Composables, making
testing difficult and creating tight coupling to the Android framework.

## Decision

Implement Clean Architecture with three layers:

1. **Domain Layer** (`:domain`): Pure Kotlin, no Android SDK dependencies. Contains:
   - Domain models
   - Repository interfaces
   - Use cases

2. **Data Layer** (`:data`): Implements repository interfaces. Contains:
   - Data sources
   - Repository implementations
   - Persistence (DataStore, SQLite)

3. **Presentation Layer** (`:ui-*`): Android-dependent UI code. Contains:
   - Composables
   - ViewModels
   - UI state models

## Consequences

- Positive: Business logic testable without Android framework
- Positive: Clear separation of concerns
- Positive: Easy to swap implementations (fake repos, local vs remote)
- Negative: More files and indirection
- Negative: Requires careful dependency injection setup
