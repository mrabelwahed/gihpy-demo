import dependencies.Dependencies
import dependencies.TestDependencies

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "com.tarek360.giphy"
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "com.tarek360.giphy.ui.UiRunner"
    }
    buildTypes {

        getByName("debug") {
            buildConfigField("String", "GIPHY_API_BASE_URL", "\"https://api.giphy.com/\"")
        }
        getByName("release") {
            isMinifyEnabled = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "GIPHY_API_BASE_URL", "https://api.giphy.com/")
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Dependencies.kotlin)

    implementation(project(":data"))
    implementation(project(":domain"))

    implementation(Dependencies.appcompat)
    implementation(Dependencies.material)
    implementation(Dependencies.recyclerview)
    implementation(Dependencies.navigation_fragment)
    implementation(Dependencies.navigation_ui)
    implementation(Dependencies.paging)
    implementation(Dependencies.constraint_layout)
    implementation(Dependencies.swiperefresh_layout)
    implementation(Dependencies.core_ktx)

    implementation(Dependencies.google_gson)

    implementation(Dependencies.dagger)
    kapt(Dependencies.dagger_compiler)

    implementation(Dependencies.coroutines_core)
    implementation(Dependencies.coroutines_android)

    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofit_gson)
    implementation(Dependencies.retrofit_gson)
    implementation(Dependencies.okHttp_logging)

    implementation(Dependencies.lifecycle_extensions)
    implementation(Dependencies.lifecycle_viewmodel_ktx)

    implementation(Dependencies.glide)

    testImplementation(project(":test:test-common"))
    testImplementation(TestDependencies.junit)
    testImplementation(TestDependencies.mockito)
    testImplementation(TestDependencies.mockitoKotlin)
    testImplementation(TestDependencies.coroutines_test)
    testImplementation(TestDependencies.arch_core)

    androidTestImplementation(TestDependencies.android_junit)
    androidTestImplementation(TestDependencies.rules)
    androidTestImplementation(TestDependencies.runner)
    androidTestImplementation(TestDependencies.espresso_core)
    androidTestImplementation(TestDependencies.kakao)
}
