plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
}

android {
    namespace = "com.mhmdnurulkarim.githubuser"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mhmdnurulkarim.githubuser"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "0.1.0"
        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        debug {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    buildFeatures{
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    dynamicFeatures += setOf(":favorite")
}

dependencies {
    implementation(project(":core"))
    ksp("androidx.room:room-compiler:2.6.1")
}