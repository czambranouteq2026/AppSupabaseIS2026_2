import java.util.Properties
val props = Properties().apply {
    rootProject.file("local.properties").inputStream().use { load(it) }
}
val url = props.getProperty("SUPABASE_URL")
    ?: error("Falta SUPABASE_URL en local.properties")
val key = props.getProperty("SUPABASE_KEY")
    ?: error("Falta SUPABASE_KEY en local.properties")

plugins {
    alias(libs.plugins.android.application)
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    buildFeatures {
        buildConfig = true
    }
    defaultConfig {
        buildConfigField("String", "SUPABASE_URL", "\"$url\"")
        buildConfigField("String", "SUPABASE_KEY", "\"$key\"")
    }
    namespace = "com.uteq.software.appsupabaseis2026_2"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.uteq.software.appsupabaseis2026_2"
        minSdk = 34
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = true
                packageScope = setOf("androidx.**", "kotlin.**", "kotlinx.**")
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(platform("io.github.jan-tennert.supabase:bom:3.6.0"))
    implementation("io.github.jan-tennert.supabase:postgrest-kt")
    implementation("io.ktor:ktor-client-android:3.5.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.2")
    implementation("com.google.android.material:material:1.12.0")

    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}