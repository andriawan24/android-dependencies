package com.andriawan.configuration

import com.andriawan.configuration.utils.applyPluginWithLog
import com.andriawan.configuration.utils.findBundle
import com.andriawan.configuration.utils.findPlugin
import com.andriawan.configuration.utils.implementationBundleWithLog
import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

class AndroidConfigurationPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            // Define plugins
            val kotlinAndroidPlugin = findPlugin("kotlin-android") ?: return
            pluginManager.applyPluginWithLog(kotlinAndroidPlugin)

            val androidApplicationPlugin = findPlugin("android-application") ?: return
            pluginManager.applyPluginWithLog(androidApplicationPlugin)

            val androidSharedConfiguration = findPlugin("configuration-shared-android") ?: return
            pluginManager.applyPluginWithLog(androidSharedConfiguration)

            // Define default android plugin configuration
            (extensions.findByName("android") as? BaseExtension)?.apply {
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

                tasks.withType(KotlinJvmCompile::class.java).configureEach {
                    compilerOptions {
                        jvmTarget.set(Configs.JVM_TARGET)
                    }
                }
            }

            // Define libraries
            dependencies {
                findBundle("android-core")?.also { implementationBundleWithLog(it) }
            }
        }
    }
}