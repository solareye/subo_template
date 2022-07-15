package com.github.solareye.subotemplate.app

fun buildGradle(

) = """
buildscript {
    ext.kotlin_version = "1.6.20"

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath 'com.android.tools.build:gradle:7.0.4'
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:${'$'}kotlin_version"
    }
}

apply from: 'deployment.gradle'

allprojects {
    repositories {
        google()
        mavenCentral()
        maven {
            url 'https://nexus-ci.corp.dev.vtb/repository/pfom-maven-lib/'
            credentials(PasswordCredentials)  {
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