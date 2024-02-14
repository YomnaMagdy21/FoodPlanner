plugins {
    id("com.android.application")
    id ("androidx.navigation.safeargs")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.foodplanner"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.foodplanner"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    implementation ("com.google.android.material:material:1.4.0")


    //navigation
    implementation ("androidx.navigation:navigation-fragment:2.5.3")
    implementation ("androidx.navigation:navigation-ui:2.5.3")

    //animation
    implementation ("com.airbnb.android:lottie:3.4.0")

    //Retrofit

    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.7.2")

    implementation ("com.github.bumptech.glide:glide:4.16.0")
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")

    //firebase
    implementation("com.google.firebase:firebase-auth:22.3.1")
    implementation("com.google.firebase:firebase-database:20.3.0")

    implementation ("com.google.android.gms:play-services-auth:20.7.0")
//    implementation ("com.github.shobhitpuri:custom-google-signin-button:2.0.0")

      //room
//    implementation ("androidx.room:room-runtime:2.6.1")
//    annotationProcessor ("androidx.room:room-compiler:2.6.1")

    implementation ("com.github.akarnokd:rxjava3-retrofit-adapter:3.0.0")

    implementation ("io.reactivex.rxjava3:rxandroid:3.0.2")
    implementation ("io.reactivex.rxjava3:rxjava:3.1.6")

    //implementation("androidx.room:room-rxjava2:2.6.1")

    implementation ("androidx.room:room-runtime:2.4.1")
    annotationProcessor ("androidx.room:room-compiler:2.4.1")
    implementation ("androidx.room:room-rxjava3:2.4.1")

    //circularImg
    implementation ("de.hdodenhof:circleimageview:3.1.0")

    //youtube
    implementation ("com.pierfrancescosoffritti.androidyoutubeplayer:core:12.1.0")
    implementation ("com.pierfrancescosoffritti.androidyoutubeplayer:chromecast-sender:0.28")


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}