package com.github.solareye.subotemplate.app

fun dependenciesValues(

) = """
ext {
    def kotlinVersion = "1.6.10"
    def kotlinCoroutine = "1.6.0"
    kotlin = [
            ktStdLib        : "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${'$'}kotlinVersion",
            coroutineCore   : "org.jetbrains.kotlinx:kotlinx-coroutines-core:${'$'}kotlinCoroutine",
            coroutineAndroid: "org.jetbrains.kotlinx:kotlinx-coroutines-android:${'$'}kotlinCoroutine",
    ]

    def androidAnnotationVersion = "1.2.0"
    def androidCoreVersion = "1.6.0"
    def androidFragmentVersion = "1.3.2"
    androidBase = [
            androidAnnotation : "androidx.annotation:annotation:${'$'}androidAnnotationVersion",
            androidCore       : "androidx.core:core-ktx:${'$'}androidCoreVersion",
            fragment          : "androidx.fragment:fragment-ktx:${'$'}androidFragmentVersion",
    ]

    def androidConstraintLayoutVersion = "2.0.4"
    def androidRecyclerViewVersion = "1.2.0"
    def androidCardViewVersion = "1.0.0"
    androidUI = [
            cardView   : "androidx.cardview:cardview:${'$'}androidCardViewVersion",
            constraint : "androidx.constraintlayout:constraintlayout:${'$'}androidConstraintLayoutVersion",
            recycler   : "androidx.recyclerview:recyclerview:${'$'}androidRecyclerViewVersion",
    ]

    def archComponentsVersion = "2.2.0"
    lifeCycle = [
            lifecycleJava8 : "androidx.lifecycle:lifecycle-common-java8:${'$'}archComponentsVersion",
            livedata   : "androidx.lifecycle:lifecycle-livedata-ktx:${'$'}archComponentsVersion",
            viewmodel  : "androidx.lifecycle:lifecycle-viewmodel-ktx:${'$'}archComponentsVersion",
            savedstate : "androidx.lifecycle:lifecycle-viewmodel-savedstate:${'$'}archComponentsVersion",
    ]

    def okHttpVersion = "4.9.0"
    def retrofitVersion = "2.9.0"
    network = [
            okhttp       : "com.squareup.okhttp3:okhttp:${'$'}okHttpVersion",
            okhttpLog    : "com.squareup.okhttp3:logging-interceptor:${'$'}okHttpVersion",
            retrofit     : "com.squareup.retrofit2:retrofit:${'$'}retrofitVersion",
            retrofitGson : "com.squareup.retrofit2:converter-gson:${'$'}retrofitVersion",
    ]

    def smb = "1.2200.30"
    vtbDeps = [
            integration   : "ru.vtb.smb:integration_library:${'$'}smb",
            authorization : "ru.vtb.smb:authorization:${'$'}smb",
            core          : "ru.vtb.smb:core_kit:${'$'}smb",
            utils         : "ru.vtb.smb:utils_kit:${'$'}smb",
            ui            : "ru.vtb.smb:ui_kit:${'$'}smb",
            ui_2_0        : "ru.vtb.smb:ui_kit_2_0:${'$'}smb",
            subo_demo     : "ru.vtb.smb:subo_demo:${'$'}smb",
    ]

    def daggerVersion = "2.37"
    dagger = "com.google.dagger:dagger:${'$'}daggerVersion"
    daggerCompiler = "com.google.dagger:dagger-compiler:${'$'}daggerVersion"

    def lottieVersion = "4.2.1"
    lottie = "com.airbnb.android:lottie:${'$'}lottieVersion"

    def glideVersion = "4.12.0"
    glide = "com.github.bumptech.glide:glide:${'$'}glideVersion"

    cicerone = "com.github.terrakok:cicerone:7.1"

    def materialVersion = "1.3.0"
    material = "com.google.android.material:material:${'$'}{materialVersion}"
}
"""