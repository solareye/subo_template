package com.github.solareye.subotemplate.module.src.di.modules

fun featureModule(
    packageName: String,
) = """
package $packageName.di.modules

import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import $packageName.navigation.FeatureRouter
import $packageName.navigation.FeatureRouterImpl
import ru.vtb.smb.core_kit.ResourceProvider
import ru.vtb.smb.subo_common.di.FeatureScope
import ru.vtb.smb.subo_common.navigation.FragmentHolder
import ru.vtb.smb.subo_common.navigation.FragmentHolderImpl

@Module
class FeatureModule {

    @Provides
    @FeatureScope
    fun featureRouter(
        router: Router,
        fragmentHolder: FragmentHolder
    ): FeatureRouter =
        FeatureRouterImpl(
            router,
            fragmentHolder
        )

    @Provides
    @FeatureScope
    fun getFragmentHolder(
    ): FragmentHolder =
        FragmentHolderImpl()

}
""".trimIndent()