# ADR-004: Local-First Privacy Model

Status: Accepted

## Context

Personal-first design means user data should remain on the device by default. Telemetry,
analytics, and cloud sync must be opt-in, not opt-out.

## Decision

Implement a local-first architecture:

1. **Default offline**: All core features work without network
2. **Opt-in telemetry**: Firebase Analytics disabled by default, enabled only with user consent
3. **Local model storage**: Models stored in app private storage, never auto-uploaded
4. **Data extraction rules**: Scoped backup/restore with privacy exclusions
5. **Network security**: Cleartext disabled, certificate pinning for model downloads

## Consequences

- Positive: Strong privacy guarantees
- Positive: Works offline in restricted environments
- Negative: No usage analytics by default
- Negative: Requires explicit permission flows for optional features
