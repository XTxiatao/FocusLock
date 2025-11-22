# FocusLock

FocusLock is an Android utility that provides a distraction-free experience by combining a system-level lock overlay, task reminders, app whitelist, and usage restriction plans. This repository contains the full Kotlin-based Android app that was recently synchronized to GitHub.

## Features

- **Lock Overlay**
  - Draws a full-screen overlay that blocks input based on schedules or temporary locks
  - Configurable whitelist launcher to open approved apps without disabling the lock
  - Force unlock flow with weekly cool down
  - Reminder pills shown directly on the overlay for quick completion or editing

- **Reminder System**
  - Grouped day cards with optional due time, recurrence, and descriptions
  - Floating (any-time) reminders that hide time labels when no due time is set
  - Swipe gestures to complete, archive, restore, or delete reminders

- **App Management**
  - Whitelist management for apps that are allowed during locks
  - App restriction plans that mirror the device lock experience

- **Permissions & Hints**
  - Overlay, usage stats, accessibility, and battery optimization checks
  - In-app hints guiding users to grant required permissions

## Project Structure

```
app/
 ├─ src/main/java/com/focuslock/
 │   ├─ service/        # LockOverlayService, accessibility service
 │   ├─ ui/             # Fragments, adapters, reminder UI
 │   ├─ data/           # Room repositories and DAOs
 │   └─ model/          # Data models (LockSchedule, Reminder, etc.)
 └─ src/main/res/       # Layouts, drawables (including overlay icons), strings
```

## Building

Requirements:

- Android Studio Flamingo+ or command-line Gradle (7.4+)
- Android SDK 34

Commands:

```bash
# Debug build
./gradlew assembleDebug

# Release build (unsigned unless you configure signingConfig)
./gradlew assembleRelease
```

Outputs are in `app/build/outputs/apk/<variant>/`.

## Permissions

The app requests several high-privilege permissions:

- `SYSTEM_ALERT_WINDOW` to show the overlay
- `PACKAGE_USAGE_STATS` to detect the foreground app
- Accessibility service to intercept inputs when locked
- Battery optimization ignore so the overlay service stays resident

Ensure you grant these permissions during onboarding for the lock features to operate.

## Contributing

1. Fork the repository and create a feature branch.
2. Make changes with clean commit messages.
3. Ensure `./gradlew assembleDebug` passes before submitting a PR.

Issues and pull requests are welcome!

## License

This project is proprietary to its owner. Please refer to the repository license terms (if added) or contact the maintainer before reuse.
