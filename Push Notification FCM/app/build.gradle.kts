plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.imthiyas.fcm"
    compileSdk = 35


    buildFeatures {
        compose = true
    }
    defaultConfig {
        applicationId = "com.imthiyas.fcm"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    implementation(platform("com.google.firebase:firebase-bom:33.12.0"))


    implementation(platform("androidx.compose:compose-bom:2025.04.01"))


    implementation("com.google.firebase:firebase-messaging")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.moshi:moshi:1.15.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.11.0")

    implementation("androidx.compose.ui:ui")
    implementation("androidx.activity:activity-compose")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.runtime:runtime:1.6.6")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    implementation("androidx.compose.ui:ui-platform")

}