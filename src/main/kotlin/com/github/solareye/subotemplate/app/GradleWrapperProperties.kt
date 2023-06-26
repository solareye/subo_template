package com.github.solareye.subotemplate.app

fun gradleWrapperProperties(
) = """
    distributionBase=GRADLE_USER_HOME
distributionUrl=https\://nexus-ci.corp.dev.vtb/repository/smbmb-maven/com/android/build/gradle/7.3.3/gradle-7.3.3-all.zip
distributionPath=wrapper/dists
zipStorePath=wrapper/dists
zipStoreBase=GRADLE_USER_HOME
""".trimIndent()