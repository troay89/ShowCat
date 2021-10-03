object Config {

    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"

    object Dependencies {
        const val androidPlugin = "com.android.tools.build:gradle:${Versions.gradle}"
        const val kotlinter = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val navigationSafeArgs =
            "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
        const val daggerHiltAndroidGradle =
            "com.google.dagger:hilt-android-gradle-plugin:${Versions.daggerHilt}"
    }

    object PluginIds {
        const val android = "com.android.application"
        const val kapt = "kotlin-kapt"
        const val kotlinAndroid = "kotlin-android"
        const val kotlinParcelize = "kotlin-parcelize"
        const val daggerHilt = "dagger.hilt.android.plugin"
        const val navigationSafeArgs = "androidx.navigation.safeargs"
        const val detekt = "io.gitlab.arturbosch.detekt"
        const val ktlint = "org.jlleitschuh.gradle.ktlint"
    }
}
