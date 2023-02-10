## Artyst 🎵
Artyst is a project that shows modern Android development: with Hilt, Coroutines, Flow, Jetpack with the new Material Design 3 guidelines.

![](https://user-images.githubusercontent.com/3328096/218131946-f3e107b9-9b55-4ee6-885a-897a74fca41b.png)

## Tech stack
- Minimum SDK 23
- 100% Kotlin
- Multi-module
- [Material Design 3](https://m3.material.io)
- Dark/Light mode support
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous operations
- [HILT](https://developer.android.com/training/dependency-injection/hilt-android) for Dependency Injection
- Architecture
    - MVVM
    - Clean Architecture
    - Repository Pattern
- Jetpack
    - [Navigation](https://developer.android.com/guide/navigation): For handling Navigation inside the app
    - [LifeCycle](https://developer.android.com/topic/libraries/architecture/lifecycle): For managing UI related data in a LifeCycle conscious way
    - [ViewBinding](https://developer.android.com/topic/libraries/view-binding): For binding UI views into controllers (like Fragments, Activities)
- [Retrofit / OkHttp3](https://github.com/square/retrofit): For performing network request
- [Robolectric](http://robolectric.org): For Unit Test
- [Mockito](https://site.mockito.org): Mocking framework for Unit Test
- [Turbine](https://github.com/cashapp/turbine): Turbine is a small testing library for kotlinx.coroutines Flow.
- [Glide](https://bumptech.github.io/glide/): For network image loading
- Material Components: For building the UI
- [Ktlint](https://ktlint.github.io): For code-formatting and for keeping the code style consistent across the project
- MotionLayout for layout animation
- [Lottie](http://airbnb.io/lottie/#/) for vectors animations

## Layer Architecture
This app is based on MVVM architecture and follows Clean Architecture principles with the repository pattern

![Architecture Diagram](https://user-images.githubusercontent.com/3328096/218133374-c433417d-ce3e-4f73-896a-1fa00239ec67.png)

## Modularisaton
![Artyst Modules](https://user-images.githubusercontent.com/3328096/218133900-ebf32ce6-5571-4fc5-a887-814c3eec9d19.png)

- **app**: Is the entrypoint of the application. It contains the main navigation graph, the app icon and the MainActivity for the Single-Activity pattern.
- **core**: Is shared with all the modules and contains shared logic, util classes, extension functions and shared UI components (in a larger project I would consider moving all the UI shared views into a dedicated `:design` module)
- **data**: All the data-related logic belongs to this module. It contains the Network implementation and the Repositories implementations from the `:domain` module.
- **domain**: Contains all the business logic, the repositories interfaces and the use-cases
- **feature**: Is the presentation layer and contains all the features of the app like Search and Artist Detail.

## Design
For designing this app, I used Figma. You can find the artboards of the project on the [following link](https://www.figma.com/file/fbm1V854CeRgjG3AT5oybW/Artyst?node-id=1%3A35&t=C9cJNQDI3zEDU9dV-1)

## Areas of improvements
There are areas where the project can be improved.
- Pagination on Search feature
- UI Test coverage
- Hardcoded strings in some mappers