
// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        // Các repositories cần thiết, chẳng hạn jcenter, mavenCentral, google()
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.4.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.47")
    }
}

plugins {
//    id("com.android.application") version "8.1.2"
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.android.application") version "7.4.2" apply false
}


