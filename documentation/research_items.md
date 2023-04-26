## Research Items

Summary of a few of the things I've been trying out while developing this sample app.

### Mock API inside the App

`:mock:apis` allows for continuous development even if the server is not available, as well as the
definition of custom mock data to be returned to try different data patterns.

### Provide additional development mode only options

With the mock API in place, additional options can be provided to test long response times, and
specific API errors. It should be possible to provide other debug options, ex. launch test
notifications through the Debug menu.

### :common module for shared resources

define a `:common` module for resources that need to be made available to the `:app` and `:feature`
modules.

Kotlin extensions that need to be accessed through the app can also be found here.

## Location of UI components

`:common:resources` is the preferred location for UI Components that are to be utilized through
different modules.

### Jetpack Compose screens

- Separate the screens entry point and the content
- Use state hoisting to delegate as much as possible to the ViewModel

### UI Components Catalogs

`:common:resources`  hosts the building blocks for all screens. These are provided as catalogs.
(Colors, Themes, Button Composables, Text Composables...). A default `CatalogView` is provided to
facilitate `@Preview` creation.

## Pending Items

- Implement internationalization of Strings with a Library that has Multiplatform support within
  their roadmap.
- Refactor `:core` modules as necessary to make it Multiplatform.
- Make `:feature` modules into Android dynamic modules. The current approach to navigation does not
  seem to be compatible with it.
- Provide alternative screens for `Landscape` orientations.
- Check for correct pagination of items.
