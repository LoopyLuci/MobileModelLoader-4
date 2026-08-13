# ADR-003: Package Identity dev.luci.mobilemodelloader

Status: Accepted

## Context

The upstream gallery uses `com.google.ai.edge.gallery`. To establish MobileModelLoader as an
independent project with its own identity, update the package namespace.

## Decision

Adopt `dev.luci.mobilemodelloader` as the primary package namespace across:

- Application ID
- Gradle namespace
- AndroidManifest package
- Deep link scheme
- All Kotlin source files

## Consequences

- Positive: Clear project identity, avoids namespace collisions
- Positive: Enables independent publishing and updates
- Negative: Requires migration of all existing references
- Negative: Breaks backward compatibility for existing deep links
