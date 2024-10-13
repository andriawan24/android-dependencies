package com.andriawan.configuration

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
            defaultConfig {
                targetSdk = Configs.TARGET_SDK
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }

            when (this) {
                is AppExtension -> buildTypes {
                    getByName("release") {
                        isMinifyEnabled = true
                        isShrinkResources = true
                        debuggable(false)
                    }

                    getByName("debug") {
                        isMinifyEnabled = false
                        isShrinkResources = false
                        debuggable(true)
                    }
                }
            }

            packagingOptions {
                resources {
                    excludes += "/META-INF/{AL2.0,LGPL2.1}"
                }
            }

            project.tasks.withType(KotlinJvmCompile::class.java).configureEach {
                compilerOptions {
                    jvmTarget.set(Configs.JVM_TARGET)
                }
            }
        }
    }
}