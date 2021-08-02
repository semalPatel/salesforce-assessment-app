# Mivi 📺

## Android development

Mivi is an app that attempts to use the latest cutting edge libraries and tools. As a summary:

 * Entirely written in [Kotlin](https://kotlinlang.org/).
 * Uses [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines/coroutines-guide.html) throughout.
 * Uses many of the [Architecture Components](https://developer.android.com/topic/libraries/architecture/), including: Lifecycle, Navigation, View Binding, Data Binding.
 * Uses [Hilt](https://dagger.dev/hilt/) for dependency injection


## Development setup

First off, you require the latest [Android Studio](https://developer.android.com/studio/preview) release to be able to build the app. This is because this app uses many components the latest Android Studio supports (Navigation components, Data binding, View binding)

### API keys

You need to supply API / client key for the various services the app uses:

- [OMDB](http://www.omdbapi.com/)

You can find information about how to gain access via the relevant links.

Once you obtain the key, you can set them in your `~/app/build.gradle`:

### Running and installing the app

Use `./gradlew clean assembleDebug` to compile and `./gradlew installDebug` to install that app on the device.
Run the app with `adb shell am 'start -n com.salesforce.mivi/com.salesforce.mivi.ui.NavigationActivity'`

### Notes on current implementation/Future optimization

* The current implementation has some (only some, I tried my best to extract out most of it :)) business logic in View components, it can be optimized and delegated to specific components.
* Used [Retrofit](https://square.github.io/retrofit/) for making network requests. The main reason behind using Retrofit instead of Volley is that it is very widely used. It has lots of flexibility, robust, has support for many type adaptors.
* For persistence, [Shared Preferences](https://developer.android.com/reference/android/content/SharedPreferences) is used for persisting favorites. As the app evolves, and we need to persist complex object, alternatives can be explored such as [Room](https://developer.android.com/jetpack/androidx/releases/room).
* Most of specs are implemented except for showing some metadata in the search results (director name, plot) because the search api endpoint does not provide the required metadata. For displaying those data, we need to make a network request for each result, and that is inefficient.
* The app is currently only supported in Day mode/Light theme.
* Unit tests are not present, but in the real production app, unit tests along with UI tests should be present.
* UI improvements can be tailored as specs evolves.
