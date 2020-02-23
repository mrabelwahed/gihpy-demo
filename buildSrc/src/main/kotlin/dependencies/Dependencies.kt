package dependencies

object Dependencies {
    // Core
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin_gradle_plugin}"

    // Coroutines
    const val coroutines_core =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutines_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    // androidx
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    const val navigation_fragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigation_ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val paging = "androidx.paging:paging-runtime:${Versions.paging}"
    const val constraint_layout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout}"
    const val swiperefresh_layout =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swiperefresh_layout}"
    const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx}"

    // Networking
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val okHttp_logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    const val retrofit_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit_gson}"
    const val google_gson = "com.google.code.gson:gson:${Versions.google_gson}"

    // Dagger
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"

    // Lifecycle
    const val lifecycle_extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val lifecycle_viewmodel_ktx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

    // Glide
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
}

object TestDependencies {
    // Core library
    const val arch_core = "androidx.arch.core:core-testing:${Versions.arch_core}"
    const val junit = "junit:junit:${Versions.junit}"

    const val coroutines_test =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines_test}"

    // AndroidJUnitRunner and JUnit Rules
    const val runner = "androidx.test:runner:${Versions.runner}"
    const val rules = "androidx.test:rules:${Versions.rules}"

    // Assertions
    const val android_junit = "androidx.test.ext:junit:${Versions.android_junit}"

    // Espresso dependencies
    const val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso_core}"
    // Kakao
    const val kakao = "com.agoda.kakao:kakao:${Versions.kakao}"

    // Third party
    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
}
