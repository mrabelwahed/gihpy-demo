import dependencies.Dependencies
import dependencies.TestDependencies

plugins {
    id("kotlin")
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Dependencies.kotlin)

    testImplementation(project(":test:test-common"))
    testImplementation(TestDependencies.junit)
    testImplementation(TestDependencies.mockito)
    testImplementation(TestDependencies.mockitoKotlin)
}
