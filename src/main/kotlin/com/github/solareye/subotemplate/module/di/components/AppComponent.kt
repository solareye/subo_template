package com.github.solareye.subotemplate.module.di.components

fun appComponent(
    packageName: String,
) = """
package $packageName.di.components

import com.github.terrakok.cicerone.Router
import dagger.Component
import $packageName.di.modules.AppModule
import $packageName.di.modules.NetworkModule
import ru.vtb.smb.core_kit.ResourceProvider
import ru.vtb.smb.subo_common.di.AppScope
import ru.vtb.smb.subo_common.di.modules.CoreModule
import ru.vtb.smb.subo_common.di.modules.NavigationModule

@AppScope
@Component(
    modules = [
        CoreModule::class,
        NetworkModule::class,
        NavigationModule::class,
        AppModule::class,
    ]
)
interface AppComponent {

    fun resourceProvider(): ResourceProvider
    fun router(): Router

    fun featureComponent(): FeatureComponent.Builder
}
""".trimIndent()