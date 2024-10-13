package com.andriawan.configuration.utils

import org.gradle.api.Project
import org.gradle.api.plugins.PluginManager
import kotlin.jvm.optionals.getOrNull

fun PluginManager.applyPluginWithLog(pluginId: String) {
    apply(pluginId)
    println("Applying plugin: $pluginId")
}

fun Project.findPlugin(alias: String): String? {
    val plugin = libs.findPlugin(alias).getOrNull()?.orNull?.pluginId
    if (plugin == null) {
        println("Plugin $alias cannot be found on your build tools, please check version")
    }
    return plugin
}