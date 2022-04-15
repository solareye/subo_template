package com.github.solareye.subotemplate.module.data.remote

fun endpointRestApi(
    packageName: String,
) = """
package $packageName.data.remote

import retrofit2.http.GET
import $packageName.data.model.TestDto

interface EndpointRestApi {

    @GET("test")
    suspend fun getTest(): TestDto

}
""".trimIndent()