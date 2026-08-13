# ADR-006: Static Analysis and Quality Gates

Status: Accepted

## Context

Production-grade code requires automated quality checks beyond unit tests.

## Decision

Implement a multi-layered quality gate:

1. **Ktlint**: Code style enforcement on every build
2. **Detekt**: Static analysis for complexity, style, bugs
3. **Unit tests**: Domain and repository logic with JUnit + Turbine
4. **Compose UI tests**: Critical screens with `ui-test-junit4`
5. **Macrobenchmark**: Performance regression detection
6. **CI pipeline**: GitHub Actions runs all checks on every PR

All checks must pass before merge. Zero tolerance for lint errors in production code.

## Consequences

- Positive: Catches bugs and style issues early
- Positive: Enforces consistency across contributors
- Negative: Build time increase
- Negative: Requires maintenance of lint configs
