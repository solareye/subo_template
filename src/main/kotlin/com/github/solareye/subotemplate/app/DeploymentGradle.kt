package com.github.solareye.subotemplate.app

fun deploymentGradle(

) = """
    /**
     * Setup build variables here
     */
    def yearCode = new Date().format("yy")
    def majorVersion = 1
    def releaseNumber = 00
    def versionCode = 1

    /**
     * Values must not have whitespaces
     */
    ext.deployment = [
            "major"              : majorVersion,
            "year"               : yearCode,
            "release"            : releaseNumber,
            "code"               : versionCode,
            "version"            : "${'$'}{majorVersion}.${'$'}{yearCode}${'$'}{String.format("%02d",releaseNumber)}.${'$'}{versionCode}"
    ]
""".trimIndent()