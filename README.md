
# VoteInformed

A developer-focused README with step-by-step environment setup, database creation instructions, usage notes, and contributor information for the VoteInformed Android app.

---

**Contents**
- [About](#about)
- [Contributors](#contributors)
- [Technology Stack & Requirements](#technology-stack--requirements)
- [Setup Guide (Development)](#setup-guide-development)
- [Setup Guide (Production / Server-backed)](#setup-guide-production--server-backed)
- [Database: Create server and run schema scripts](#database-create-server-and-run-schema-scripts)
- [User Guide](#user-guide)
- [Testing Strategy & Sample Test Cases](#testing-strategy--sample-test-cases)
- [Features & Technical Implementation](#features--technical-implementation)
- [Packages & APIs](#packages--apis)
- 
---

## About

VoteInformed is an Android application intended to help voters compare politicians side-by-side across multiple dimensions: overview, issues, contact info, articles, and legislation. The app supports offline viewing (Room/SQLite) and can be backed by a server API for remote data.

Core ideas:
- Quick politician lookup and side-by-side comparison UI
- Local persistence for fast offline use
- Articles and legislation filtered by user-selected concerns
- Bookmarking / saved content for later reference

---

## Contributors

- **Andrew Perrone** — Project owner, architect, repository manager. Responsibilities: overall app architecture, database schema design, project structure, main contributor.
- **Sarah Torres** — Fullstack lead, API integration. Responsibilities: API design and integration, backend/server glue, integration testing.
- **Jennifer Kwon** — Database designer. Responsibilities: schema design and query optimization.
- **Jeff Lin** — UI/UX lead and API integrator. Responsibilities: UI/UX design, RecyclerView/adapter implementations, integration with image loading.



**Specific areas of expertise & notable contributions**
- **Andrew Perrone (Architecture & Data):** designed the app structure, repository pattern, and authored `db/schema.sql` and Gradle config.
- **Sarah Torres (API & Backend):** API design, sample server scaffolding, and integration tests for network flows.
- **Jennifer Kwon (Database):** table/index design and Room migrations guidance.
- **Jeff Lin (UI/UX & Android):** implemented the comparison UI, `PoliticianSearchAdapter`, and polished image loading with Glide.

---

## Technology Stack & Requirements

Hardware
- Development machine: Windows 10/11, macOS, or Linux with at least 8GB RAM (16GB recommended) and 10GB free disk space.
- Android device or emulator (API level matching project config; emulator with Google Play recommended).

Software (developer)
- Java JDK 11 or newer. Set `JAVA_HOME` to your JDK installation.
- Android Studio (recommended) or command-line Gradle.
- Android SDK packages that match the project's `compileSdk`/`targetSdk` (open Android Studio and let it install missing SDKs).
- Git for version control.
- Optional: PostgreSQL (for server-backed testing), `psql` client for applying the provided SQL scripts.

Project tools
- Gradle wrapper (`gradlew` / `gradlew.bat`) is included — use the wrapper so no global Gradle install is required.

---

## Setup Guide (Development)

These steps let you run and develop the Android app locally.

1. Clone the repository

```powershell
git clone <repository-url>
cd VoteInformed_v1.0
```

2. Verify Java and Android environment

```powershell
java -version
# Ensure JAVA_HOME is set; on Windows PowerShell example:
# $env:JAVA_HOME = 'C:\Program Files\Java\jdk-11.0.x'
```

3. Open in Android Studio (recommended)
- `File → Open...` and select the repository root.
- Let Android Studio sync Gradle, download SDKs and dependencies.
- If prompted to update Android Gradle Plugin or wrappers, prefer the versions already present unless you intentionally want to upgrade.

4. Build and run

From Android Studio: select the `app` run configuration and click Run.

Or from PowerShell (command-line):

```powershell
# Build debug APK
.\gradlew.bat assembleDebug

# Install to a connected device or emulator
.\gradlew.bat installDebug
```

5. Tests

```powershell
# Run unit tests
.\gradlew.bat test

# Run instrumentation tests on a connected device/emulator
.\gradlew.bat connectedAndroidTest
```

Notes
- If you need to change the minimum SDK or compile SDK, update `app/build.gradle.kts` and re-sync Gradle.
- Use Android Studio's Logcat to inspect runtime logs and debug crashes.

---

## Setup Guide (Production / Server-backed)

This section explains how to run a simple server-backed environment (example using PostgreSQL). The app itself stores data locally (Room). If you want a server to provide politician & article feeds, follow these guidelines.

1. Provision a PostgreSQL server
- For local development, install PostgreSQL (available from EnterpriseDB or via package managers). For production, use a managed provider (e.g., AWS RDS, Azure Database).

2. Create database and apply schema (see next section for exact commands):

```powershell
# Example: using psql (Windows PowerShell). Adjust host/port/user as needed.
# Create database
psql -U postgres -c "CREATE DATABASE voteinformed_dev;"
# Apply the schema
psql -U postgres -d voteinformed_dev -f db/schema.sql
```

If you need to create a dedicated database user and grant privileges (recommended for production):

```powershell
# Create a role and password (replace values safely)
psql -U postgres -c "CREATE USER vote_user WITH PASSWORD 'change_me_strong_password';"
psql -U postgres -c "GRANT CONNECT ON DATABASE voteinformed_dev TO vote_user;"
psql -U postgres -d voteinformed_dev -c "GRANT USAGE ON SCHEMA public TO vote_user;"
psql -U postgres -d voteinformed_dev -c "GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO vote_user;"
# Optionally grant future table privileges
psql -U postgres -d voteinformed_dev -c "ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO vote_user;"
```

3. Server API
- You can implement a simple REST API server (Node/Express, Spring Boot, Flask, etc.) that connects to the PostgreSQL database and exposes endpoints the Android app can consume. Example endpoints are documented in the `Packages & APIs` section.

4. Configure the Android app to use the remote API
- If adding remote support, add a configuration (e.g., `buildConfigField` or a `res/values/strings.xml`) for the server base URL, and implement network sync using Retrofit / OkHttp.

Example `build.gradle.kts` snippet (app module) to add a base URL accessible as `BuildConfig.API_BASE_URL`:

```kotlin
android {
	defaultConfig {
		buildConfigField("String", "API_BASE_URL", "\"https://api.example.com/\"")
	}
}
```

Access in code:

```kotlin
val baseUrl = BuildConfig.API_BASE_URL
```

Minimal Retrofit interface example:

```kotlin
interface ApiService {
	@GET("api/politicians")
	suspend fun getPoliticians(
		@Query("q") q: String?,
		@Query("limit") limit: Int = 20,
		@Query("offset") offset: Int = 0
	): Response<PoliticianListResponse>
}
```

---

## Database: Create server and run schema scripts

This repo includes SQL scripts under `db/` for creating a development database quickly.

Files included:
- `db/schema.sql` — SQL DDL to create tables (PostgreSQL DDL statements).

How to run (PostgreSQL example):

1) Start/verify your PostgreSQL server is running and accessible.
2) In PowerShell, run:

```powershell
# Example: create a database
psql -U postgres -c "CREATE DATABASE voteinformed_dev;"
# Apply schema
psql -U postgres -d voteinformed_dev -f db/schema.sql
```

If `psql` is not in your PATH, you may need to use the full path to `psql.exe` or run the commands from the PostgreSQL `bin` directory. On managed DB services, upload/execute the SQL files via the provider's console or a DB client.

Security note: the sample scripts include a commented placeholder for creating an admin user — DO NOT use sample passwords in production.

Inspecting the app's local Room database (developer)
- Using Android Studio Device File Explorer: open `View -> Tool Windows -> Device File Explorer`, navigate to `/data/data/<applicationId>/databases/` and download the `.db` file.
- Using `adb` (replace `<applicationId>` and `<dbName>`):

```powershell
adb shell run-as <applicationId> ls /data/data/<applicationId>/databases/
adb shell run-as <applicationId> cat /data/data/<applicationId>/databases/<dbName> > /sdcard/<dbName>
adb pull /sdcard/<dbName>
```

Note: `run-as` requires a debuggable build; replace `<applicationId>` with your app's package name (see `app/build.gradle.kts` `applicationId`).

---

## User Guide

Install & run (developer)
1. Follow the steps in *Setup Guide (Development)*.
2. Launch app on device/emulator.
3. Use the search UI to find politicians, or open the comparison view to select two politicians side-by-side.
4. Use the Saved/Bookmarks screen to store articles for later.

Common workflows
- Search: open the search dialog, type a name, and select a politician — the app updates the card.
- Compare: open the Compare screen, tap each card to select politicians for left and right, swap, or remove.
- Offline: when the device is offline the app reads from the Room DB.

---

## Testing Strategy & Sample Test Cases

Testing approaches
- Unit tests: business logic, repository classes, DAO unit tests (run with `./gradlew test`).
- Instrumentation/UI tests: Espresso tests for RecyclerViews, dialogs, and Activity flows (run with `./gradlew connectedAndroidTest`).
- Manual QA: smoke tests on emulator and physical device.

Sample test cases

1) Launch & Home Load
- Steps: Launch app, wait for home screen to populate.
- Expected: No crashes. Home screen shows prioritized articles or a friendly empty-state.
- Result (example): PASS (home loads within 3s on emulator API 30)

2) Search Politician
- Steps: From search dialog, input `Eric Adams`, select result.
- Expected: Selection updates the politician card with name, party, and photo if available.
- Result (example): PASS — card updated and image loaded via Glide.

3) Compare Two Politicians
- Steps: Select two politicians and open comparison view.
- Expected: Both cards show correct information and toggling tabs updates visible metrics.
- Result (example): PASS — metrics updated on tab switch.

4) Bookmark Article
- Steps: Open an article, tap bookmark, reopen Saved tab.
- Expected: Bookmarked article appears in Saved list and persists across app restarts.
- Result (example): PASS — bookmark stored in Room DB.

How to run tests
- Unit tests: `.
.\gradlew.bat test`
- Instrumentation tests: `.
.\gradlew.bat connectedAndroidTest`

Run a single test class or method (example):

```powershell
.
.\gradlew.bat test --tests "com.yourpackage.repository.PoliticianRepositoryTest"
```

Example expected output snippets
- Unit tests: after running `test`, you should see something like `BUILD SUCCESSFUL in Xs` and a test summary `Tests passed: 12, Failures: 0, Skipped: 0`.
- Instrumentation: `connectedAndroidTest` will upload and run tests on the device; inspect results in the Gradle console or `app/build/reports/androidTests/connected/index.html`.

---

## Features & Technical Implementation

Below is a feature list and how each is implemented at a technical level.

- Politician Profiles
	- Storage: Room entities representing politicians and positions.
	- UI: Card views, RecyclerView lists, dialogs for search/selection.
	- Images: Glide for efficient image loading & caching.

- Side-by-side Comparison
	- Implementation: Activity/Fragment with two selectable card containers, a shared ViewModel to hold selected politician IDs and LiveData to drive UI updates.
	- Swap/remove: local ViewModel operations that update LiveData and persist selection if desired.

- Articles & News
	- Storage: Room table for articles; remote sync implemented (example) via Retrofit.
	- UI: RecyclerView with DiffUtil and clickable items opening a WebView or external browser.

- Legislations
	- Storage & Filters: Room table; UI provides category filtering with adapters.

- Bookmarks / Favorites
	- Storage: Room `bookmarks` table linking `users` to `articles`.

- Local-first design
	- Repository pattern: Repositories abstract Room DB and optional remote data sources; ViewModels consume repositories and expose LiveData to the UI.

---

## Packages & APIs

Packages / libraries used (in-project and recommended):

- **AndroidX** — platform support libraries (Fragments, Lifecycle, RecyclerView).
	- Why: Standard modern Android components and compatibility.

- **Room** — SQLite object-mapping library.
	- Why: Strong typed DAOs, compile-time query checks, simple migration path.

- **Glide** — Image loading and caching.
	- Why: Efficient decoding, memory/disk caching, easy integration.

- **Material Components** — UI components and theming.
	- Why: Modern, accessible UIs and consistent look.

- **ViewModel + LiveData** — Architecture components for UI state management.
	- Why: Lifecycle-aware state handling; prevents leaks and improves testability.

- **Retrofit + OkHttp (recommended for remote)** — for REST API integration.
	- Why: Declarative HTTP client, easy converters (JSON), interceptors for auth and logging.

Example API specification (server is not included in this repo — outline for implementers):

- Base URL (example): `https://api.example.com`

1) GET /api/politicians
- Description: Get list of politicians (supports query params: `q`, `limit`, `offset`).
- Request example: `GET /api/politicians?q=adams&limit=20&offset=0`
- Response example (200):
```json
{
	"items": [
		{"id": 1, "first_name": "Eric", "last_name": "Adams", "party": "Democratic", "photo_url": "https://..."}
	],
	"total": 1
}
```

2) GET /api/politicians/{id}
- Description: Get detailed politician data, including positions and concerns.
- Response example (200):
```json
{
	"id": 1,
	"first_name": "Eric",
	"last_name": "Adams",
	"party": "Democratic",
	"biography": "...",
	"positions": [{"title": "Mayor", "organization": "City"}],
	"concerns": ["Healthcare", "Economy"]
}
```

3) GET /api/articles
- Description: Get articles, filter by concern or politician.
- Example: `GET /api/articles?concern=Healthcare&limit=20`

Authentication (example)
- Bearer token (JWT) in `Authorization: Bearer <token>` header for endpoints that require authentication (e.g., bookmarking, user profile).
- Login endpoint: `POST /api/auth/login` with `email` and `password` returning a token.

Notes
- The above endpoints are example templates — adapt them for your backend stack. If a server already exists, replace these examples with the real endpoints and update the Android `Retrofit` interface accordingly.

Authentication example (client):
- After obtaining a JWT from `POST /api/auth/login`, include it on requests using an OkHttp interceptor:

```kotlin
val client = OkHttpClient.Builder()
	.addInterceptor { chain ->
		val req = chain.request().newBuilder()
			.addHeader("Authorization", "Bearer ${token}")
			.build()
		chain.proceed(req)
	}
	.build()
```

Replace `${token}` with a secure in-memory or persisted token management strategy (avoid plaintext storage).

---
