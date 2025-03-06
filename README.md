
# 🍪 CookieNote

CookieNote is a modern note-taking Android app built with Kotlin using Jetpack Compose for UI and a robust MVI (Model-View-Intent) architecture. 
The app is backed by a custom backend server and supports both offline and online functionality through Room for local storage and Retrofit for network communication.

## 📱 Features
- ✅ Create, Edit, and Delete Notes
- ✅ Sync notes with a custom backend server
- ✅ Offline access to notes
- ✅ Modern Jetpack Compose UI
- ✅ Separation of concerns using clean architecture (data-domain-presentation)

## ⚙️ Architecture
CookieNote follows a **Clean Architecture** approach with 3 distinct layers:

### 1. Domain Layer
- Contains **pure Kotlin** code.
- Defines all business logic.
- Independent of the Android platform.
- Contains repository interfaces, use cases, and core domain models.

### 2. Data Layer
- Implements the repository interfaces defined in the domain layer.
- Handles **network calls** using **Retrofit**.
- Provides local storage using **Room** database.
- Contains platform-specific implementations (e.g., Android database, network).

### 3. Presentation Layer
- Contains all **UI components** built with **Jetpack Compose**.
- Implements **MVI (Model-View-Intent)** architecture for state management.
- Depends only on the **domain layer**, ensuring UI logic is separated from data management.
- Dependency injection is handled using **Dagger Hilt**, which binds the domain and data layers at runtime.


## Tech Stack
| Layer          | Technology |
|----------------|-------------|
| UI              | Jetpack Compose |
| DI              | Dagger Hilt |
| Local Storage   | Room Database |
| Network         | Retrofit |
| Language        | Kotlin (100%) |

### 📡 Backend Integration
CookieNote connects to a custom backend using Retrofit, handling:

- Authentication
- Syncing notes between local database and server
- Fetching user-specific notes

### 🏛️ Dependency Injection
The app uses Dagger Hilt for dependency injection, ensuring:

- Clear scoping across layers (App scope, ViewModel scope, etc.)
- Seamless injection of platform-specific implementations (e.g., repository implementations from :data into :presentation).

### 🗄️ Local Storage
- Notes are saved locally using Room.
- In offline mode, users can still manage their notes, and changes are synced when back online.

### Cloning the Repo
```bash
git clone https://github.com/your-username/CookieNote.git
cd CookieNote
```

### Building the Project
Open the project in Android Studio and sync the Gradle files. You should be able to build and run the project directly.

## Contributing
Contributions are welcome! Feel free to open issues or submit pull requests.

---
*Happy Coding 🍪*
