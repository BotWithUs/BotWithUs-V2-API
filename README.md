# BotWithUs API

Core API package for BotWIthUs providing essential interfaces and utilities for game interaction.

## Features

### Interface Management
- **Component System**: Robust interface component handling
- **Interface Navigation**: Tools for detecting and interacting with game interfaces
- **Event Handling**: System for managing game events and interface updates

### RS3 Integration
- **Cache Management**: Access to game cache and assets
- **Game State Tracking**: Monitor and respond to client states
- **UI Components**: Interface with game UI elements

### Module System
- **Bot Module Framework**: Create modular bot components with `@BotModuleInfo`
- **Script Management**: Script organization with `@Info` annotation
- **Workspace Integration**: Tools for managing bot workspaces

### Core Utilities
- **Logging System**: Built-in logging capabilities
- **Service Management**: Service loader integration
- **Configuration**: Properties-based configuration system

## Getting Started

### Prerequisites
- Java 22
- Gradle build tool

### Installation

Add the BotWithUs repository to your `build.gradle.kts`:
```kotlin
repositories {
    maven {
        url = uri("https://nexus.botwithus.net/repository/maven-public/")
    }
}

dependencies {
    implementation("net.botwithus:api:latest.release")
}
```

### Basic Usage

Creating a Bot Module:
```java
@BotModuleInfo(
    name = "MyModule",
    author = "YourName",
    description = "Module description",
    version = "1.0"
)
public class MyModule implements BotModule {
    @Override
    public void run() {
        // Module logic here
    }

    @Override
    public boolean isVisible() {
        return true;
    }

    @Override
    public void setVisible(boolean visible) {
        // Visibility logic
    }

    @Override
    public void enable() {
        // Enable logic
    }

    @Override
    public void disable() {
        // Disable logic
    }
}
```

## Package Structure

- `net.botwithus.rs3`: Core RS3 game integration
- `net.botwithus.rs3.interfaces`: Interface and component management
- `net.botwithus.rs3.cache`: Game cache access and management
- `net.botwithus.modules`: Bot module framework
- `net.botwithus.scripts`: Script management
- `net.botwithus.ui`: UI utilities
- `net.botwithus.util`: Common utilities

## Contributing

We welcome contributions! Please ensure your code:
1. Follows Java coding standards
2. Includes appropriate documentation
3. Includes unit tests where applicable

## Support

- [Discord Community](https://discord.gg/botwithus)
- [Documentation](https://docs.botwithus.net)
