plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {

    viewBinding {
        enable = true
    }
    namespace = "com.imthiyas.rxjava"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.imthiyas.rxjava"
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
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")

    // Converter for JSON (Moshi/Gson)
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // RxJava Adapter for Retrofit
    implementation("com.squareup.retrofit2:adapter-rxjava3:2.9.0")

    // RxJava Core
    implementation("io.reactivex.rxjava3:rxjava:3.1.6")

    // RxAndroid (for Android-specific threading)
    implementation("io.reactivex.rxjava3:rxandroid:3.0.2")
    implementation("com.jakewharton.rxbinding4:rxbinding-core:4.0.0")
}