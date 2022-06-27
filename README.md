# TVMaze App

A simple android app that uses TVMaze API to list and search for TV shows.

## Specifications

The app has a home screen that lists all the shows on TVMaze, a search option to filter these shows,
and on selecting a show it displays the show and it's episodes information.

The app uses the following API: [TVMaze](https://www.tvmaze.com/api).

## Technologies used

* The app was developed with Kotlin.
* Asynchronous tasks are handled using Coroutines and Flows.
* Network requests are handled by Retrofit and Moshi.
* Image loading is done through Coil.
* The Navigation component is used for handling in-app navigation.
* Koin is used for dependency injection.
* The UI was mainly developed using Compose.

## Architecture

* The project follows Clean Architecture, and is divided in the following layers: "data", "domain",
"presentation".

    * Domain: contains the classes related to business rules, without dependencies on external
      frameworks. Define the models used in the app and the repositories' contracts (interfaces).

    * Data: responsible for handling the loading of the data to be used in the app. Define the DTOs
      used to receive data from external APIs. Implements the repository interfaces and maps the DTOs
      to business models, abstracting their source.

    * Presentation: responsible for handling user interactions and displaying the relevant
      information to the user.

* The project is split into multiple packages (main/show/episode/di). Each one is split into inner
packages that follow the layers explained above, with the exception on the "di" package, that glues
together the app dependencies.

* The presentation layer follow MVVM architecture. where each screen has a view model responsible
for managing it's state. The view model interacts with the domain layer, removing logic from the
view itself.