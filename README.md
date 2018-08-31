# Emerald Components Android
This is a base project, created to serve as a Emerald pattern for all future Android applications. 

## Setup

This application uses

* Android Gradle Plugin 3+
* Android API 17+

## Implementation
Guide to implementation [soon]

## Knowledge Stack
> The project uses some concepts and tecnologies below
* Kotlin
* Android SDK

## Test
To test this application whe are using the following technologies
* **JUnit4** for repeatable tests 
* **Espresso** for Android UI interactions tests 
* **Mockito** for mock objects 

## Lint
Android SDK already have a [lint](https://developer.android.com/studio/write/lint.html) 
toolkit so if you want to edit the severity of problems jump to ```lint.xml``` in this project. 
 
You can run the lint manually executing this command ```gradlew lint ``` on terminal.

To see the results go to (PROJECT/app/build/reports/lint-results) and you'll see the file in
 ***HTML*** and ***XML*** extension.

## CI/CD
We're using [Bitrise](https://www.bitrise.io/) as a service because it is optimized 
for mobile applications and we already have a licence.

## Application Design
* Design [soon]
* Workflow [soon]

## Contribute
See the [guide of contribution](CONTRIBUTING.md)
