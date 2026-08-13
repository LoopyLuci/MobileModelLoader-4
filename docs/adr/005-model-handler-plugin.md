# ADR-005: Extensible Model Handler Plugin System

Status: Accepted

## Context

The ML ecosystem evolves rapidly. New model formats, runtimes, and accelerators appear regularly.
Hardcoding support for specific runtimes limits long-term adaptability.

## Decision

Define a `ModelHandler` interface in the `:domain` layer:

- Pure Kotlin interface, no Android dependencies
- Implementations in `:runtime` or plugin modules
- Discovered via Hilt multibinding or service loader
- Configuration-driven model selection

This allows new backends to be added without modifying core app logic.

## Consequences

- Positive: Open for extension, closed for modification
- Positive: Community can contribute new runtime adapters
- Negative: Initial abstraction overhead
- Negative: Requires stable interface contracts
