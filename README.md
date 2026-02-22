# 📜 PokeScroll

PokeScroll is a modern Android application built to explore the Pokémon universe. It serves as a practical implementation of Clean Architecture, leveraging the latest Android development tools and best practices.

## 🛠️ Tech Stack
* Language: **Kotlin**
* UI Framework: **Jetpack Compose** (Declarative UI)
* Dependency Injection: **Hilt** (Standardized DI for Android)
* Networking: **Retrofit + Gson**
* Image Loading: **Coil** (Kotlin-first image loading)
* Asynchronous Logic: **Kotlin Coroutines & Flow**
* Architecture: **Clean Architecture** with **MVI** (Model-View-Intent) principles.

## 🏗️ Architecture Overview
The project is divided into three distinct layers to ensure separation of concerns, scalability, and testability:

### Domain Layer
The core of the application. It contains the Business Logic.
* Entities: Pure Kotlin data models (Pokemon). 
* Use Cases: Single-responsibility components that perform specific tasks (e.g., GetPokemonDetails). 
* Repository Interfaces: Contracts that define how data should be handled, keeping the domain layer independent of external frameworks.

### Data Layer
The implementation of the data requirements defined by the Domain layer. 
* DTOs (Data Transfer Objects): Models that match the PokeAPI JSON structure. 
* Mappers: Functions that convert DTOs into Domain Entities. 
* Repository Implementation: Logic to fetch data from the network (Retrofit) or local storage (Room).

### Presentation Layer
The UI and State management. 
* ViewModels: Expose StateFlow to the UI and interact with Use Cases. 
* UI (Compose): Stateless and stateful composables that react to state changes. 
* Navigation: Type-safe navigation for seamless screen transitions.

## 🚦 Getting Started
### Prerequisites 
* Android Studio Ladybug or newer. 
* JDK 17+.

### Installation
1. Clone the repository: Bash `git clone https://github.com/andreea-sicaru/pokescroll.git`
2. Open the project in Android Studio. 
3. Sync Gradle and run the app on an emulator or physical device.

## 🗺️ Roadmap
✅Initial Clean Architecture setup.

✅ Hilt Dependency Injection. 

✅ Network integration with PokeAPI. 

⏳ Type-safe Navigation setup. 

⏳ Local persistence for Favourites (Room). 

⏳ Pagination for the Home list.