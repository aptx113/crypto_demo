# crypto_demo
It's a demonstrative app about Crypto. Parsing Json file in assets to obtain list of CurrencyInfo in order to display in Fragment via Activity and db.
The master branch uses ShareViewModel to let Activity communicate with Fragment, based on [doc](https://developer.android.com/guide/fragments/communicate#host-activity).
Another branch uses bundle to let Activity provide dataset to Fragment.

This Repo also adds tests to test ViewModel, Repository, and Dao, etc.

## Feature
1. Display list of CurrencyInfo
2. Sort CurrencyInfo

## Tech stack & Open-source libraries
- Minimum SDK level 26
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) - For writing Gradle build scripts using Kotlin.
- Jetpack
  - [Databinding](https://developer.android.com/topic/libraries/data-binding) - Bind UI components in the layouts to data sources in the app.
  - [Hilt](https://dagger.dev/hilt/) - For dependency injection.
  - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - Build lifecycle-aware components, like ViewModel and LiveData that can automatically adjust their behavior based on the current lifecycle state of an activity or fragment.
  - [Recyclerview](https://developer.android.com/guide/topics/ui/layout/recyclerview) - Use to efficiently display large sets of data.
  - [Room](https://developer.android.com/training/data-storage/room) - Construct a database using the abstract layer for offline cache.
  - [Test](https://developer.android.com/jetpack/androidx/releases/test) - For unit tests and instrumented tests
  - [Truth](https://truth.dev/) - For tests' assertions
- Third Party Library
  - [Coil](https://github.com/coil-kt/coil) - Loading images.
  - [Mockk](https://mockk.io/) - For mock object in tests
  - [Moshi](https://github.com/square/moshi) - Parse and convert a JSON object into Kotlin objects.
  - [Timber](https://github.com/JakeWharton/timber) - For logging.
- [Material](https://github.com/material-components/material-components-android) - Help to build material components like bottom navigation bar, floating action button.
- [detekt](https://github.com/detekt/detekt) - Use static code analysis tool for the Kotlin to improve code smell.
- [Ktlint](https://github.com/JLLeitschuh/ktlint-gradle) - To make code follow Kotlin official code style.
- Architectural and Design pattern
  - MVVM
  - Observer
  - Adapter
  - Dependency Injection
  - Singleton

## Contact
<danteyu.studio@gmail.com>

Dante Yu
