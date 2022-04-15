package com.github.solareye.subotemplate.module.di.components

fun featureComponent(
    packageName: String,
    featureName: String,
) = """
package $packageName.di.components

import com.github.terrakok.cicerone.Router
import dagger.Subcomponent
import $packageName.di.modules.AppModule
import $packageName.di.modules.FeatureModule
import $packageName.di.modules.ViewModelModule
import $packageName.integration.${featureName}FeatureFragment
import ru.vtb.smb.core_kit.ResourceProvider
import ru.vtb.smb.subo_common.di.FeatureScope
import ru.vtb.smb.subo_common.di.modules.NavigationModule


@FeatureScope
@Subcomponent(
    modules = [
        ViewModelModule::class,
        FeatureModule::class,
    ]
)
interface FeatureComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): FeatureComponent
    }

    fun inject(fragment: ${featureName}FeatureFragment)
}
""".trimIndent()