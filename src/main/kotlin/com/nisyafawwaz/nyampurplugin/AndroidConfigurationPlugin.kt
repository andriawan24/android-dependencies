package com.nisyafawwaz.nyampurplugin

import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

class AndroidConfigurationPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply("shared-android-configuration")
        val androidExtensions = project.extensions.findByName("android") as? BaseExtension
        androidExtensions?.apply {
            namespace = Configs.ANDROID_APPLICATION_ID
            defaultConfig {
                applicationId = Configs.ANDROID_APPLICATION_ID
                targetSdk = Configs.TARGET_SDK
                versionCode = 1
                versionName = "0.0.1"
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }

            when (this) {
                is LibraryExtension -> defaultConfig {
                    // TODO: Setup proguard consumer here
                }

                is AppExtension -> buildTypes {
                    getByName("release") {
                        isMinifyEnabled = true
                        isShrinkResources = true
                        debuggable(false)
                        // TODO: Setup proguard here
                    }

                    getByName("debug") {
                        isMinifyEnabled = false
                        isShrinkResources = false
                        debuggable(true)
                        // TODO: Setup proguard here
                    }
                }
            }

            packagingOptions {
                resources {
                    // Avoid duplicated licences
                    excludes += "/META-INF/{AL2.0,LGPL2.1}"
                }
            }

            buildFeatures.apply {
                viewBinding = true
            }

            project.tasks.withType(KotlinJvmCompile::class.java).configureEach {
                compilerOptions {
                    jvmTarget.set(Configs.JVM_TARGET)
                }
            }
        }
    }
}