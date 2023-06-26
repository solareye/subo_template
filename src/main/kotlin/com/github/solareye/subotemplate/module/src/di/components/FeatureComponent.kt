package com.github.solareye.subotemplate.module.src.di.components

fun featureComponent(
    packageName: String,
    featureName: String,
) = """
package $packageName.di.components

import dagger.Subcomponent
import $packageName.di.modules.FeatureModule
import $packageName.di.modules.ViewModelModule
import $packageName.integration.${featureName}FeatureFragment
import $packageName.ui.main.MainFragment
import ru.vtb.smb.subo_common.di.FeatureScope

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
    fun inject(fragment: MainFragment)
}
""".trimIndent()