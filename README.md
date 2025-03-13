This is a Kotlin Multiplatform project targeting Android, iOS.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…

HELPFUL MATERIALS !!! 

KMP:
* https://github.com/terrakok/kmp-awesome?tab=readme-ov-file

Google Auth:
* https://medium.com/@arezoo.nazerdeylami/implementing-google-sign-in-with-kotlin-and-compose-multiplatform-8fad1898b866
* https://euryperez.dev/compose-multiplatform-login-with-google-cc3217a99349
* https://github.com/ktorio/ktor-documentation/blob/3.0.0/codeSnippets/snippets/auth-oauth-google/src/main/kotlin/com/example/oauth/google/Application.kt
* https://hyperskill.org/learn/step/29293#configuring-oauth-in-ktor

Exposed:
* https://ohadshai.medium.com/bits-and-blobs-of-kotlin-exposed-jdbc-framework-f1ee56dc8840
* https://brightinventions.pl/blog/exposed-in-your-project-part-2-dao/
