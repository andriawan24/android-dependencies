package com.andriawan.configuration.utils

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

val Project.libs
    get(): VersionCatalog = getVersionCatalogByName("libs")

private fun Project.getVersionCatalogByName(name: String): VersionCatalog {
    return runCatching {
        extensions.getByType<VersionCatalogsExtension>().named(name)
    }.getOrElse {
        println("Cannot find version catalog named: $name")
        println("Please add version catalog on your settings.gradle.kts first")
        throw it
    }
}