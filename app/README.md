# Lost & Found

## Project Overview

Lost & Found is a simple Android application built with Kotlin and Jetpack Compose. The app allows users to report lost or found items and manage their status.

The goal of the application is to provide a simple way to keep track of lost and found items locally on the device.

## Technologies

* Kotlin
* Jetpack Compose
* Material 3
* Room Database
* ViewModel
* StateFlow
* Kotlin Coroutines
* Navigation Compose
* Coil

## Main Features

* Add a lost item
* Add a found item
* Item name is required
* Select Lost or Found type
* Add a short description
* Add a location
* Add an optional image
* View recent lost and found items
* Mark an item as resolved
* Delete an item
* Keep resolved items visible with a resolved status
* Store items locally using Room Database

## Setup and Run

1. Clone the repository from GitHub.
2. Open the project in Android Studio.
3. Wait for Gradle synchronization to finish.
4. Connect an Android device or start an Android emulator.
5. Run the application from Android Studio.

## Technical Decisions

### Room Database

Room is used for local data persistence. This allows lost and found items to remain available after restarting the application.

### MVVM

The application uses the MVVM pattern. The ViewModel handles application logic and communicates with the Room DAO, while Compose screens are responsible for displaying the UI.

### StateFlow

StateFlow is used to observe changes in the list of items and automatically update the UI when the database changes.

### Navigation Compose

Navigation Compose is used to navigate between the home screen and the add item screen.

### Image Storage

Images are selected from the device using the system document picker. The image URI is stored as a String in the Room database instead of storing the actual image data.

### Resolved Items

Resolved items remain visible in the list and are marked as `✓ Resolved`. The Resolve and Delete actions are hidden after an item is resolved. This preserves the item's history while keeping the interface simple.

## Optional Features Implemented

* Image selection
* Local data persistence with Room
* Navigation between screens
