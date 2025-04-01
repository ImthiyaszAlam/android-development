
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.dagger.hilt.android")
    id("kotlinx-serialization")
    kotlin("kapt")
}

android {
    namespace = "com.imthiyas.cricradio"
    compileSdk = 35

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }

    defaultConfig {
        applicationId = "com.imthiyas.cricradio"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.runtime.android)
    implementation(libs.androidx.foundation.android)
    implementation(libs.androidx.material3.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Jetpack Compose BOM (Manages Compose versions)
    implementation(platform("androidx.compose:compose-bom:2025.02.00"))

// Compose UI & Material 3
    implementation(libs.androidx.ui)
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.activity:activity-compose:1.10.1")

// Navigation Component for Compose
    implementation("androidx.navigation:navigation-compose:2.8.8")


    // Retrofit for API calls
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.google.code.gson:gson:2.11.0")

// OkHttp for logging API requests
    implementation(libs.logging.interceptor)

// WebSockets (For Real-Time Score Updates)
   /* implementation(libs.scarlet)
    implementation(libs.websocket.okhttp)
    implementation(libs.message.adapter.gson)*/



    // ViewModel for state management
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")

// LiveData & Flow for real-time updates
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.7")

    // Hilt for Dependency Injection
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

// Hilt for Jetpack Compose
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    // Room Database (For caching scores locally)
    implementation(libs.androidx.room.runtime)
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")

// DataStore (For storing preferences like last fetched scores)
    implementation("androidx.datastore:datastore-preferences:1.1.3")

    // Kotlin Coroutines
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)

    // Timber for better logging
    implementation("com.jakewharton.timber:timber:5.0.1")
    implementation(libs.androidx.material.icons.extended)

    // Ktor (Networking)
    implementation("io.ktor:ktor-client-core:2.3.5")
    implementation("io.ktor:ktor-client-cio:2.3.5") // or ktor-client-okhttp

    // Ktor Content Negotiation and JSON Support
    implementation("io.ktor:ktor-client-content-negotiation:2.3.5")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.5") // ✅ Use this instead of ktor-client-json

    // Kotlinx Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")


}