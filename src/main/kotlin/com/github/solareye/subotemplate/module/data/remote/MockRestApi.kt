package com.github.solareye.subotemplate.module.data.remote

fun mockRestApi(
    packageName: String,
) = """
package $packageName.data.remote

import android.content.Context
import kotlinx.coroutines.delay
import ru.vtb.onboarding.data.model.TestDto
import ru.vtb.smb.subo_common.data.remote.MockUtils

class MockRestApi(
    private val context: Context
) : EndpointRestApi {

    private companion object {
        const val DELAY = 500L
    }

    override suspend fun getClients(): TestDto {
        delay(DELAY)
        return TestDto(Any())
        //TODO: 
        /*MockUtils.readEntityFromJson(
            context,
            "test.json",
            TestDto::class.java
        )*/
    }

}
""".trimIndent()