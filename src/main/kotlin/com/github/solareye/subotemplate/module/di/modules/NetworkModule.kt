package com.github.solareye.subotemplate.module.di.modules

fun networkModule(
    packageName: String,
    featureName: String,
) = """
package $packageName.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import $packageName.BuildConfig
import $packageName.data.remote.EndpointRestApi
import $packageName.data.remote.MockRestApi
import ru.vtb.smb.subo_common.data.remote.ApiClient
import ru.vtb.smb.subo_common.di.AppScope

@Module
class NetworkModule {

    @Provides
    @AppScope
    fun provide${featureName.capitalize()}ApiClient(apiClient: ApiClient, context: Context): EndpointRestApi {
        return if (BuildConfig.MOCK) {
            MockRestApi(context)
        } else {
            apiClient.createApiClient(EndpointRestApi::class.java)
        }
    }
}
""".trimIndent()