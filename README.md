
# VoteInformed

A comprehensive civic engagement Android application helping voters make informed decisions through politician profiles, elections lookup, legislation tracking, and news feeds. Integrates with multiple government APIs and local persistence via Room database.

---

## Table of Contents
- [About](#about)
- [Features](#features)
- [Current Implementation](#current-implementation)
- [Contributors](#contributors)
- [Technology Stack & Requirements](#technology-stack--requirements)
- [Setup Guide (Development)](#setup-guide-development)
- [Usage Guide](#usage-guide)
- [Project Architecture](#project-architecture)
- [Database Schema](#database-schema)
- [API Integration](#api-integration)
- [Testing](#testing)
- [Packages & Dependencies](#packages--dependencies)
---

## About

**VoteInformed** empowers voters with multi-level political engagement tools:

- **Politician Profiles & Comparison**: Search federal, state, and local politicians; compare side-by-side across background, positions, and contact info
- **Elections & Voting**: Look up upcoming elections and polling locations via Google Civic API
- **Legislation Tracking**: Monitor NYC City Council bills and matters via Legistar API
- **News Feed**: Curated news articles filtered by your selected concerns (12 categories)
- **User Preferences**: Set concern categories (Healthcare, Education, Civil Rights, etc.) to personalize content
- **Saved Content**: Bookmark articles for later reference
- **Local-First Data**: All data cached locally in Room database for offline access
- **User Authentication**: Email/password login and registration with persistent sessions

## Features

### Screens & Activities
| Activity | Purpose | Key Features |
|----------|---------|--------------|
| **Homescreen** | Landing screen | Login/Register entry points |
| **Home Feed** | Main content hub | Filtered legislation & news, Chip-based concern filtering, Bookmark articles, Legistar API integration |
| **Elections** | Upcoming elections & featured politicians | Google Civic API, Election listings, Featured politicians carousel, Voter information |
| **Search** | Politician discovery | Full-text search, Party filtering (Republican/Democratic/Independent) |
| **Politician Comparison** | Side-by-side politician analysis | Dual politician selection, Multiple tabs (Overview/Issues/Contact), Search dialog within comparison |
| **Politician Profile** | Individual politician details | Background, Policy positions, Contact info, Tabs with animations |
| **Saved Articles** | Bookmarked content | View saved articles, Bookmark management, Deletion |
| **Profile** | User account | Edit name/email/location, Logout |
| **Concerns** | Preference selection | 12 issue categories, Dynamic chip reordering, Persistent settings |
| **Personal Info** | User settings | Edit profile information |

### Core Functionality
- **Multi-level Politician Data**: Federal, state (NY), and local (NYC) politicians from real APIs
- **Advanced Search**: Full-text politician search with party affiliation filtering
- **Legislation Tracking**: Live NYC City Council bills via Legistar API
- **Intelligent News Filtering**: Articles dynamically queried based on user concerns
- **Bookmarking System**: Dual system - quick toggle via SharedPreferences, persistent storage via Room
- **Animated UI**: Tab transitions, bookmark heart animations, smooth component interactions
- **Session Management**: Email/password authentication with SharedPreferences persistence

---

## Current Implementation

### What's Built
**Complete navigation drawer** with 7 main screens  
**Politician search & comparison** with dual-panel UI  
**Elections lookup** via Google Civic API integration  
**Live legislation tracking** via Legistar API (NYC City Council)  
**News aggregation** from NewsAPI filtered by user concerns  
**User authentication** with email/password login & registration  
**Persistent local database** with Room (14 entities, complex relationships)  
**Bookmarking system** for articles (SharedPreferences + Room)  
**Concerns/preferences system** with 12 issue categories  
**Animated UI elements** (tab transitions, heart animations)  
**ViewModels & LiveData** for reactive UI updates  
**RecyclerView adapters** for lists and carousels  

| Name | Role | Key Responsibilities |
|------|------|----------------------|
| **Andrew Perrone** | Project Owner & Architect | Overall app architecture, database design, Gradle configuration, project management |
| **Sarah Torres** | Fullstack Lead | API integration, backend design, integration testing |
| **Jennifer Kwon** | Database Designer | Schema design, query optimization, Room migrations |
| **Jeff Lin** | UI/UX Lead | UI design, RecyclerView implementations, image handling, Comparison UI |

---

## Technology Stack & Requirements

### Hardware
- **Development Machine**: Windows 10/11, macOS, or Linux with:
  - Minimum 8GB RAM (16GB recommended)
  - 10GB free disk space
  - SSD strongly recommended for emulator performance
- **Testing Device**: Android device (API 24+) or Android emulator (API 24-36)

### Software Requirements
- **Java**: JDK 11 or newer
- **Android Studio**: Latest version (recommended) or command-line tools
- **Android SDK**: API level 36 (compileSdk), minimum API 24 (minSdk)
- **Git**: For version control
- **Gradle**: Included via wrapper (no global installation needed)

### Optional (for advanced development)
- **PostgreSQL**: For server-backed data development
- **Postman**: For API testing
- **Android Device Monitor**: For advanced debugging

---

## Setup Guide (Development)

### 1. Clone the Repository

```powershell
git clone https://github.com/andrewperrone/VoteInformed_v1.0.git
cd VoteInformed_v1.0
```

### 2. Verify Java Environment

```powershell
java -version
# Output should show Java 11 or higher
```

If Java is not in your PATH, set `JAVA_HOME`:

```powershell
$env:JAVA_HOME = 'C:\Program Files\Java\jdk-11.0.x'  # Adjust path as needed
```

### 3. Open in Android Studio

1. **File → Open...** and select the repository root
2. Let Android Studio sync Gradle and download SDKs
3. Accept any plugin or Gradle wrapper updates
4. Wait for indexing to complete

### 4. Configure API Keys

The app requires API keys for external services. Add them to `app/src/main/res/values/strings.xml`:

```xml
<!-- Google Civic API Key -->
<string name="civic_api_key">YOUR_CIVIC_API_KEY</string>

<!-- NewsAPI Key -->
<string name="news_api_key">YOUR_NEWS_API_KEY</string>

<!-- Legistar API Key (already in HomeActivity as hardcoded token) -->
```

Get API keys from:
- [Google Civic Information API](https://developers.google.com/civic-information)
- [NewsAPI.org](https://newsapi.org/)

### 5. Build and Run

#### Via Android Studio (Recommended)
1. Connect an Android device (API 24+) or launch an emulator
2. Select `app` in the run configuration dropdown
3. Click the **Run** button (Shift+F10)

#### Via Command Line

```powershell
# Build debug APK
.\gradlew.bat assembleDebug

# Install to connected device/emulator
.\gradlew.bat installDebug
```

### 6. First Launch

1. **Homescreen** appears with Login/Register buttons
2. **Register** a new account with email and password
3. **Login** with created credentials
4. **Set your concerns** to personalize content (optional but recommended)
5. Explore **Home Feed**, **Search**, **Compare**, and other screens

---

## Usage Guide

### Navigation Flow

```
HomescreenActivity (Start)
├── LoginActivity → HomeActivity (if registered user exists)
├── RegisterActivity → HomeActivity (after registration)
└── HomeActivity (Main Dashboard)
    ├── Home Feed (Legislation + News filtered by concerns)
    ├── Elections (Upcoming elections, featured politicians)
    ├── Search (Find and compare politicians)
    ├── Saved Articles (Bookmarked articles)
    ├── Comparison (Side-by-side politician analysis)
    ├── Profile (User account, logout)
    └── Concerns (Edit issue preferences)
```

### Common Workflows

#### 1. Login/Register
1. Launch app → See Homescreen
2. Tap **Register** to create account with email/password
3. Or tap **Login** if you already have account
4. Enter credentials and proceed to Home Feed

#### 2. Search & Compare Politicians
1. Tap **Search** in drawer
2. Type politician name (e.g., "Eric Adams")
3. View results with party affiliation (Rep/Dem/Ind)
4. Tap result → See politician profile
5. Tap **Compare** FAB → Opens comparison screen
6. Tap cards to select left/right politicians
7. Swipe tabs to view different dimensions (Overview, Issues, Contact)
8. Tap swap icon to exchange left/right
9. Tap X to remove and select different politician

#### 3. View & Filter News/Legislation
1. Tap **Home** in drawer → See feed
2. Top shows filtered Legistar legislation (NYC bills)
3. Below shows news articles from NewsAPI
4. Articles filtered by your **Concerns** (if set)
5. Tap heart icon to **bookmark** articles
6. Tap chip to filter by concern category

#### 4. Set Your Concerns (Issue Categories)
1. Tap **Concerns** in drawer
2. See 12 issue categories with chips
3. Tap chip to select interest (selected chips move to top)
4. Selections persist and drive article filtering
5. Back button saves preferences

#### 5. Find Elections & Polling Locations
1. Tap **Elections** in drawer
2. See upcoming elections for NYC area
3. View featured politicians on election cards
4. Each election shows date and location info
5. Featured politicians can be tapped for profile

#### 6. Save Articles for Later
1. Browse articles in **Home** feed
2. Tap heart icon on article → Article bookmarked
3. Tap **Saved** in drawer to view all bookmarked articles
4. Tap article to view details
5. Saved articles persist across app sessions

#### 7. Manage Profile
1. Tap **Profile** in drawer
2. View current user email
3. Tap **Personal Info** to edit name, email, location
4. Tap **Logout** to sign out and return to Homescreen

---

## Project Architecture

### Technology Stack

| Component | Library | Version | Purpose |
|-----------|---------|---------|---------|
| **Android SDK** | AndroidX | Latest | Core Android framework |
| **Networking** | Retrofit | 2.11.0 | HTTP client for APIs |
| **JSON** | Gson | 2.10.1 | JSON serialization |
| **Image Loading** | Glide | 4.16.0 | Efficient image loading/caching |
| **Database** | Room | 2.8.3+ | Type-safe local database |
| **State Management** | ViewModel, LiveData | 2.9.4 | Lifecycle-aware reactive updates |
| **Async** | Coroutines | 1.7.3 | Async operations |
| **UI** | Material Design | 1.13.0 | Modern Material 3 components |
| **Navigation** | DrawerLayout | 1.2.0 | Side navigation menu |
| **Lists** | RecyclerView | 1.3.2 | Efficient scrolling lists |

### Build Configuration
- **compileSdk:** 36 (Android 15)
- **targetSdk:** 36
- **minSdk:** 24 (Android 7.0)
- **Java Version:** 11
- **Kotlin:** 2.0.21

### Directory Structure

```
app/src/main/
├── java/com/example/voteinformed/
│   ├── ui/                          # 9 Activity packages
│   │   ├── home/
│   │   ├── politician/
│   │   ├── search/
│   │   ├── saved/
│   │   ├── elections/
│   │   ├── login/
│   │   ├── user/
│   │   └── concerns/
│   ├── data/                        # Data layer
│   │   ├── entity/                  # Room entities (6 main + relations)
│   │   ├── dao/                     # Data access objects (6 DAOs)
│   │   ├── database/                # VoteInformed_Database.java
│   │   ├── repository/              # 3 repositories
│   │   └── util/                    # Converters, DatabaseClient
│   ├── network/                     # API services
│   │   ├── NewsApiService.java
│   │   ├── CivicApiService.java
│   │   ├── PoliticianApiService.java
│   │   ├── LegistarApiService.kt
│   │   └── Models (Article, Source, etc.)
│   └── model/                       # Request/Response models
├── res/
│   ├── layout/                      # 22 XML activity layouts
│   ├── drawable/                    # Icons, backgrounds
│   ├── values/                      # Colors, strings, dimensions
│   └── menu/                        # Navigation drawer menu XML
└── AndroidManifest.xml
```

### Core Components

#### Data Layer
- **VoteInformed_Database**: Room database with 14 entities
- **6 DAOs**: User_Dao, Article_Dao, Election_Dao, Issue_Dao, Politician_Dao, SavedArticle_Dao
- **3 Repositories**: 
  - `VoteInformed_Repository` - Main repository for all entities
  - `NewsRepository` - News API integration with concern-based filtering
  - `BookmarkRepository` - Article bookmarking via SharedPreferences

#### Network Layer
- **NewsApiService**: News article fetching
- **CivicApiService**: Google Civic API for elections
- **PoliticianApiService**: NYC Council and NY Senate APIs
- **LegistarApiService**: NYC City Council legislation (Kotlin)

#### UI Layer
- **Activities**: One per major feature (no Fragments used currently)
- **ViewModels**: `HomeViewModel`, `PoliticianSearchViewModel`, `PoliticianComparisonViewModel`, `SavedArticleViewModel`, `FeaturedPoliticianViewModel`
- **Adapters**: `ElectionsAdapter`, `FeaturedPoliticianAdapter`, `PoliticianSearchAdapter`, `SavedArticleAdapter`, `PoliticianSelectionAdapter`
- **Dialogs**: `PoliticianSearchDialog` for modal politician selection

#### Authentication
- Email/password login and registration
- SharedPreferences-based session management (user_id storage)
- Room database stores User entity with credentials
- Logout clears session and returns to Homescreen

### Data Flow

```
User Input
    ↓
Activity/ViewModel
    ↓
Repository (decides source: DB or API)
    ↓
┌─────────────────────┬──────────────┐
↓                     ↓
Room Database      Retrofit API
(Cached data)      (Fresh data)
↓                     ↓
└─────────────────────┴──────────────┘
    ↓
   DAO
    ↓
LiveData
    ↓
UI (RecyclerView/CardView/etc.)

---

## Database Schema

### Room Database Configuration
- **Database Class**: `VoteInformed_Database`
- **Version**: 1
- **Total Entities**: 14 (6 main + 8 junction/relationship tables)
- **Type Converters**: `DateConverter` for Date serialization

### Main Entities

| Entity | PK | Key Fields | Purpose |
|--------|----|-----------|----|
| **User** | user_id | name, email, password, location, preference, is_admin | User account data |
| **Politician** | politician_id | name, party, image_url, contact, background, location | Politician profiles |
| **Article** | article_id | article_title, article_url | News articles |
| **SavedArticle** | articleId | title, summary, imageUrl, savedAt (timestamp) | User's bookmarked articles |
| **Election** | election_id | election_name, election_date, location, description | Upcoming elections |
| **Issue** | issue_id | title, description, type, location | Political issues/concerns |

### Relationship Entities (Many-to-Many)
- `User_Article`, `User_Election`, `User_Issue`, `User_Politician`
- `Article_Election`, `Article_Issue`, `Article_Politician`
- `Politician_Election`, `Politician_Issue`

### DAOs (Data Access Objects)
```
User_Dao          - CRUD operations on User entity
Article_Dao       - CRUD operations on Article entity
SavedArticle_Dao  - CRUD operations on SavedArticle entity
Election_Dao      - CRUD operations on Election entity
Issue_Dao         - CRUD operations on Issue entity
Politician_Dao    - CRUD operations on Politician entity
```

### Database Access Pattern
```java
// Get singleton instance
val db = DatabaseClient.getInstance(context).voteinformedDatabase

// Access DAOs
val userDao = db.user_Dao()
val articleDao = db.article_Dao()
// ... etc
```

### Persistence Features
- ✅ Articles cached locally for offline access
- ✅ User data persisted across sessions
- ✅ Bookmarks stored in SavedArticle entity
- ✅ Complex relationships supported for future features
---

## Testing
### Available Tests

**Unit Tests:**
```powershell
.\gradlew.bat test
```
- `ExampleTest.java` - Basic placeholder (2 + 2 = 4)
- Results: `app/build/reports/tests/testDebugUnitTest/index.html`

**Instrumentation Tests:**
```powershell
.\gradlew.bat connectedAndroidTest
```
- `ExampleInstrumentedTest.kt` - Basic placeholder
- Requires connected device or running emulator
- Results: `app/build/reports/androidTests/connected/index.html`

### Debugging Tips

**Logcat (Runtime Logs):**
```
View → Tool Windows → Logcat
```
- Filter by app tag: `voteinformed` or `com.example.voteinformed`
- Search for errors and API calls

**Database Inspector:**
```
View → Tool Windows → Database Inspector
```
- Browse Room database tables in real-time
- Edit data during debugging
- View schema

**Device File Explorer:**
```
View → Tool Windows → Device File Explorer
```
- Navigate to `/data/data/com.example.voteinformed/databases/`
- Download `.db` file for external inspection

**Pull Database via adb:**
```powershell
adb shell run-as com.example.voteinformed cat /data/data/com.example.voteinformed/databases/voteinformed.db > voteinformed.db
```

### Running Specific Tests
```powershell
# Run single unit test
.\gradlew.bat test --tests "com.example.voteinformed.ExampleTest"

# Run tests with pattern matching
.\gradlew.bat test --tests "*Repository*"

# Run with detailed output
.\gradlew.bat test --tests "*" --info
```

---

## Packages & Dependencies

### AndroidX & Core Libraries

| Library | Version | Purpose |
|---------|---------|---------|
| androidx.core:core-ktx | 1.17.0 | Android core extensions |
| androidx.appcompat:appcompat | 1.7.1 | Compatibility library |
| androidx.lifecycle:lifecycle-livedata-ktx | 2.9.4 | LiveData for reactive updates |
| androidx.lifecycle:lifecycle-viewmodel-ktx | 2.9.4 | ViewModel lifecycle management |
| androidx.navigation:navigation-fragment-ktx | 2.9.6 | Navigation framework (declared but not used) |
| androidx.navigation:navigation-ui-ktx | 2.9.6 | Navigation UI helpers |
| androidx.constraintlayout:constraintlayout | 2.2.1 | Flexible layout engine |

### UI & Material Design

| Library | Version | Purpose |
|---------|---------|---------|
| com.google.android.material:material | 1.13.0 | Material Design 3 components |
| androidx.recyclerview:recyclerview | 1.3.2 | Efficient list rendering |
| androidx.drawerlayout:drawerlayout | 1.2.0 | Navigation drawer |

### Networking & Data

| Library | Version | Purpose |
|---------|---------|---------|
| com.squareup.retrofit2:retrofit | 2.11.0 | REST API client (primary) |
| com.squareup.retrofit2:converter-gson | 2.11.0 | JSON conversion |
| com.squareup.okhttp3:logging-interceptor | 4.12.0 | HTTP request/response logging |
| com.google.code.gson:gson | 2.10.1 | JSON serialization/deserialization |
| androidx.room:room-runtime | 2.8.3+ | Local SQLite database |
| androidx.room:room-compiler | 2.8.3+ | Room annotation processor |
| androidx.room:room-common-jvm | Latest | Room JVM support |

### Async & Concurrency

| Library | Version | Purpose |
|---------|---------|---------|
| org.jetbrains.kotlinx:kotlinx-coroutines-android | 1.7.3 | Coroutines for async operations |
| org.jetbrains.kotlinx:kotlinx-coroutines-core | 1.7.3 | Coroutine core library |

### Image Loading

| Library | Version | Purpose |
|---------|---------|---------|
| com.github.bumptech.glide:glide | 4.16.0 | Image loading and caching |
| com.github.bumptech.glide:compiler | 4.16.0 | Glide annotation processor |

### Testing

| Library | Version | Purpose |
|---------|---------|---------|
| junit:junit | 4.13.2 | Unit testing framework |
| androidx.test.ext:junit | 1.3.0 | AndroidX JUnit extensions |
| androidx.test.espresso:espresso-core | 3.7.0 | UI testing framework |
| org.jetbrains.kotlin:kotlin-stdlib | 2.0.21 | Kotlin standard library |

### Kotlin & Gradle

| Component | Version | Purpose |
|-----------|---------|---------|
| Kotlin | 2.0.21 | Language support |
| Gradle | 8.9 | Build system |
| JVM Target | 11 | Java compatibility level |

### Dependency Version Catalog
See `gradle/libs.versions.toml` for centralized version management

---

### 🧪 Testing Gaps

- No unit tests for repositories
- No tests for network calls
- No ViewModel tests
- No database migration tests
- No UI/integration tests (beyond placeholders)
- No authentication flow tests

### Recommended Fixes (Priority Order)

**High Priority:**
1. Hash passwords using bcrypt
2. Move hardcoded API keys to BuildConfig/secure storage
3. Add comprehensive error handling
4. Implement proper unit and integration tests
5. Extract Activity-level API calls to service layer

**Medium Priority:**
6. Consolidate Retrofit versions
7. Implement offline-first caching with expiration
8. Add user feedback for network errors
9. Implement request retries with backoff
10. Migrate to Fragment-based navigation

**Low Priority:**
11. Add analytics/crash reporting
12. Implement dark mode
13. Add localization support
14. User profile pictures
15. Push notifications

---

## API Integration

### Integrated APIs

#### 1. **Google Civic Information API**
- **Purpose**: Elections, polling locations, official information by address
- **Integration**: `CivicApiService.java`
- **Auth**: API Key in `strings.xml`
- **Endpoints**: Elections, polling locations, representatives
- **Usage in App**: ElectionsActivity displays upcoming elections
- **Data Cached**: Yes, in Room database

#### 2. **NewsAPI.org**
- **Purpose**: News article aggregation
- **Integration**: `NewsApiService.java`
- **Auth**: API Key in `strings.xml`
- **Dynamic Filtering**: Constructs queries from user's selected concerns
- **Endpoint**: `/v2/everything` with keyword-based search
- **Usage in App**: HomeActivity displays filtered news feed
- **Caching**: Articles cached in Room for offline access

#### 3. **NYC Open Data (Socrata API)**
- **Base URL**: `https://data.cityofnewyork.us/`
- **Purpose**: NYC City Council member information
- **Integration**: `PoliticianApiService.java`
- **Endpoint**: `/resource/uvw5-9znb.json`
- **Data**: Council members, names, contact info
- **Auth**: Public (no key required)
- **Usage**: Search and politician profiles

#### 4. **New York State Senate API**
- **Base URL**: `https://legislation.nysenate.gov/`
- **Purpose**: NY State legislator information
- **Integration**: `PoliticianApiService.java`
- **Endpoint**: `/api/3/members/2025?full=true`
- **Auth**: API Key (passed as query parameter)
- **Data**: State legislators, positions, info
- **Usage**: Search and politician profiles

#### 5. **Legistar API** (NYC City Council Legislation)
- **Purpose**: Live legislation and bills tracking
- **Integration**: `LegistarApiService.kt` (Kotlin)
- **Auth**: API Token (hardcoded in HomeActivity - **security concern**)
- **Endpoint**: `/v1/nyc/matters`
- **Data**: Bills, proposals, legislation status
- **Usage**: HomeActivity displays current legislation
- **Dynamic Filtering**: Filters bills based on user concerns

### API Service Examples

**NewsApiService.java:**
```java
public interface NewsApiService {
    @GET("v2/everything")
    Call<NewsResponse> searchNews(
        @Query("q") String query,
        @Query("apiKey") String apiKey,
        @Query("language") String language,
        @Query("pageSize") int pageSize
    );
}
```

**PoliticianApiService.java:**
```java
@GET("resource/uvw5-9znb.json")
Call<List<CouncilMember>> getCouncilMembers();

@GET("api/3/members/2025?full=true")
Call<LegislatorResponse> getStateLegislators(@Query("key") String apiKey);
```

**LegistarApiService.kt (Kotlin):**
```kotlin
@GET("api/v1/nyc/matters")
suspend fun getMattersByYear(
    @Query("year") Int,
    @Query("token") String = LEGISTAR_TOKEN
): LegislationResponse
```

### Data Flow & Caching

1. **Network Request**: User triggers action (search, load feed, etc.)
2. **API Call**: Via Retrofit to appropriate service
3. **Response Parsing**: Gson deserializes JSON to entity/model classes
4. **Cache Storage**: Data written to Room database
5. **LiveData Emission**: Repository emits cached data via LiveData
6. **UI Update**: ViewModel observes LiveData, UI updates reactively
7. **Offline Access**: If network unavailable, Room provides cached data
