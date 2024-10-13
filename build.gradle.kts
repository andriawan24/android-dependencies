plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    alias(libs.plugins.ksp)
}

dependencies {
    compileOnly(libs.plugin.gradle)
    compileOnly(libs.plugin.kgp)
    compileOnly(libs.plugin.ksp)
}

gradlePlugin {
    val androidAppShared by plugins.creating {
        id = "com.andriawan.shared-android-configuration"
        implementationClass = "com.andriawan.configuration.SharedAndroidConfigurationPlugin"
    }
    val androidApp by plugins.creating {
        id = "com.andriawan.android-configuration"
        implementationClass = "com.andriawan.configuration.AndroidConfigurationPlugin"
    }
}