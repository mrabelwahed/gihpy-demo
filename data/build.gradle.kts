import dependencies.Dependencies
import dependencies.TestDependencies

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

val giphy_api_key: String by project

android {
    compileSdkVersion(29)
    defaultConfig {
        minSdkVersion(21)
    }
    buildTypes {

        getByName("debug") {
            buildConfigField("String", "GIPHY_API_KEY", giphy_api_key)
        }
        getByName("release") {
            buildConfigField("String", "GIPHY_API_KEY", giphy_api_key)
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(":domain"))
    implementation(Dependencies.kotlin)

    //Retrofit
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofit_gson)

    testImplementation(TestDependencies.junit)
    testImplementation(project(":test:test-common"))
}
