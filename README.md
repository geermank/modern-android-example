# modern-android-example

## Description
This is a sample app using modern Android components and patterns. In this app you can get a list of Rick and Morty episodes, and for each of them you can see what  characteres appeared in them.

## API
To achieve the objective of this application I used the Rick and Morty API, which you can find here: https://rickandmortyapi.com/documentation/

## Libraries and patterns
Concepts and libraries used for building this app:
- Retrofit (https://square.github.io/retrofit/)
- Moshi (https://github.com/square/moshi)
- MVVM with Jetpack Architecture components (https://developer.android.com/jetpack/guide)
- Hilt for dependency injection (https://developer.android.com/training/dependency-injection/hilt-android)
- Paging2 Library (https://developer.android.com/topic/libraries/architecture/paging)
- Coroutines (https://developer.android.com/kotlin/coroutines)
- Room (https://developer.android.com/training/data-storage/room)
- Picasso (https://square.github.io/picasso/)

For testing I used Mockito and JUnit (https://developer.android.com/training/testing/unit-testing/local-unit-tests), algon with two libraries for coroutine and architecture testing.

This app has 3 modules: 
- app: contains the logic of the View and ViewModels. For this application I used one Activity and two Fragments (one for the episodes list and one for the characters list).
- data: the data layer. It contains the repositories that know how to store data locally and when to fetch it from the api. It has only one source of truth, the local database.
- common: this module has common classes that all modules may use (BaseActivity, BaseViewModel, DateManager, etc.). This module was created because this app will have more functionalities, and another module will be introduced in the near future. 
