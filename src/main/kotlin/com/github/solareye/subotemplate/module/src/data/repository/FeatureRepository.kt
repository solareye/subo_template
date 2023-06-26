package com.github.solareye.subotemplate.module.src.data.repository

fun featureRepository(
    packageName: String,
) = """
package $packageName.data.repository

import $packageName.model.Test
import ru.vtb.smb.subo_common.data.model.Result

interface FeatureRepository {

    suspend fun getTest(): Result<Test>

}
""".trimIndent()