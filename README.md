<h1 align="center">UrlAnts</h1>
<p align="center">  
UrlAnts is an small application using modern android tech stack especialy focuses on Jetpack Compose, the app is build and structured by keeping Clean architecture (Uncle Bob) in mind.
</p>

<p align="center">
<img src="/MOCKUP.png" />
</p>

## Tech stack & Open-source libraries
- Minimum SDK level 21
- 100% [Kotlin](https://kotlinlang.org/) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- Hilt for dependency injection.
- JetPack
  - Compose - A modern toolkit for building native Android UI.
  - Lifecycle - dispose observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
- Architecture
  - MVVM Architecture (Declarative View - ViewModel - Model)
  - [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
  - Material Design & Animations
  - [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs and paging network data.
- API
  - [Ant-Man-Url-Shortner](https://github.com/Vishvajeet590/Ant-Man-Url-Shortner) - Rest api to handle the backend of this application.
