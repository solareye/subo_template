package com.github.solareye.subotemplate.module.navigation

fun featureRouter(
    packageName: String,
) = """
package $packageName.navigation

import com.github.terrakok.cicerone.Router
import ru.vtb.smb.subo_common.navigation.BaseFeatureRouter
import ru.vtb.smb.subo_common.navigation.BaseFeatureRouterImpl
import ru.vtb.smb.subo_common.navigation.FragmentHolder

interface FeatureRouter : BaseFeatureRouter {

    fun showMain()

}

class FeatureRouterImpl(
    private val router: Router,
    private val fragmentHolder: FragmentHolder
) : BaseFeatureRouterImpl(router, fragmentHolder), FeatureRouter {

    override fun showMain() {
        router.newRootChain(Screens.main())
    }

}
""".trimIndent()