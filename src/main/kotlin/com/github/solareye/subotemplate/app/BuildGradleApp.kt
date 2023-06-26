package com.github.solareye.subotemplate.app

fun buildGradleApp(
    applicationId: String,
    featureModuleName: String,
) = """
    plugins {
        id 'com.android.application'
        id 'kotlin-android'
        id 'kotlin-kapt'
    }

    apply from: "${'$'}rootDir/dependencies.gradle"

    android {
        compileSdkVersion 33
        buildToolsVersion "30.0.3"

        defaultConfig {
            applicationId "$applicationId"
            minSdkVersion 26
            testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
            buildConfigField("String", "Build", "\"release\"")
        }

        buildFeatures {
            buildConfig = true
            viewBinding = true
        }

        buildTypes {
            release {
                minifyEnabled false
                proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
            }

            debug {
                minifyEnabled false
                buildConfigField("String", "Build", "\"debug\"")
            }


            mocked {
                buildConfigField("String", "Build", "\"mocked\"")
                initWith debug
            }

            k3 {
                buildConfigField("String", "Build", "\"k3\"")
                initWith debug
            }
        }
        compileOptions {
            sourceCompatibility JavaVersion.VERSION_11
            targetCompatibility JavaVersion.VERSION_11
        }
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_11.toString()
            freeCompilerArgs = ["-Xjvm-default=all-compatibility"]
        }
    }


    dependencies {
        implementation project(':$featureModuleName')
        implementation vtbDeps.subo_demo
    }
""".trimIndent()