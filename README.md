# agrios_ui

Compose Multiplatform desktop prototype for the AgrioS dashboard.

## Current scope

- Desktop JVM target
- Shared UI in `commonMain`
- Dummy farm/zone/alert/weather data
- Clickable zone selection
- Zone-aware AI irrigation recommendation
- Soil moisture chart
- Theme abstraction prepared for light/dark
- No external map SDK yet; farm map is a stylized Canvas prototype

## Run

```bash
./gradlew :composeApp:run
```

## Architecture direction

The UI is intentionally split into:

- `model/` — domain-facing UI models
- `data/` — local dummy data provider
- `theme/` — appearance/token boundary
- `ui/` — feature components

Next iterations should introduce explicit feature/state boundaries rather than letting composables own domain logic.

## Dark mode

Do not scatter `Color(...)` literals into feature code when implementing dark mode.
Move remaining visual constants into semantic design tokens first, then switch
the `AgrioSTheme` color scheme.

## Version baseline

This project uses Kotlin 2.4.10 and Compose Multiplatform 1.11.1, matching
the current official compatibility guidance at the time this prototype was created.
