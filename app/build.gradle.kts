import java.util.Properties

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.google.firebase.firebase-perf")
    id("me.tadej.versioning") version("0.2.0")
}

val composeVersion = "1.0.4"
android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.tobiasstrom.stairs"
        minSdk = 23
        targetSdk = 31
        versionCode = versioning.versionCode()
        versionName = versioning.versionName()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    val propsFile = rootProject.file("keys/release/signing.properties")
    val hasReleaseSigningConfig = propsFile.isFile
    signingConfigs {
        if(hasReleaseSigningConfig) {
            val props = Properties().apply {
                load(propsFile.reader())
            }
            create("release") {
                storeFile = rootProject.file(props.getProperty("storeFile"))
                storePassword = props.getProperty("storePassword")
                keyPassword = props.getProperty("keyPassword")
                keyAlias = props.getProperty("keyAlias")
            }
        }
    }

    buildTypes {
        if(hasReleaseSigningConfig) {
            getByName("release") {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
                signingConfig = signingConfigs.getByName("release")
            }
        }
    }

    lint {
        textReport = true
        lintConfig = rootProject.file("lint.xml")
        isCheckDependencies = true
        isCheckTestSources = true
        isExplainIssues = true
        isCheckReleaseBuilds = true
        isAbortOnError = true
        disable(
            "UnusedIds", "StaticFieldLeak", "UnusedResources", "ObsoleteLintCustomCheck"
        )
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = composeVersion
    }

    packagingOptions {
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
    }
}

dependencies {
    // Core
    implementation("androidx.core:core-ktx:1.6.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")

    // Material Design
    implementation("com.google.android.material:material:1.4.0")

    // Compose
    implementation("androidx.compose.runtime:runtime:$composeVersion")
    implementation("androidx.activity:activity-compose:1.3.1")
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.foundation:foundation:$composeVersion")
    implementation("androidx.compose.foundation:foundation-layout:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.material:material-icons-extended:$composeVersion")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.0-rc01")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0-rc01")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.4.0-alpha10")

    // Accompanist
    val accompanistVersion = "0.16.1"
    implementation("com.google.accompanist:accompanist-pager:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-pager-indicators:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion")

    // Coroutines
    val coroutinesVersion = "1.5.0"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:28.1.0"))
    implementation("com.google.firebase:firebase-perf-ktx")
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-config")
    implementation("com.google.firebase:firebase-messaging")
    implementation("com.google.firebase:firebase-crashlytics")

    // Koin
    val koinVersion = "3.1.2"
    implementation("io.insert-koin:koin-core:$koinVersion")
    implementation("io.insert-koin:koin-android:$koinVersion")
    implementation("io.insert-koin:koin-androidx-compose:$koinVersion")

    // Datastore Preferences
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Google Play
    implementation ("com.google.android.play:core-ktx:1.8.1")

    // Timber
    implementation("com.jakewharton.timber:timber:5.0.1")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling:$composeVersion")

    implementation(kotlin("reflect"))
}