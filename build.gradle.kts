buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath (Config.Dependencies.androidPlugin)
        classpath (Config.Dependencies.kotlinter)
        classpath (Config.Dependencies.daggerHiltAndroidGradle)
        classpath (Config.Dependencies.navigationSafeArgs)
    }

}

plugins {
    id (Config.PluginIds.detekt).version(Versions.detektV)
    id (Config.PluginIds.ktlint).version(Versions.ktlintV)
}

subprojects {
    apply (plugin = "org.jlleitschuh.gradle.ktlint")
    ktlint {
        debug.set(false)
    }
}

detekt {
    toolVersion = "1.18.1"
    config = files("config/detekt/detekt.yml")
    buildUponDefaultConfig = true

    source = files("app/src/main/java", "app/src/main/kotlin")

    reports {
        html {
            enabled = true
            destination = file("app/build/detekt/detekt.html")
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

tasks.register("getHomeDir") {
    println("Gradle home dir: ${gradle.gradleHomeDir}")
}
