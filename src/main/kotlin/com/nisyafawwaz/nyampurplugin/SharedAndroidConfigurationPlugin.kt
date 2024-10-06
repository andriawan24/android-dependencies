package com.nisyafawwaz.nyampurplugin

import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class SharedAndroidConfigurationPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val androidExtensions = project.extensions.findByName("android") as? BaseExtension
        androidExtensions?.apply {
            namespace = Configs.APPLICATION_ID
            compileSdkVersion(Configs.COMPILE_SDK)

            defaultConfig {
                minSdk = Configs.MIN_SDK
            }

            compileOptions {
                sourceCompatibility = Configs.sourceCompatibility
                targetCompatibility = Configs.targetCompatibility
            }
        }
    }
}