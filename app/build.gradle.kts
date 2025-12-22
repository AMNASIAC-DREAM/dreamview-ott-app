plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.dreamview.ott"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.dreamview.ott"
        minSdk = 28   // Android 9
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release { isMinifyEnabled = false }
        debug { isMinifyEnabled = false }
    }

    kotlinOptions { jvmTarget = "17" }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
}
