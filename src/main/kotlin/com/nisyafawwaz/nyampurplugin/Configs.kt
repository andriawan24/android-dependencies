package com.nisyafawwaz.nyampurplugin

import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

object Configs {
    const val APPLICATION_ID = "id.nisyafawwaz.nyampur"
    const val ANDROID_APPLICATION_ID = "id.nisyafawwaz.nyampur.android"
    const val MIN_SDK = 24
    const val TARGET_SDK = 35
    const val COMPILE_SDK = 35
    val JVM_TARGET = JvmTarget.JVM_18
    val sourceCompatibility = JavaVersion.VERSION_18
    val targetCompatibility = JavaVersion.VERSION_18
}