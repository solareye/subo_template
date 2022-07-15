package com.github.solareye.subotemplate.app

fun settingsGradle(
    projectName: String,
    featureModuleName: String,
) = """
    rootProject.name = "${projectName.capitalize()}"
    include ':app', ':$featureModuleName'
""".trimIndent()