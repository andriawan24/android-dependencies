package com.andriawan.configuration

import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

object Configs {
    const val MIN_SDK = 24
    const val TARGET_SDK = 35
    const val COMPILE_SDK = 35
    val JVM_TARGET = JvmTarget.JVM_17
    val sourceCompatibility = JavaVersion.VERSION_17
    val targetCompatibility = JavaVersion.VERSION_17
}