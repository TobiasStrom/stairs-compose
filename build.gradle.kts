import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.diffplug.gradle.spotless.SpotlessExtension

plugins {
    id("com.diffplug.spotless") version("5.14.2")
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.7.1")
        classpath("com.google.firebase:perf-plugin:1.4.0")

        // 4.3.9 ("... need to call FirebaseApp.initializeApp(context) issue
        // https://stackoverflow.com/a/54003634/16261172
        classpath("com.google.gms:google-services:4.3.10")
    }
}

subprojects {
    apply { plugin("com.diffplug.spotless") }
    configure<SpotlessExtension>  {
        kotlin {
            target("**/*.kt")
            ktlint("0.42.1").userData(mapOf(
                "disabled_rules" to "no-wildcard-imports"
            ))
        }
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions.allWarningsAsErrors = true
        kotlinOptions.freeCompilerArgs += listOf(
            "-Xopt-in=kotlin.RequiresOptIn",
            "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-Xopt-in=androidx.compose.material.ExperimentalMaterialApi",
//            "-Xallow-jvm-ir-dependencies"
        )
    }
}