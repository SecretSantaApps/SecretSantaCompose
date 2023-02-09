plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
    id("kotlinx-serialization")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.google.firebase.firebase-perf")
}

android {
    compileSdk = 33
    
    defaultConfig {
        applicationId = "ru.kheynov.santa"
        minSdk = 23
        targetSdk = 33
        versionCode = 10
        versionName = "1.0.1"
        
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.1"
    }
    
    buildTypes {
        getByName("release") {
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
//        isCoreLibraryDesugaringEnabled = true
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.0")
    implementation("com.google.android.material:material:1.8.0")
    
    // Compose
    val composeVersion = "1.3.1"
    val composeCompilerVersion = "1.3.1"
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    implementation("androidx.compose.runtime:runtime:$composeVersion")
    implementation("androidx.compose.compiler:compiler:$composeCompilerVersion")
    
    val navigationVersion = "2.5.3"
    implementation("androidx.navigation:navigation-compose:$navigationVersion")
    
    val hiltNavigationComposeVersion = "1.0.0"
    implementation("androidx.hilt:hilt-navigation-compose:$hiltNavigationComposeVersion")
    
    val activityComposeVersion = "1.6.1"
    implementation("androidx.activity:activity-compose:$activityComposeVersion")
    
    val lifecycleVersion = "2.5.1"
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion")
    
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    
    // Hilt
    kapt("com.google.dagger:hilt-android-compiler:2.44.2")
    implementation("com.google.dagger:hilt-android:2.44.2")
    
    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    
    // UI
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.9")
    
    // Room
    val roomVersion = "2.5.0"
    implementation("androidx.room:room-runtime:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    
    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    
    // Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.4.1")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    
    //SplashScreen
    implementation("androidx.core:core-splashscreen:1.0.0")
    
    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:31.1.1"))
    implementation("com.google.android.gms:play-services-auth:20.4.1")
    implementation("com.firebaseui:firebase-ui-auth:8.0.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.6.4")
    implementation("com.google.firebase:firebase-crashlytics-ktx")
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-perf-ktx")
    
    // OneSignal notification service
    implementation("com.onesignal:OneSignal:[4.0.0, 4.99.99]")
    
    //Glide
    val glideVersion = "1.5.0"
//    implementation("com.github.skydoves:landscapist-glide:$glideVersion")
    
    implementation("androidx.security:security-crypto:1.0.0")
}
