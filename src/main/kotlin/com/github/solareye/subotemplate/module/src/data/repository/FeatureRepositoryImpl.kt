package com.github.solareye.subotemplate.module.src.data.repository

fun featureRepositoryImpl(
    packageName: String,
) = """
package $packageName.data.repository

import $packageName.data.ErrorMapper
import $packageName.data.remote.EndpointRestApi
import $packageName.model.Test
import ru.vtb.smb.subo_common.data.model.Result
import ru.vtb.smb.subo_common.data.model.handleResult

class FeatureRepositoryImpl(
    private val api: EndpointRestApi
) : FeatureRepository {

    override suspend fun getTest(): Result<Test> {
        return handleResult(
            networkCall = {
                val value = api.getTest()?.value!!
                return@handleResult Test(value)
            },
            errorMapper = ErrorMapper::map
        )
    }

}
""".trimIndent()