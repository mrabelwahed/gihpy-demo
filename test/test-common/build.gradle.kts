import dependencies.Dependencies
import dependencies.TestDependencies

plugins {
    id("kotlin")
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Dependencies.kotlin)
    implementation(Dependencies.coroutines_core)

    implementation(TestDependencies.junit)
    implementation(TestDependencies.coroutines_test)
}
