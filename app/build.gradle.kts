plugins {
    id(Config.PluginIds.android)
    id(Config.PluginIds.kotlinAndroid)
    id(Config.PluginIds.kotlinParcelize)
    id(Config.PluginIds.kapt)
    id(Config.PluginIds.daggerHilt)
    id(Config.PluginIds.navigationSafeArgs)
}
apply {
    plugin("kotlin-android")
}

android {

    useLibrary("android.test.mock")
    compileSdk = Versions.compileSdk

    defaultConfig {
        applicationId = Application.id
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = Releases.versionCode
        versionName = Releases.versionName

        testInstrumentationRunner = Config.testRunner
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
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }

    testOptions {
        unitTests.apply {
            isReturnDefaultValues = true
            isIncludeAndroidResources = true
        }
    }

    configurations.all {
        resolutionStrategy {
            force ("androidx.test:monitor:1.4.0")
        }
    }

}

dependencies {

    implementation(Libraries.Supports.coreKtx)
    implementation(Libraries.Supports.appcompat)
    implementation(Libraries.Supports.material)
    implementation(Libraries.Supports.constraintLayout)
    implementation(Libraries.Supports.glide)
    implementation(Libraries.Supports.pagingRuntime)

    implementation(Libraries.Navigation.fragmentKtx)
    implementation(Libraries.Navigation.uiKtx)

    implementation(Libraries.Retrofit.retrofit)
    implementation(Libraries.Retrofit.retrofit2Converter)

    implementation(Libraries.Hilt.hiltAndroid)
    kapt(Libraries.Hilt.daggerHiltCompiler)
    implementation(Libraries.Hilt.hiltLifecycleViewmodel)
    kapt(Libraries.Hilt.hiltHiltCompiler)

    testImplementation(Libraries.Test.junit)
    androidTestImplementation(Libraries.Test.androidJunit)
    androidTestImplementation(Libraries.Test.espressoCore)
    androidTestImplementation(Libraries.Test.mockK)
    androidTestImplementation(Libraries.Test.mockKAndroid)
    androidTestImplementation(Libraries.Test.coroutine)
    androidTestImplementation(Libraries.Test.kotlinJunit)
}