# Lost & Found App

A simple Android application to track lost and found items.

## Features
- **Add Items**: Report lost or found items with a name, description, and location.
- **Track Status**: Mark items as "Resolved" when they are returned or found.
- **Delete Items**: Remove items from the database.
- **Real-time Updates**: Uses Kotlin Flow and Room Database for reactive UI updates.

## Tech Stack
- **UI**: Jetpack Compose
- **Database**: Room with KSP (Kotlin Symbol Processing)
- **Architecture**: MVVM (ViewModel, Repository pattern)
- **Language**: Kotlin 2.0+

## Getting Started
1. Clone the repository.
2. Open in Android Studio (Ladybug or newer recommended).
3. Build and run the app.

## Project Structure
- `data/`: Room database, DAO, and TypeConverters.
- `model/`: Data classes and Enums.
- `ui/`: Compose screens and components.
- `viewmodel/`: Application logic and state management.
