import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.hamza.weatherapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.hamza.weatherapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())

        buildConfigField("String", "WEATHER_API_KEY", properties.getProperty("WEATHER_API_KEY"))
        buildConfigField("String", "BASE_URL", "\"http://api.openweathermap.org/data/2.5/\"")
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
//    packaging {
//        resources {
//            excludes += "/META-INF/gradle/incremental.annotation.processors"
//            excludes += "/META-INF/{AL2.0,LGPL2.1}"
//        }
//    }
}

dependencies {
    // AndroidX Libraries
    implementation(libs.bundles.androidx)

    // Retrofit Libraries
    implementation(libs.bundles.retrofit)

    // Hilt Libraries
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Coil Library
    implementation(libs.bundles.coil)

    // Testing Libraries
    testImplementation(libs.bundles.testing)
    androidTestImplementation(libs.bundles.testing)

    // Room Libraries
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    implementation("androidx.room:room-ktx:2.6.1")
}