plugins {
    id("com.android.application")
    id("kotlin-parcelize")
    id("org.jetbrains.kotlin.kapt")
    id("dagger.hilt.android.plugin")
    kotlin("plugin.serialization") version Versions.kotlinVersion
    kotlin("android")
}

android {
    compileSdkVersion(AppConfig.COMPILE_SDK_VERSION)
    buildToolsVersion(Versions.buildToolsVersion)

    defaultConfig {
        applicationId(AppConfig.APPLICATION_ID)
        minSdkVersion(AppConfig.MIN_SDK_VERSION)
        targetSdkVersion(AppConfig.TARGET_SDK_VERSION)
        versionCode(AppConfig.VERSION_CODE)
        versionName(AppConfig.VERSION_NAME)
        buildConfigField("String", "API_KEY_VALUE", "\"7f7a4723\"")
    }

    buildFeatures {
        viewBinding = true
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
    implementation(Libs.coreKtx)
    implementation(Libs.appCompat)
    implementation(Libs.material)
    implementation(Libs.constraintLayout)
    implementation(Libs.hilt)
    kapt(Libs.hiltCompiler)
    testImplementation(Libs.junit)
    androidTestImplementation(Libs.espresso)
}