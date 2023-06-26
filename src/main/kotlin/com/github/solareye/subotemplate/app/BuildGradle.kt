package com.github.solareye.subotemplate.app

fun buildGradle(

) = """
buildscript {
    ext.kotlin_version = "1.8.10"

    repositories {
        maven {
            url = uri("https://nexus-ci.corp.dev.vtb/repository/smbmb-maven/")
            credentials {
                username = System.getenv("SMB_PUBLISHING_USERNAME")
                password = System.getenv("SMB_PUBLISHING_PASSWORD")
            }
        }
        maven {
            url = uri("https://nexus-ci.corp.dev.vtb/repository/pfom-maven-lib/")
            credentials {
                username = System.getenv("SMB_PUBLISHING_USERNAME")
                password = System.getenv("SMB_PUBLISHING_PASSWORD")
            }
        }
    }

    dependencies {
        classpath 'com.android.tools.build:gradle:7.2.2'
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:${'$'}kotlin_version"
    }
}

apply from: 'deployment.gradle'

allprojects {
    repositories {
        maven {
            url = uri("https://nexus-ci.corp.dev.vtb/repository/smbmb-maven/")
            credentials {
                username = System.getenv("SMB_PUBLISHING_USERNAME")
                password = System.getenv("SMB_PUBLISHING_PASSWORD")
            }
        }
        maven {
            url = uri("https://nexus-ci.corp.dev.vtb/repository/pfom-maven-lib/")
            credentials {
                username = System.getenv("SMB_PUBLISHING_USERNAME")
                password = System.getenv("SMB_PUBLISHING_PASSWORD")
            }
        }
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}
""".trimIndent()