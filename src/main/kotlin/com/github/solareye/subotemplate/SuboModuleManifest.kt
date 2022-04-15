package com.github.solareye.subotemplate

fun suboConfigProperties() = """
# Repeat interval of updating rates
REPEAT_UPDATE_RATE=3000
# Repeat interval of updating account list
REPEAT_UPDATE_ACCOUNT=10000
# Timeout repeat sms request
REPEAT_SMS_REQUEST_TIME=30000
    """


fun suboGradleFile() = """
        plugins {
            id 'com.android.library'
            id 'kotlin-android'
            id 'kotlin-kapt'
            id 'kotlin-parcelize'
            id 'maven-publish'
        }

        apply from: "${'$'}rootDir/dependencies.gradle"

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
                versionName "1.0.2"
                version "1.0.2"

                buildConfigField("boolean", "MOCK", "false")
                buildConfigField("long", "REPEAT_UPDATE_RATE", properties["REPEAT_UPDATE_RATE"])
                buildConfigField("long", "REPEAT_UPDATE_ACCOUNT", properties["REPEAT_UPDATE_ACCOUNT"])
                buildConfigField("long", "REPEAT_SMS_REQUEST_TIME", properties["REPEAT_SMS_REQUEST_TIME"])
                buildConfigField("String", "SERVER_DSO_URL", "\"https://consulting-ds1-genr01-sfcs-dso0.apps.ds1-genr01.corp.dev.vtb\"")
                buildConfigField("String", "SERVER_K3_URL", "\"https://k3-epaa-app401lv.vtb24.ru\"")
                buildConfigField("String", "SERVER_RELEASE_URL", "\"https://epa.api.vtb.ru\"")
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

            implementation kotlin.ktStdLib
            implementation kotlin.coroutineCore
            implementation kotlin.coroutineAndroid

            implementation androidBase.androidAnnotation
            implementation androidBase.androidCore
            implementation androidBase.fragment

            implementation androidUI.cardView
            implementation androidUI.constraint
            implementation androidUI.recycler

            implementation lifeCycle.lifecycleJava8
            implementation lifeCycle.livedata
            implementation lifeCycle.viewmodel
            implementation lifeCycle.savedstate

            implementation network.okhttp
            implementation network.okhttpLog
            implementation network.retrofit
            implementation network.retrofitGson

            implementation vtbDeps.integration
            implementation vtbDeps.core
            implementation vtbDeps.utils
            implementation vtbDeps.ui
            implementation vtbDeps.ui_2_0
            implementation vtbDeps.authorization

            coreLibraryDesugaring 'com.android.tools:desugar_jdk_libs:1.1.5'

            implementation material

            implementation dagger
            kapt daggerCompiler

            implementation cicerone

            implementation lottie
            implementation (glide) {
                exclude group: "com.android.support"
            }
        }

        afterEvaluate {
            publishing {
                publications {
                    release(MavenPublication) {
                        from components.release
                        artifactId = 'nsTaxCalendarFeature'
                        groupId = 'ru.vtb.smb'
                        version = version

                        pom {
                            name = 'nsTaxCalendarFeature'
                            description = 'Tax calendar subo module'
                            url = ''
                            scm {
                                connection = ''
                                developerConnection = ''
                                url = ''
                            }
                        }
                    }
                }
                repositories {
                    maven {
                        name = "nexus-ci"
                        url = "https://nexus-ci.corp.dev.vtb/repository/pfom-maven-lib/"
                        credentials(PasswordCredentials) {
                            username System.getenv("SMB_PUBLISHING_USERNAME")
                            password System.getenv("SMB_PUBLISHING_PASSWORD")
                        }
                    }
                }
            }
        }
"""

fun suboModuleManifest(
    packageName: String,
) = """
<?xml version="1.0" encoding="utf-8"?>
<manifest package="$packageName" />
"""