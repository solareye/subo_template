package com.github.solareye.subotemplate.module.data.repository

fun featureRepository(
    packageName: String,
) = """
package $packageName.data.repository

interface FeatureRepository {

    suspend fun getTest(): Result<Any>

}
""".trimIndent()