package com.github.solareye.subotemplate.app

fun dependenciesGradle(

) = """
ext {
    def smb = "2.2304.11"
    vtbDeps = [
            subo_demo     : "ru.vtb.smb:subo_demo:${'$'}smb",
            subo_common   : "ru.vtb.smb:subo_common:${'$'}smb"
    ]

    def daggerVersion = "2.43.2"
    daggerCompiler = "com.google.dagger:dagger-compiler:${'$'}daggerVersion"

    def lottieVersion = "4.2.1"
    lottie = "com.airbnb.android:lottie:${'$'}lottieVersion"

    def glideVersion = "4.12.0"
    glide = "com.github.bumptech.glide:glide:${'$'}glideVersion"
}
"""