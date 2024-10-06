plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

gradlePlugin {
    plugins {
        create("shared-android-configuration") {
            id = "shared-android-configuration"
            implementationClass = "com.nisyafawwaz.nyampurplugin.SharedAndroidConfigurationPlugin"
        }

        create("android-configuration") {
            id = "android-configuration"
            implementationClass = "com.nisyafawwaz.nyampurplugin.AndroidConfigurationPlugin"
        }
    }
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("com.android.tools.build:gradle:8.7.0")
    implementation(kotlin("gradle-plugin", "2.0.20"))
    implementation(kotlin("android-extensions"))
    implementation(kotlin("script-runtime"))
}