package com.github.solareye.subotemplate.module.src.di.modules

fun appModule(
    packageName: String,
) = """
package $packageName.di.modules

import dagger.Module
import dagger.Provides
import $packageName.data.remote.EndpointRestApi
import $packageName.data.repository.FeatureRepository
import $packageName.data.repository.FeatureRepositoryImpl
import ru.vtb.smb.subo_common.di.AppScope

@Module
class AppModule {

    @Provides
    @AppScope
    fun provideRepo(
        api: EndpointRestApi
    ): FeatureRepository {
        return FeatureRepositoryImpl(
            api
        )
    }
}
""".trimIndent()