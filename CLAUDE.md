# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

LX Music Mobile (洛雪音乐移动版) — a React Native music player for Android 5+. Supports multiple music sources (KuGou, KuWo, QQ Music, NetEase, MiGu, Baidu). Written primarily in TypeScript with Chinese UI and comments.

## Common Commands

```bash
npm run start          # Start Metro bundler
npm run sc             # Start Metro with cache reset
npm run dev            # Run on Android device (debug, active arch only)
npm run lint           # ESLint check
npm run lint:fix       # ESLint auto-fix
npm run pack           # Build release APK (assembleRelease)
npm run pack:android:debug  # Build debug APK
npm run clear          # Clean Android build
npm run build:theme    # Regenerate theme assets
```

## Architecture

### State Management — Custom Event System (not Redux)

State is managed through plain objects with direct mutations, broadcasting changes via custom event emitters:

- `global.state_event` (StateEvent) — config, theme, player, list state changes
- `global.app_event` (AppEvent) — player control, errors, app-wide events
- `global.list_event` (ListEvent) — list operations
- `global.dislike_event` (DislikeEvent) — dislike management

Pattern: actions in `src/store/*/action.ts` mutate state objects in `src/store/*/state.ts`, then emit events. Components subscribe to events for re-rendering.

### Navigation

Uses `react-native-navigation` (not React Navigation). Screens are registered in `src/navigation/registerScreens.tsx` and wrapped with ThemeProvider. Navigation is stack/modal-based with component IDs tracked in `src/config/constant.ts`.

### Initialization Flow

Entry: `index.js` → `src/app.ts` → `src/core/init/index.ts`

Init sequence: theme → i18n → user API → API source → TrackPlayer service → player → data (lists/settings) → common state → sync → navigation → home screen → post-launch (deeplink, update check).

### Global Runtime Data

`global.lx` holds runtime state (player status, API references, quality lists, etc.). Initialized in `src/config/globalData.ts`.

### Music SDKs

Each source lives in `src/utils/musicSdk/{bd,kg,kw,tx,wy,mg}/` implementing search, songlist, leaderboard, lyrics, and metadata modules. User-defined API sources are also supported.

### Path Alias

`@/*` maps to `src/*` (configured in tsconfig.json and babel.config.js).

## Key Conventions

- Language: TypeScript with `@react-native/typescript-config`. Mixed `.ts`/`.js` — some SDK files are plain JS.
- i18n files in `src/lang/` (en-us, zh-cn, zh-tw). Access via `global.i18n.t()`.
- Theme definitions built from `src/theme/themes/`; run `npm run build:theme` after changes.
- Several dependencies use custom forks (track-player, background-timer, file-system, local-media-metadata) — check `package.json` for GitHub URLs before upgrading.
- Node >= 18, npm >= 8.5.2 required.
