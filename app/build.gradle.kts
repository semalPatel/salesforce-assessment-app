plugins {
    id("com.android.application")
    id("kotlin-parcelize")
    id("org.jetbrains.kotlin.kapt")
    id("dagger.hilt.android.plugin")
    kotlin("plugin.serialization") version Versions.kotlinVersion
    kotlin("android")
}

android {
    compileSdkPreview = AppConfig.COMPILE_SDK_VERSION
    buildToolsVersion = Versions.buildToolsVersion

    defaultConfig {
        applicationId = AppConfig.APPLICATION_ID
        minSdkPreview = AppConfig.MIN_SDK_VERSION
        targetSdkPreview = AppConfig.TARGET_SDK_VERSION
        versionCode = AppConfig.VERSION_CODE
        versionName = AppConfig.VERSION_NAME
        buildConfigField("String", "API_KEY_VALUE", "\"7f7a4723\"")
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(Libs.ktxAndroidExtensions)
    implementation(Libs.kotlinXSerialization)
    implementation(Libs.retrofitSerializationConverter)
    implementation(Libs.activity)
    implementation(Libs.retrofit)
    implementation(Libs.viewModel)
    implementation(Libs.kotlinStdLib)
    implementation(Libs.coroutinesCore)
    implementation(Libs.coroutinesAndroid)
    implementation(Libs.appCompat)
    implementation(Libs.material)
    implementation(Libs.glide)
    implementation(Libs.constraintLayout)
    implementation(Libs.hilt)
    implementation(Libs.fragmentNavigation)
    implementation(Libs.navigationUi)
    kapt(Libs.hiltCompiler)
    testImplementation(Libs.junit)
    androidTestImplementation(Libs.espresso)
}