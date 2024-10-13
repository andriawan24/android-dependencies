package com.andriawan.configuration.utils

import org.gradle.api.Project
import org.gradle.api.artifacts.ExternalModuleDependencyBundle
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.DependencyHandlerScope
import kotlin.jvm.optionals.getOrNull

fun DependencyHandlerScope.implementationWithLog(notation: Provider<MinimalExternalModuleDependency>) {
    add("implementation", notation)
    println("Adding library implementation: ${notation.orNull?.name}")
}

fun DependencyHandlerScope.implementationBundleWithLog(notation: Provider<ExternalModuleDependencyBundle>) {
    add("implementation", notation)
    println("Adding bundle library implementation: ${notation.orNull?.size} added")
}

fun DependencyHandlerScope.implementPlatformWithLog(notation: Provider<MinimalExternalModuleDependency>) {
    add("implementation", platform(notation))
    println("Adding platform library implementation: ${notation.orNull?.name}")
}

fun DependencyHandlerScope.kspWithLog(notation: Provider<MinimalExternalModuleDependency>) {
    add("ksp", notation)
    println("Adding ksp implementation: ${notation.orNull?.name}")
}

fun Project.findLibs(alias: String, isPrintError: Boolean = true): Provider<MinimalExternalModuleDependency>? {
    val dependency = libs.findLibrary(alias).getOrNull()
    if (dependency == null) {
        if (isPrintError) println("Cannot find library $alias on build tools version catalogs, please check version")
    }
    return dependency
}

fun Project.findBundle(alias: String, isPrintError: Boolean = true): Provider<ExternalModuleDependencyBundle>? {
    val dependency = libs.findBundle(alias).getOrNull()
    if (dependency == null) {
        if (isPrintError) println("Cannot find bundle $alias on build tools version catalogs, please check version")
    }
    return dependency
}