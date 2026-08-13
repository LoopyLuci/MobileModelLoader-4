# ADR-001: Modular Architecture

Status: Accepted

## Context

The upstream gallery app is a single monolithic Android application. For long-term maintainability,
independent evolution, and parallel development, we need a modular architecture.

## Decision

Adopt a multi-module Gradle architecture with clear module boundaries:

- `:app` - Application shell and composition root
- `:core` - Pure Kotlin utilities and primitives
- `:domain` - Business logic, use cases, repository interfaces
- `:data` - Data sources, repositories, persistence implementations
- `:runtime` - Model inference runtime adapters (LiteRT, TFLite)
- `:ui-common` - Shared Compose UI components
- `:modelmanager` - Model download and management feature
- `:chat` - Chat/conversation feature
- `:benchmark` - Model benchmarking feature
- `:skills` - Skills/skills manager feature
- `:mcp` - Model Context Protocol integration

## Consequences

- Positive: Independent module evolution, parallel builds, clearer ownership
- Positive: Feature modules can become dynamic delivery modules
- Negative: Increased initial complexity, more Gradle configuration
- Negative: Requires careful dependency direction management
