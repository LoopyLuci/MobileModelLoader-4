plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jetbrains.kotlinx.benchmark")
}
android {
    namespace = "dev.luci.mobilemodelloader.app.test"
    compileSdk = 37
    defaultConfig {
        minSdk = 31
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    compilerOptions {
        jvmTarget.set(JavaVersion.VERSION_11)
        freeCompilerArgs += "-Xcontext-receivers"
    }

    kotlin {
        jvmToolchain(11)
    }
}
dependencies {
    androidTestImplementation(project(":app"))
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation(libs.hilt.android.testing)
}
