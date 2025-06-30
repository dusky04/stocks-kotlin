# Stocks

A simple Android application to track stock prices and manage your portfolio.

Resources (including more sample screenshots as well as a demo video): `[Google Drive Link](https://drive.google.com/drive/folders/1rGy5rNoEKm67GmlsXbRe46K9gUitW09P?usp=sharing)`

## Features

- View real-time stock prices.
- Search for stocks.
- Manage a personal watchlist.
- View Top Gainers and Top Losers.

## Building

> [!NOTE]
> Before building the App yourself, add your own Alpha Vantage API Key to `local.properties` under property `API_KEY`

```kt
API_KEY="YOUR_API_KEY"
```

1.  **Clone the repository:**
    ```sh
    git clone https://github.com/dusky04/stocks-kotlin.git
    ```
2.  **Open in Android Studio:**

    - Open Android Studio.
    - Click on "Open" and navigate to the cloned project directory.

3.  **Run the application:**
    - Select an emulator or connect a physical device.
    - Click the "Run" button.

Building using the command line using Gradle:

```sh
# Windows
./gradlew assembleDebug

# macOS/Linux
./gradlew assembleDebug
```

This will generate a debug APK in the `app/build/outputs/apk/debug/` directory.

## Dependencies

This project uses Gradle for dependency management. All required dependencies for the application are listed in the `app/build.gradle.kts` file.

Key libraries include:

- `Jetpack Compose`
- `Kotlin Coroutines`
- `Retrofit` and `okhttp3`

## Screenshots

<img src="screenshots\00_HomeScreenLight.jpg" width="300">
<img src="screenshots\00_HomeScreenDark.jpg" width="300">
<img src="screenshots\01_StockOverviewLight.jpg" width="300">
<img src="screenshots\01_StockOverviewDark.jpg" width="300">
<img src="screenshots\02_NewsScreenLight.jpg" width="300">
<img src="screenshots\02_NewsScreenDark.jpg" width="300">
