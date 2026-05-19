plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.scr.alertix"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.scr.alertix"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    sourceSets {
        getByName("main") {
            res.srcDirs(
                "src/main/res/layouts/auth",
                "src/main/res/layouts/home",
                "src/main/res"
            )
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    // --- LOMBOK ---
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)

    // --- LIBRERÍAS PARA LA API (Network) ---
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.gson)
    implementation(libs.volley)

    // --- LIBRERÍAS DE DISEÑO E IMÁGENES ---
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.recyclerview)
    implementation(libs.cardview)
    implementation(libs.lottie)
    implementation(libs.picasso)

    // 2. Para cargar fotos de productos (Glide)
    implementation(libs.glide)
    annotationProcessor(libs.glide.compiler)

    // 3. Para pedir permisos de cámara/gps fácil (Dexter)
    implementation(libs.dexter)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
