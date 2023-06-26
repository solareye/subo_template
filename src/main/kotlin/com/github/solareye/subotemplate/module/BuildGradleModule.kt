package com.github.solareye.subotemplate.module

fun buildGradleModule(

) = """
        plugins {
            id 'com.android.library'
            id 'kotlin-android'
            id 'kotlin-kapt'
            id 'kotlin-parcelize'
            id 'maven-publish'
        }

        apply from: "${'$'}rootDir/dependencies.gradle"
        apply from: '../publishing.gradle'

        def propertiesFile = project.file("config.properties")
        def properties = new Properties()
        properties.load(new FileInputStream(propertiesFile))

        android {
            compileSdkVersion 31
            buildToolsVersion "30.0.3"

            defaultConfig {
                minSdkVersion 23
                targetSdkVersion 31
                versionCode 1
                versionName "1.0.0"
                version "1.0.0"

                buildConfigField("boolean", "MOCK", "false")
            }

            buildFeatures {
                viewBinding true
                buildConfig true
            }

            buildTypes {

                debug {
                    minifyEnabled false
                }

                release {
                    minifyEnabled false
                }

                mocked {
                    buildConfigField("boolean", "MOCK", "true")
                }

                k3 {
                    initWith debug
                }
            }
            compileOptions {
                sourceCompatibility JavaVersion.VERSION_11
                targetCompatibility JavaVersion.VERSION_11
                coreLibraryDesugaringEnabled true
            }

            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_11.toString()
                freeCompilerArgs = ["-Xjvm-default=all-compatibility"]
            }
        }

        dependencies {
            implementation fileTree(dir: "libs", include: ["*.jar"])

            implementation vtbDeps.subo_common

            kapt daggerCompiler
            coreLibraryDesugaring 'com.android.tools:desugar_jdk_libs:1.1.5'
        }
"""