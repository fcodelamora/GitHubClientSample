# Sample Arch

![Android Studio](https://img.shields.io/badge/Android%20Studio-Jellyfish%202023.3.1-B4D455.svg?style=popout)

Sample application to test and practice new technologies using GitHub API for data.

#### Research items

Research items in this app are listed up in this link:

* [Research Items](./documentation/research_items.md)

#### About tests

Test are made available through the Gradle task `Run Tests` in Android Studio
"Run Configurations".

#### Building the Project & GitHub API key

- You will need to create a `project.properties` file in the project root directory, and
add `apiKeys.githubApiKey="your_api_key_from_github_api"`.
- Java `19` is used to build the project.

## Screenshots

 <img src="/documentation/resources/Screenshots2.png" >

## Android Version

* `minSdkVersion` = 28 (Android 9)
* `targetSdkVersion` = 31 (Android 12)

## Tested Devices

* [Emulator] Pixel 3 (Android 9)
* Samsung S9+ (Android 10) [*Main development device*]
* [Emulator] Pixel 4 (Android 11)
* [Emulator] Pixel 4 (Android 12)

## Architecture

 <img src="/documentation/resources/architecture.png" width="800" align="top">

* Green arrows represent Hilt related dependencies.
* Pink arrows represent dev/mock related dependencies.

### Long-term Objective (WIP)

Make `:core` usable as a multiplatform module. If possible, use JetBrains Compose Multiplatform to
provide and share as many UI components as possible.

#### buildSrc

Handle dependencies and provide custom common dependency scripts (`buildSrc/commons`).
The project local Gradle properties are accessed here. (`project.properties`)

#### Android App (:app)

The Android application main module. A `dev` environment is provided with a mock API to run the app
even if the server is not available.

##### Product Flavors

| flavor  | Description                                                                | 
|---------|----------------------------------------------------------------------------|
| product | Used to create the final deliverable                                       |
| qa      | Flavor that currently hits the API, can be targeted to a stub if necessary |
| dev     | Mocks the API and provides extra debug options for development             |

#### Common (Android)

Provides resources and dependency injection (DI) shared components between the Android `:app`
and its `:features`

##### :common:di

Mainly targets the UseCase provider. Note that datasource and repository DI providers are located in
their respective modules as they are not required to be accessed here. (the UseCase module already
handles that for us).

##### :common:resources

All UI components that are meant to be shared/reused through the different `:feature` and `:app`
modules. (Ex. Buttons, TextViews, Themes, Colors, etc...)
Utility Kotlin extensions to be used in different modules can also be provided here.

This module is expected to evolve once Kotlin Multiplatform is implemented.

#### Core

Contains the business logic and tests. The (work in progress) goal is to make this module reusable
by all targeted operating systems by making it fully multiplatform.

##### :core:entities

Contains the single representation of a real world objects, a concepts or a categories about which
information is stored.

* Real world object : Planet, User
* Concept : User Preferences
* Category : Media type (Video, Photo, ...)

###### A note on Entities

Entities are added as a dependency through the app to reduce the requirement of duplicating entity
like classes, improving consistency of concepts through the app.

##### :core:usecases

Defines the app's specification different use scenarios and actionable items, handling the result of
the execution of said actions. A test case is expected For each provided use case.

##### :core:systems

Interfaces to access functionality provided by the OS (Ex. GPS, device sensors, etc...)

##### :core:repositories

Repository interfaces to access the different datasources.

##### :core:datasources

Interfaces for data persistence and its retrieval

##### :core:apis

Interfaces for the APIs, `Requests` and `Response` classes are defined here. Although APIs are
datasources as a concept, it was considered helpful to encapsulate them in their own module.

#### Provide

This module set ties together the `:core` interfaces to the actual implementation of the OS.

##### :provide:systems

Implementation of `:core:systems` with OS specific code.

##### :provide:repositories

Implementation of `:core:repositories` with OS specific code.

##### :provide:datasources

Implementation of `:core:datasources` with OS specific code.

##### :provide:apis

Implementation of `:core:apis`

**This is a module that might be able to be handled as a Multiplatform module**

##### :provide:mocks

Module set of Mocks for `:core` interfaces.

##### :provide:mocks:apis

In-App implementation of the APIs to allow continuous development even if the Stub of target server
is not available.

**This is a module that might be able to be handled as a Multiplatform module**

#### Features

The app features

##### :features:xxxxx

Actual feature added to the app. (Ex. GitHubClient, an Image viewer, etc...)

##### Technologies & Libraries

* [Kotlin](https://kotlinlang.org/docs/android-overview.html)
* [BuildSrc](https://docs.gradle.org/current/userguide/organizing_gradle_projects.html#sec:build_sources)
* [Gradle KTS](https://docs.gradle.org/current/userguide/kotlin_dsl.html)
* [Jetpack Compose](https://developer.android.com/jetpack/compose)
* [Jetpack Compose - Navigation](https://developer.android.com/jetpack/compose/navigation)
* [Android Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
* [Coil](https://coil-kt.github.io/coil/)
* [okHttp](https://square.github.io/okhttp/)
* [About Libraries](https://github.com/mikepenz/AboutLibraries)
* [Timber](https://github.com/JakeWharton/timber)
* [Moshi](https://github.com/square/moshi)
* [Retrofit](https://square.github.io/retrofit/)
* [Accompanist](https://github.com/google/accompanist)
* [ktlint](https://github.com/JLLeitschuh/ktlint-gradle)
* [PlantUML Integration](https://plugins.jetbrains.com/plugin/7017-plantuml-integration/)
* [Lottie - Compose](https://github.com/airbnb/lottie/blob/master/android-compose.md)
* [Graphviz](https://www.graphviz.org)