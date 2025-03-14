# Broadcasting and GPS Android Demo

This Android project demonstrates how to implement a dynamic broadcast receiver for monitoring system changes (specifically airplane mode) and how to retrieve the device's last known location using the Fused Location Provider API in Kotlin.

## Overview

This demo application showcases:
- **Dynamic Broadcast Receiver Registration:**  
  Registers a broadcast receiver at runtime to listen for `ACTION_AIRPLANE_MODE_CHANGED` events. When the airplane mode state changes, the app displays a toast indicating "Air On" or "Air Off".

- **GPS Module Integration:**  
  Uses the Fused Location Provider API to retrieve and display the device's last known location upon launch, after proper runtime permission checks.

## Features

- **Dynamic Broadcast Receiver:**
    - Listens for airplane mode changes.
    - Displays a toast message with the current airplane mode state.
    - Can be manually registered and unregistered using on-screen buttons.

- **Location Services:**
    - Retrieves the last known location via `FusedLocationProviderClient`.
    - Displays the latitude and longitude using toast notifications.
    - Handles runtime permission requests for location access.

## Prerequisites

- **Android Studio:** Latest stable version recommended.
- **Android Device/Emulator:** Running API Level 21 (Lollipop) or above.
- **Google Play Services:** Required for location services.

## Download APK

Click below to download the latest version of the app:

[![Download APK](https://img.shields.io/badge/Download-APK-blue?style=for-the-badge&logo=android)](APK/app-debug.apk)

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/BroadcastingAndGPS.git
cd BroadcastingAndGPS
