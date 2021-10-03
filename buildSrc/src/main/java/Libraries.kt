object Libraries {

    object Supports {
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val appcompat = "com.google.android.material:material:${Versions.appcompat}"
        const val material = "com.google.android.material:material:${Versions.material}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val pagingRuntime = "androidx.paging:paging-runtime-ktx:${Versions.paging}"
    }

    object Navigation {
        const val fragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val uiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit2}"
        const val retrofit2Converter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit2}"

    }

    object Hilt {
        const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.daggerHilt}"
        const val daggerHiltCompiler = "com.google.dagger:hilt-compiler:${Versions.daggerHilt}"
        const val hiltLifecycleViewmodel =
            "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.androidxHilt}"
        const val hiltHiltCompiler = "androidx.hilt:hilt-compiler:${Versions.androidxHilt}"
    }

    object Test {
        const val junit = "junit:junit:${Versions.junit}"
        const val androidJunit = "androidx.test.ext:junit:${Versions.androidxJunit}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
        const val mockK = "io.mockk:mockk:${Versions.mockkVersion}"
        const val mockKAndroid = "io.mockk:mockk-android:${Versions.mockkVersion}"
        const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutineTestVersion}"
        const val kotlinJunit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    }
}
